package si.fri.auth;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.hibernate.UnitOfWork;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import si.fri.core.User;
import si.fri.db.UserDAO;

import java.security.Key;
import java.util.Optional;

public class SimpleAuthenticator implements Authenticator<String, User> {

    public static final int CLOCK_SKEW = 30;
    private final UserDAO dao;
    private final Key key;

    public SimpleAuthenticator(UserDAO dao, Key key) {
        this.dao = dao;
        this.key = key;
    }

    @Override
    @UnitOfWork
    public Optional<User> authenticate(String jwt) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setAllowedClockSkewSeconds(CLOCK_SKEW)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);
            return dao.getForUsername(jws.getBody().getSubject());
        } catch (JwtException e) {

            return Optional.empty();
        }
    }
}
