package si.fri.auth;


import io.dropwizard.auth.Authorizer;
import si.fri.core.User;

import javax.annotation.Nullable;
import javax.ws.rs.container.ContainerRequestContext;

public class SimpleAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return user.getRole().equals(role);
    }

    @Override
    public boolean authorize(User user, String role, @Nullable ContainerRequestContext requestContext) {
        return user.getRole().equals(role);
    }
}
