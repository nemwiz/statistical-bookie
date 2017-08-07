import com.meltmedia.dropwizard.mongo.MongoBundle;
import dao.FixtureDAO;
import dao.LeaguesDAO;
import dao.MatchDAO;
import dao.MorphiaDatastore;
import healthchecks.DatabaseHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import jobs.FixtureJob;
import jobs.MatchJob;
import livescore.FixtureScraper;
import livescore.MatchScraper;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class DataScrapperApp extends Application<DataScrapperConfiguration> {

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
        FixtureDAO fixtureDAO = new FixtureDAO(morphiaDatastore);

        final DatabaseHealthCheck databaseHealthCheck = new DatabaseHealthCheck(morphiaDatastore.getDatastore());
        environment.healthChecks().register("MorphiaDatastore health check", databaseHealthCheck);

//        HistoricalMatchesScrapper historicalMatchesScrapper = new HistoricalMatchesScrapper(matchDAO, leaguesDAO);
//        historicalMatchesScrapper.scrapeMatchesFromCsvFilesOnFootballDataSite();

        FixtureScraper fixtureScraper = new FixtureScraper(leaguesDAO, fixtureDAO, 25000, 10000);
        MatchScraper matchScraper = new MatchScraper(leaguesDAO, matchDAO, 25000, 10000);
//        fixtureScraper.scrapeUpcomingFixturesForSpecificLeague("E0");
//        matchScraper.scrapeSpecificLeague("I2");

        this.scheduleCronScrappingJobs(fixtureScraper, matchScraper);

    }

    private void scheduleCronScrappingJobs(FixtureScraper fixtureScraper, MatchScraper matchScraper) throws SchedulerException {

        String triggerGroup = "group1";

        JobDetail fixturesScrappingJob = newJob(FixtureJob.class)
                .withIdentity("fixtureJob", triggerGroup)
                .build();

        Trigger fixtureScrappingTrigger = newTrigger()
                .withIdentity("fixtureScrappingTrigger", triggerGroup)
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInHours(72)
                        .repeatForever())
                .build();

        JobDetail matchesScrappingJob = newJob(MatchJob.class)
                .withIdentity("matchesJob", triggerGroup)
                .build();

        Trigger matchesScrappingTrigger = newTrigger()
                .withIdentity("matchesScrappingTrigger", triggerGroup)
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInHours(96)
                        .repeatForever())
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.getContext().put(FixtureScraper.SCRAPPER_ID, fixtureScraper);
        scheduler.getContext().put(MatchScraper.SCRAPPER_ID, matchScraper);

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(fixturesScrappingJob, fixtureScrappingTrigger);
        scheduler.scheduleJob(matchesScrappingJob, matchesScrappingTrigger);

        scheduler.start();
    }
}
