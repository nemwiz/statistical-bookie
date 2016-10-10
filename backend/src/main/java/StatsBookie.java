import com.meltmedia.dropwizard.mongo.MongoBundle;
import dao.MatchDAO;
import dao.MorphiaDatastore;
import healthchecks.DatabaseHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.MatchResource;

public class StatsBookie extends Application<StatsBookieConfiguration> {

    private MongoBundle<StatsBookieConfiguration> mongoBundle;

    public static void main(String[] args) throws Exception {
        new StatsBookie().run(args);
    }

    @Override
    public String getName() {
        return "stats-bookie-backend-app";
    }

    @Override
    public void initialize(Bootstrap<StatsBookieConfiguration> bootstrap) {
        bootstrap.addBundle(mongoBundle = MongoBundle.<StatsBookieConfiguration>builder()
                .withConfiguration(StatsBookieConfiguration::getMongo)
                .build());
    }

    public void run(StatsBookieConfiguration statsBookieConfiguration, Environment environment) throws Exception {

        MorphiaDatastore morphiaDatastore = new MorphiaDatastore(mongoBundle.getClient(), mongoBundle.getDB().getName());

        MatchDAO matchDAO = new MatchDAO(morphiaDatastore);

        final DatabaseHealthCheck databaseHealthCheck = new DatabaseHealthCheck(morphiaDatastore.getDatastore());
        environment.healthChecks().register("MorphiaDatastore health check", databaseHealthCheck);

        final MatchResource matchResource = new MatchResource(matchDAO);
        environment.jersey().register(matchResource);
    }
}
