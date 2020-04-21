package si.fri.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import io.jsonwebtoken.*;
import si.fri.core.User;
import si.fri.db.UserDAO;

import io.jsonwebtoken.security.Keys;
import java.security.Key;

import java.util.Optional;

public class SimpleAuthenticator implements Authenticator<String, User> {

    private final UserDAO dao;
    private final Key key;
    public static final int CLOCK_SKEW = 30;

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
        }catch (JwtException e) {

            return Optional.empty();
        }
    }
}
