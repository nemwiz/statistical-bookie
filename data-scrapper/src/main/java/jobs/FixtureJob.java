package jobs;

import livescore.FixtureScraper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;

public class FixtureJob implements org.quartz.Job {

    public FixtureJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = jobExecutionContext.getScheduler().getContext();
        } catch (SchedulerException e1) {
            e1.printStackTrace();
        }
        FixtureScraper fixtureScraper =
                (FixtureScraper) schedulerContext.get(FixtureScraper.SCRAPPER_ID);

        fixtureScraper.scrapeUpcomingFixtures();
    }
}
