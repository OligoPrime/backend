package si.fri;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import si.fri.auth.SimpleAuthenticator;
import si.fri.auth.SimpleAuthorizer;
import si.fri.core.Hello;
import si.fri.core.User;
import si.fri.core.Primer;
import si.fri.db.HelloDAO;
import si.fri.db.UserDAO;
import si.fri.db.PrimerDAO;
import si.fri.health.BasicHealthCheck;
import si.fri.resources.HelloResource;
import si.fri.resources.UserResource;
import si.fri.resources.PrimerResource;

public class BackendApplication extends Application<BackendConfiguration> {

    private final HibernateBundle<BackendConfiguration> hibernate = new HibernateBundle<BackendConfiguration>(Hello.class, User.class, Primer.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(BackendConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new BackendApplication().run(args);

    }

    @Override
    public String getName() {
        return "BioPrime-backend";
    }

    @Override
    public void initialize(final Bootstrap<BackendConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor()));
    }

    @Override
    public void run(final BackendConfiguration configuration,
                    final Environment environment) {
        final HelloDAO helloDAO = new HelloDAO(hibernate.getSessionFactory());
        final UserDAO userDAO = new UserDAO(hibernate.getSessionFactory());
        final PrimerDAO primerDAO = new PrimerDAO(hibernate.getSessionFactory());


        SimpleAuthenticator simpleAuthenticator = new UnitOfWorkAwareProxyFactory(hibernate)
                .create(SimpleAuthenticator.class, UserDAO.class, userDAO);

        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(simpleAuthenticator)
                .setAuthorizer(new SimpleAuthorizer())
                .setRealm("BASIC REALM")
                .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        environment.jersey().register(new HelloResource(
                configuration.getTemplate(),
                configuration.getDefaultName(),
                helloDAO
        ));
        environment.jersey().register(new UserResource(userDAO));
        environment.jersey().register(new PrimerResource(primerDAO));
        environment.healthChecks().register("template", new BasicHealthCheck(configuration.getTemplate()));


    }

}
