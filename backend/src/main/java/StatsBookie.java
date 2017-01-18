import com.meltmedia.dropwizard.mongo.MongoBundle;
import controller.FixturesController;
import controller.MainController;
import dao.FixturesDAO;
import dao.MatchDAO;
import dao.MorphiaDatastore;
import healthchecks.DatabaseHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.FixturesResource;
import resource.NumberOfGoalsPerMatchResource;
import scrapper.LiveScoreScrapper;

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

        MatchDAO matchDAO = new MatchDAO(morphiaDatastore);
        FixturesDAO fixturesDAO = new FixturesDAO(morphiaDatastore);

        final DatabaseHealthCheck databaseHealthCheck = new DatabaseHealthCheck(morphiaDatastore.getDatastore());
        environment.healthChecks().register("MorphiaDatastore health check", databaseHealthCheck);

//        HistoricalMatchesScrapper historicalMatchesScrapper = new HistoricalMatchesScrapper(matchDAO);
//        historicalMatchesScrapper.scrapeMatchesFromCsvFilesOnFootballDataSite();

//        LiveScoreScrapper liveScoreScrapper = new LiveScoreScrapper();
//        liveScoreScrapper.main();

        final MainController mainController = new MainController(matchDAO);
        final FixturesController fixturesController = new FixturesController(fixturesDAO);

        final NumberOfGoalsPerMatchResource numberOfGoalsPerMatchResource = new NumberOfGoalsPerMatchResource(mainController);
        final FixturesResource fixturesResource = new FixturesResource(fixturesController);

        environment.jersey().register(numberOfGoalsPerMatchResource);
        environment.jersey().register(fixturesResource);

        environment.jersey().setUrlPattern(BASE_URL);
    }
}
