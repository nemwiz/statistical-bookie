import aggregator.*;
import com.meltmedia.dropwizard.mongo.MongoBundle;
import controller.FixturesController;
import controller.LeaguesController;
import controller.MainController;
import dao.FixturesDAO;
import dao.LeaguesDAO;
import dao.MatchDAO;
import dao.MorphiaDatastore;
import healthchecks.DatabaseHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import resource.FixturesResource;
import resource.LeaguesResource;
import resource.AggregatedMatchResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class StatsBookie extends Application<StatsBookieConfiguration> {

    private static final String BASE_URL = "/api/*";

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

        final DatabaseHealthCheck databaseHealthCheck = new DatabaseHealthCheck(morphiaDatastore.getDatastore());
        environment.healthChecks().register("MorphiaDatastore health check", databaseHealthCheck);

        // Main controller set up
        MatchDAO matchDAO = new MatchDAO(morphiaDatastore);
        final NumberOfGoalsAggregator numberOfGoalsAggregator = new NumberOfGoalsAggregator();
        final TeamGoalsAggregator teamGoalsAggregator = new TeamGoalsAggregator();
        final MatchOutcomeAggregator matchOutcomeAggregator = new MatchOutcomeAggregator();
        final MatchDetailOutcomeAggregator matchDetailOutcomeAggregator = new MatchDetailOutcomeAggregator();
        final NumberOfGoalsAndWinsAggregator numberOfGoalsAndWinsAggregator = new NumberOfGoalsAndWinsAggregator();
        final HalfTimeWithMoreGoalsAggregator halfTimeWithMoreGoalsAggregator = new HalfTimeWithMoreGoalsAggregator();
        final ExactResultAggregator exactResultAggregator = new ExactResultAggregator();

        final MainController mainController = new MainController(
                matchDAO,
                numberOfGoalsAggregator,
                teamGoalsAggregator,
                matchOutcomeAggregator,
                matchDetailOutcomeAggregator,
                numberOfGoalsAndWinsAggregator,
                halfTimeWithMoreGoalsAggregator,
                exactResultAggregator);

        FixturesDAO fixturesDAO = new FixturesDAO(morphiaDatastore);
        LeaguesDAO leaguesDAO = new LeaguesDAO(morphiaDatastore);

        final FixturesController fixturesController = new FixturesController(fixturesDAO);
        final LeaguesController leaguesController = new LeaguesController(leaguesDAO, matchDAO);

        final AggregatedMatchResource aggregatedMatchResource = new AggregatedMatchResource(mainController);
        final FixturesResource fixturesResource = new FixturesResource(fixturesController);
        final LeaguesResource leaguesResource = new LeaguesResource(leaguesController);

        environment.jersey().register(aggregatedMatchResource);
        environment.jersey().register(fixturesResource);
        environment.jersey().register(leaguesResource);

        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "http://localhost:4200");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin,If-None-Match");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/api/*");

        environment.jersey().setUrlPattern(BASE_URL);
    }
}
