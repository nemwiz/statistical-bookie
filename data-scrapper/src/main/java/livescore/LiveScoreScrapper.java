package livescore;

import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.Page;
import dao.LeaguesDAO;
import dao.model.League;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import static com.ui4j.api.browser.BrowserFactory.getWebKit;

public class LiveScoreScrapper {

    private static String BASE_URL = "http://www.livescore.com";
    private static String BASE_URL_SOCCER = BASE_URL + "/soccer/";
    private static String DATA_TYPE = "data-type";
    private static String HOME_TEAM = "H";
    private static String AWAY_TEAM = "A";
    private static String DRAW = "D";

    private LeaguesDAO leaguesDAO;

    private BrowserEngine webkit;

    public LiveScoreScrapper(LeaguesDAO leaguesDAO) {
        this.leaguesDAO = leaguesDAO;
    }

    public void scrape() {

        this.webkit = BrowserFactory.getWebKit();

        List<League> leagues = this.leaguesDAO.getAllLeagues();

        leagues.forEach(league -> scrapeLeague(
                league.getLeagueCode(),
                league.getLeagueName(),
                league.getCountryName(),
                league.getLiveScoreLink()));

//        scrapeLeague(null, null, null, null);

        this.webkit.shutdown();

    }

    public void scrapeLeague(String leagueCode, String leagueName, String countryName, String liveScoreLink) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Scraping league = " + leagueName);

        String liveScoreLeagueLinkFull = BASE_URL_SOCCER + liveScoreLink;
//        String liveScoreLeagueLinkFull = BASE_URL_SOCCER + "england/championship";


        try (Page leaguePage = this.webkit.navigate(liveScoreLeagueLinkFull)) {

            try {
                // wait until redirect
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // TODO get current round and navigate to round page
            int currentRound = getCurrentRound(leaguePage);
            System.out.println("currentRound = " + currentRound);

            String liveScoreCurrentLeaguePage = leaguePage.getDocument().queryAll(".content").toString();
            Document liveScorePageWithMatchesForCurrentRound = Jsoup.parse(liveScoreCurrentLeaguePage);

            List<String> individualMatchUrls = getUrlForEachMatch(liveScorePageWithMatchesForCurrentRound);
            System.out.println("individualMatchUrls = " + individualMatchUrls);

//            scrapeIndividualMatch(individualMatchUrls, currentRound);

            leaguePage.close();

        }


    }

    private int getCurrentRound(Page page) {
        page.getDocument().query(".cal-sel").get().getChildren().get(0).click();

        Document roundPicker = Jsoup.parse(page.getDocument().query(".abs").toString());

        return Integer.parseInt(roundPicker.getElementsByClass("item b").get(0).getElementsByTag("a").attr("data-id"));
    }

    private List<String> getUrlForEachMatch(Document document) {

        List<String> individualMatchUrls = new ArrayList<>();

        document.getElementsByAttributeValue(DATA_TYPE, "container").
                forEach(element -> element.getElementsByAttributeValue(DATA_TYPE, "evt")
                        .forEach(aTag -> individualMatchUrls.add(BASE_URL + aTag.getElementsByClass("scorelink").attr("href"))));

        return individualMatchUrls;
    }

    private void scrapeIndividualMatch(List<String> individualMatchUrls, int currentRound) {
        individualMatchUrls.forEach(url -> {

            System.out.println("url = " + url);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Page detailPage = getWebKit().navigate(url);

            String detailPageHtml = detailPage.getDocument().getBody().getInnerHTML();

            System.out.println("detailPageHtml = " + detailPageHtml);

            Document detailDocument = Jsoup.parse(detailPageHtml);

            String homeTeam = detailDocument.getElementsByAttributeValue(DATA_TYPE, "home-team").text();
            String awayTeam = detailDocument.getElementsByAttributeValue(DATA_TYPE, "away-team").text();

            int homeTeamGoals = Integer.parseInt(detailDocument.getElementsByAttributeValue(DATA_TYPE, "score").get(0).getElementsByAttributeValue(DATA_TYPE, "home").text());
            int awayTeamGoals = Integer.parseInt(detailDocument.getElementsByAttributeValue(DATA_TYPE, "score").get(0).getElementsByAttributeValue(DATA_TYPE, "away").text());

            String finalOutcome = determineOutcome(homeTeamGoals, awayTeamGoals);

            String halfTimeScore = detailDocument.getElementsByAttributeValue(DATA_TYPE, "header-score").get(0).getElementsByAttributeValue(DATA_TYPE, "score").text();

            int homeTeamHalfTimeGoals = 0;
            int awayTeamHalfTimeGoals = 0;
            String halfTimeOutCome = DRAW;

            if (halfTimeScore.length() > 2) {

                String halfTimeScoreFormatted = formatHalfTimeScoreString(halfTimeScore);

                homeTeamHalfTimeGoals = Integer.parseInt(halfTimeScoreFormatted.substring(0, 1));
                awayTeamHalfTimeGoals = Integer.parseInt(halfTimeScoreFormatted.substring(halfTimeScoreFormatted.length() - 1, halfTimeScoreFormatted.length()));
                halfTimeOutCome = determineOutcome(homeTeamHalfTimeGoals, awayTeamHalfTimeGoals);
            }


            System.out.println("homeTeam = " + homeTeam);
            System.out.println("awayTeam = " + awayTeam);
            System.out.println("homeTeamGoals = " + homeTeamGoals);
            System.out.println("awayTeamGoals = " + awayTeamGoals);
            System.out.println("finalOutcome = " + finalOutcome);
            System.out.println("halfTimeScore = " + halfTimeScore);
            System.out.println("homeTeamHalfTimeGoals = " + homeTeamHalfTimeGoals);
            System.out.println("awayTeamHalfTimeGoals = " + awayTeamHalfTimeGoals);
            System.out.println("halfTimeOutCome = " + halfTimeOutCome);

            MatchDetailsScrapper matchDetailsScrapper = new MatchDetailsScrapper();
            matchDetailsScrapper.getMatchDetails(detailDocument);

            System.out.println(" ");


        });
    }

    private String determineOutcome(int homeTeamGoals, int awayTeamGoals) {
        return homeTeamGoals > awayTeamGoals ? HOME_TEAM : awayTeamGoals > homeTeamGoals ? AWAY_TEAM : DRAW;
    }

    private String formatHalfTimeScoreString(String halfTimeScore) {
        return halfTimeScore.replaceAll("[^0-9]+", " ").trim();
    }

}