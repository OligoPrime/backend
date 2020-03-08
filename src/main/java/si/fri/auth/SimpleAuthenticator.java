package si.fri.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.User;
import si.fri.db.UserDAO;

import java.util.Optional;

public class SimpleAuthenticator implements Authenticator<BasicCredentials, User> {

    private final UserDAO dao;

    public SimpleAuthenticator(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    @UnitOfWork
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        return dao.findById(1L);
    }
}
