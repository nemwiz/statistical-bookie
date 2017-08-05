package livescore;

import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.Page;
import csv.helper.MilisecondsRandomizer;
import dao.LeaguesDAO;
import dao.model.League;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

abstract class LiveScoreScraper {

    private static final String ROUND_INFIX = "#/round-";
    static final String BASE_URL = "http://www.livescore.com";
    private static final String BASE_URL_SOCCER = BASE_URL + "/soccer/";
    static final String DATA_TYPE = "data-type";
    static final String DATA_ID = "data-id";

    private BrowserEngine webkit;
    private LeaguesDAO leaguesDAO;
    private MilisecondsRandomizer milisecondsRandomizer;

    LiveScoreScraper(LeaguesDAO leaguesDAO, int maxMiliseconds, int minMiliseconds) {
        this.webkit = BrowserFactory.getWebKit();
        this.leaguesDAO = leaguesDAO;
        this.milisecondsRandomizer = new MilisecondsRandomizer(maxMiliseconds, minMiliseconds);
    }


    int getCurrentRound(League league) {

        Page leaguePage = this.scrapeLeaguePage(league);
        leaguePage.getDocument().query(".cal-sel").get().getChildren().get(0).click();

        Document roundPicker = Jsoup.parse(leaguePage.getDocument().query(".abs").toString());

        return Integer.parseInt(roundPicker.getAllElements().get(0).getElementsByTag("a").attr(DATA_ID));
    }

    Document navigateToAndReturnRoundPage(League league, int round) {

        String roundPageUrl = this.constructLeagueFullUrl(league) + ROUND_INFIX + round;
        System.out.println("roundPageUrl = " + roundPageUrl);

        try (Page currentRoundPage = this.webkit.navigate(roundPageUrl)) {
            waitForPageToLoad();

            String liveScoreCurrentLeaguePage = currentRoundPage.getDocument().queryAll(".content").toString();

            return Jsoup.parse(liveScoreCurrentLeaguePage);
        }

    }

    private Page scrapeLeaguePage(League league) {

        System.out.println("Scraping league = " + league.getCountryName() + " " + league.getLeagueName());

        randomPause();

        String liveScoreUrl = this.constructLeagueFullUrl(league);

        try (Page leaguePage = this.webkit.navigate(liveScoreUrl)) {
            waitForPageToLoad();
            return leaguePage;
        }

    }

    private void randomPause() {
        try {
            Thread.sleep(this.milisecondsRandomizer.randomize());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void shutdownBrowser() {
        this.webkit.shutdown();
    }

    private String constructLeagueFullUrl(League league) {
        return BASE_URL_SOCCER + league.getLiveScoreLink();
    }

    private void waitForPageToLoad() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    LeaguesDAO getLeaguesDAO() {
        return leaguesDAO;
    }

    MilisecondsRandomizer getMilisecondsRandomizer() {
        return milisecondsRandomizer;
    }
}
