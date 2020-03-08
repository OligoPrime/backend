package si.fri;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import si.fri.core.Hello;
import si.fri.db.HelloDAO;
import si.fri.health.BasicHealthCheck;
import si.fri.resources.HelloResource;

public class BackendApplication extends Application<BackendConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BackendApplication().run(args);
    }

    @Override
    public String getName() {
        return "BioPrime-backend";
    }

    private final HibernateBundle<BackendConfiguration> hibernate = new HibernateBundle<BackendConfiguration>(Hello.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(BackendConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<BackendConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final BackendConfiguration configuration,
                    final Environment environment) {
        final HelloDAO dao = new HelloDAO(hibernate.getSessionFactory());
        final HelloResource resource = new HelloResource(
                configuration.getTemplate(),
                configuration.getDefaultName(),
                dao
        );
        environment.healthChecks().register("template", new BasicHealthCheck(configuration.getTemplate()));
        environment.jersey().register(resource);

    }

}
