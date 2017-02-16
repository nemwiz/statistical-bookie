import com.meltmedia.dropwizard.mongo.MongoBundle;
import csv.HistoricalMatchesScrapper;
import dao.LeaguesDAO;
import dao.MatchDAO;
import dao.MorphiaDatastore;
import healthchecks.DatabaseHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import livescore.LiveScoreScrapper;

public class DataScrapperApp extends Application<DataScrapperConfiguration>{

    private static String APP_NAME = "data-scrapper-app";
    private MongoBundle<DataScrapperConfiguration> mongoBundle;

    public static void main(String[] args) throws Exception {
        new DataScrapperApp().run(args);
    }

    @Override
    public String getName() {
        return APP_NAME;
    }

    @Override
    public void initialize(Bootstrap<DataScrapperConfiguration> bootstrap) {
        bootstrap.addBundle(mongoBundle = MongoBundle.<DataScrapperConfiguration>builder()
                .withConfiguration(DataScrapperConfiguration::getMongo)
                .build());
    }

    public void run(DataScrapperConfiguration dataScrapperConfiguration, Environment environment) throws Exception {

        MorphiaDatastore morphiaDatastore = new MorphiaDatastore(mongoBundle.getClient(), mongoBundle.getDB().getName());

        MatchDAO matchDAO = new MatchDAO(morphiaDatastore);
        LeaguesDAO leaguesDAO = new LeaguesDAO(morphiaDatastore);

        final DatabaseHealthCheck databaseHealthCheck = new DatabaseHealthCheck(morphiaDatastore.getDatastore());
        environment.healthChecks().register("MorphiaDatastore health check", databaseHealthCheck);

//        HistoricalMatchesScrapper historicalMatchesScrapper = new HistoricalMatchesScrapper(matchDAO, leaguesDAO);
//        historicalMatchesScrapper.scrapeMatchesFromCsvFilesOnFootballDataSite();

        LiveScoreScrapper liveScoreScrapper = new LiveScoreScrapper(leaguesDAO, matchDAO);
        boolean scrapeFromBeginningOfSeason = true;
        liveScoreScrapper.scrape(scrapeFromBeginningOfSeason);

    }
}
