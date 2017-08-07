package jobs;

import livescore.MatchScraper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;

public class MatchJob implements org.quartz.Job {

    public MatchJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = jobExecutionContext.getScheduler().getContext();
        } catch (SchedulerException e1) {
            e1.printStackTrace();
        }
        MatchScraper matchScraper =
                (MatchScraper) schedulerContext.get(MatchScraper.SCRAPPER_ID);

        matchScraper.scrapeAll();
    }
}
