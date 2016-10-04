import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.MatchResource;

public class StatsBookie extends Application<StatsBookieConfiguration> {
    public static void main(String[] args) throws Exception {
        new StatsBookie().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<StatsBookieConfiguration> bootstrap) {
        // nothing to do yet
    }

    public void run(StatsBookieConfiguration statsBookieConfiguration, Environment environment) throws Exception {
        final MatchResource matchResource = new MatchResource();
        environment.jersey().register(matchResource);
    }
}
