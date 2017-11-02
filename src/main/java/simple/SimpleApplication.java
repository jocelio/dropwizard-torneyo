package simple;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import simple.api.Equipe;
import simple.api.Player;
import simple.db.EquipesDAO;
import simple.db.PlayersDAO;
import simple.api.Saying;
import simple.health.TemplateHealthCheck;
import simple.resources.EquipeResource;
import simple.resources.HelloWorldResource;
import simple.resources.PlayerResource;

public class SimpleApplication extends Application<SimpleConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SimpleApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-simple";
    }

    private final HibernateBundle<SimpleConfiguration> hibernate = new HibernateBundle<SimpleConfiguration>(
          Player.class
        , Equipe.class
    ) {
        public DataSourceFactory getDataSourceFactory(SimpleConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<SimpleConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final SimpleConfiguration configuration, final Environment environment) {


        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        ));

        environment.jersey().register(new PlayerResource(new PlayersDAO(hibernate.getSessionFactory())));
        environment.jersey().register(new EquipeResource(new EquipesDAO(hibernate.getSessionFactory())));
    }

}
