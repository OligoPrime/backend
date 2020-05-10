package si.fri;

import com.github.benmanes.caffeine.cache.CaffeineSpec;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import si.fri.auth.SimpleAuthenticator;
import si.fri.auth.SimpleAuthorizer;
import si.fri.core.*;
import si.fri.core.primer_foreign_tables.*;
import si.fri.db.*;
import si.fri.health.BasicHealthCheck;
import si.fri.resources.*;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.security.Key;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackendApplication extends Application<BackendConfiguration> {

    private final HibernateBundle<BackendConfiguration> hibernate = new HibernateBundle<BackendConfiguration>(Hello.class, User.class,
            Primer.class, PositionInReference.class, PurificationMethod.class, Freezer.class, Drawer.class, Box.class,
            Organism.class, HumanGenomBuild.class, Formulation.class, TypeOfPrimer.class, PrimerApplication.class,
            FiveModification.class, ThreeModification.class, Project.class, Manufacturer.class, Supplier.class,
            ThreeQuencher.class, FiveDye.class, Gen.class, NcbiGenId.class, DesignerName.class, DesignerPublication.class,
            DesignerDatabase.class, History.class) {
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


    private void configureCors(Environment environment) {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    }

    @Override
    public void run(final BackendConfiguration configuration,
                    final Environment environment) {
        configureCors(environment);

        final HelloDAO helloDAO = new HelloDAO(hibernate.getSessionFactory());
        final UserDAO userDAO = new UserDAO(hibernate.getSessionFactory());
        final PrimerDAO primerDAO = new PrimerDAO(hibernate.getSessionFactory());
        final HistoryDAO historyDAO = new HistoryDAO(hibernate.getSessionFactory());
        final PrimerForeignTablesDAO primerForeignTablesDAO = new PrimerForeignTablesDAO(hibernate.getSessionFactory());

        final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


        SimpleAuthenticator simpleAuthenticator = new UnitOfWorkAwareProxyFactory(hibernate)
                .create(SimpleAuthenticator.class, new Class<?>[]{UserDAO.class, Key.class}, new Object[]{userDAO, key});

        CachingAuthenticator<String, User> cachingAuthenticator = new CachingAuthenticator<>(
                environment.metrics(), simpleAuthenticator,
                CaffeineSpec.parse(configuration.getAuthenticationCachePolicy()));

        environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(cachingAuthenticator)
                        .setAuthorizer(new SimpleAuthorizer())
                        .setPrefix("Bearer")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        environment.jersey().register(new HelloResource(
                configuration.getTemplate(),
                configuration.getDefaultName(),
                helloDAO
        ));
        environment.jersey().register(new UserResource(userDAO));
        environment.jersey().register(new PrimerResource(primerDAO, primerForeignTablesDAO));
        environment.jersey().register(new HistoryResource(historyDAO));
        environment.jersey().register(new CsvResource(primerDAO, primerForeignTablesDAO));
        environment.jersey().register(MultiPartFeature.class);
        environment.jersey().register(new AuthenticationResource(userDAO, key));
        environment.healthChecks().register("template", new BasicHealthCheck(configuration.getTemplate()));
        environment.jersey().register(new LoggingFeature(Logger.getLogger("si.fri"), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));

    }

}
