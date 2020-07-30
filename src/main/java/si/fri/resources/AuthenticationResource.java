package si.fri.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import si.fri.auth.LoginForm;
import si.fri.auth.NewPasswordForm;
import si.fri.auth.Passwords;
import si.fri.core.User;
import si.fri.db.UserDAO;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static si.fri.auth.SimpleAuthenticator.CLOCK_SKEW;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {


    private static final int TOKEN_DURATION = 1800;
    private final UserDAO userDAO;
    private final Key key;

    public AuthenticationResource(UserDAO userDAO, Key key) {
        this.userDAO = userDAO;
        this.key = key;
    }

    @POST
    @UnitOfWork
    @Path("/isValid")
    public Response isValid(String jwt) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setAllowedClockSkewSeconds(CLOCK_SKEW)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);

            return Response.ok().build();

        } catch (JwtException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @UnitOfWork
    @Path("/refresh")
    public Response getJWT(String jwt) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setAllowedClockSkewSeconds(CLOCK_SKEW)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);

            return Response.ok(Jwts.builder()
                    .setSubject(jws.getBody().getSubject())
                    .setExpiration(Date.from(Instant.now().plusSeconds(TOKEN_DURATION)))
                    .signWith(key)
                    .compact())
                    .build();

        } catch (JwtException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @UnitOfWork
    @Path("/login")
    public Response getJWT(LoginForm loginForm) {
        Optional<User> userResult = userDAO.getForUsername(loginForm.username);
        if (userResult.isPresent()) {
            User user = userResult.get();
            byte[] generatedHash = Passwords.hashPassword(loginForm.password, user.getSalt());
            if (generatedHash != null) {
                if (Arrays.equals(generatedHash, user.getHash())) {
                    return Response.ok(Jwts.builder()
                            .setSubject(loginForm.username)
                            .setExpiration(Date.from(Instant.now().plusSeconds(TOKEN_DURATION)))
                            .claim("role", user.getRole())
                            .signWith(key)
                            .compact())
                            .build();
                }
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @UnitOfWork
    @Path("/change-password")
    public Response changePassword(NewPasswordForm newPasswordForm) {
        Optional<User> userResult = userDAO.getForUsername(newPasswordForm.username);
        if (userResult.isPresent()) {
            User user = userResult.get();

            byte[] generatedHash = Passwords.hashPassword(newPasswordForm.password, user.getSalt());
            if (generatedHash != null) {
                if (Arrays.equals(generatedHash, user.getHash())) {
                    userDAO.updatePassword(user, newPasswordForm.newPassword);
                    return Response.ok(Jwts.builder()
                            .setSubject(newPasswordForm.username)
                            .setExpiration(Date.from(Instant.now().plusSeconds(TOKEN_DURATION)))
                            .claim("role", user.getRole())
                            .signWith(key)
                            .compact())
                            .build();
                }
                return Response.serverError().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
