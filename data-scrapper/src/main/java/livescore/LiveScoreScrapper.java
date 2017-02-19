package livescore;

import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.Page;
import csv.helper.MilisecondsRandomizer;
import csv.helper.ScrapperHelper;
import csv.model.DatabaseMatch;
import dao.LeaguesDAO;
import dao.MatchDAO;
import dao.model.League;
import livescore.model.LiveScoreMatchDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.ui4j.api.browser.BrowserFactory.getWebKit;

public class LiveScoreScrapper {

    private static final String ROUND_INFIX = "#/round-";
    private static final String BASE_URL = "http://www.livescore.com";
    private static final String BASE_URL_SOCCER = BASE_URL + "/soccer/";
    private static final String DATA_TYPE = "data-type";
    private static final String HOME_TEAM = "H";
    private static final String AWAY_TEAM = "A";
    private static final String DRAW = "D";

    private MatchDetailsScrapper matchDetailsScrapper;
    private LeaguesDAO leaguesDAO;
    private MatchDAO matchDAO;
    private MilisecondsRandomizer milisecondsRandomizer;

    private BrowserEngine webkit;

    public LiveScoreScrapper(LeaguesDAO leaguesDAO, MatchDAO matchDAO) {
        this.leaguesDAO = leaguesDAO;
        this.matchDAO = matchDAO;
        this.matchDetailsScrapper = new MatchDetailsScrapper();
        this.milisecondsRandomizer = new MilisecondsRandomizer(25000, 15000);
    }

    public void scrape(boolean scrapeFromBeginningOfSeason) {

        this.webkit = BrowserFactory.getWebKit();

//        List<League> leagues = this.leaguesDAO.getAllLeagues();
//
//        leagues.forEach(league ->
//                scrapeLeague(
//                        league.getLeagueCode(),
//                        league.getLeagueName(),
//                        league.getCountryName(),
//                        league.getLiveScoreLink(),
//                        scrapeFromBeginningOfSeason));

        scrapeLeague("I1", "Serie A", "Italy", "italy/serie-a/", scrapeFromBeginningOfSeason);

        this.webkit.shutdown();

    }

    private void scrapeLeague(String leagueCode, String leagueName, String countryName, String liveScoreLink, boolean scrapeFromBeginningOfSeason) {

        try {
            Thread.sleep(this.milisecondsRandomizer.randomize());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Scraping league = " + countryName + " " + leagueName);

        String liveScoreLeagueLinkFull = BASE_URL_SOCCER + liveScoreLink;

        try (Page leaguePage = this.webkit.navigate(liveScoreLeagueLinkFull)) {

            try {
                // wait until redirect
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int currentRound = getCurrentRound(leaguePage);
            System.out.println("currentRound = " + currentRound);
            int previousRound = currentRound - 1;

            if (scrapeFromBeginningOfSeason) {
                for (int i = 1; i < currentRound; i++) {
                    navigateToRoundPage(liveScoreLeagueLinkFull, i, leagueCode, leagueName, countryName);
                }
            } else {
                navigateToRoundPage(liveScoreLeagueLinkFull, previousRound, leagueCode, leagueName, countryName);
            }

        }

    }

    private void navigateToRoundPage(String liveScoreLeagueLinkFull, int previousRound, String leagueCode, String leagueName, String countryName) {
        String liveScoreCurrentLeaguePage = "";

        String fullUrl = liveScoreLeagueLinkFull + ROUND_INFIX + previousRound;
        System.out.println("fullUrl = " + fullUrl);

        try (Page currentRoundPage = this.webkit.navigate(fullUrl)) {
            try {
                // wait until redirect
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            liveScoreCurrentLeaguePage = currentRoundPage.getDocument().queryAll(".content").toString();
            Document liveScorePageWithMatchesForCurrentRound = Jsoup.parse(liveScoreCurrentLeaguePage);

            List<String> individualMatchUrls = getUrlForEachMatch(liveScorePageWithMatchesForCurrentRound);
            System.out.println("individualMatchUrls = " + individualMatchUrls);
            System.out.println("count = " + individualMatchUrls.size());

            List<DatabaseMatch> databaseMatches = scrapeIndividualMatch(individualMatchUrls, previousRound, leagueCode, leagueName, countryName);

            this.matchDAO.insertMatchesIntoDatabase(databaseMatches);
        }
    }

    private List<DatabaseMatch> scrapeIndividualMatch(List<String> individualMatchUrls, int currentRound, String leagueCode, String leagueName, String countryName) {

        List<DatabaseMatch> databaseMatches = new ArrayList<>();

        individualMatchUrls.forEach(url -> {

            System.out.println("url = " + url);

            Page detailPage = getWebKit().navigate(url);

            try {
                Thread.sleep(this.milisecondsRandomizer.randomize());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String detailPageHtml = detailPage.getDocument().getBody().getInnerHTML();

            System.out.println("detailPageHtml = " + detailPageHtml);

            Document detailDocument = Jsoup.parse(detailPageHtml);

            String seasonYear = ScrapperHelper.getCurrentSeasonYearWithDash(LocalDate.now());

            String homeTeam = detailDocument.getElementsByAttributeValue(DATA_TYPE, "home-team").text();
            String awayTeam = detailDocument.getElementsByAttributeValue(DATA_TYPE, "away-team").text();

            String homeTeamGoalsString = detailDocument.getElementsByAttributeValue(DATA_TYPE, "score").get(0).getElementsByAttributeValue(DATA_TYPE, "home").text();

            if (!homeTeamGoalsString.equals("?")) {

                int homeTeamGoals = Integer.parseInt(homeTeamGoalsString);
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

                List<LiveScoreMatchDetail> matchDetails = this.matchDetailsScrapper.getMatchDetails(detailDocument);

                System.out.println("seasonYear = " + seasonYear);
                System.out.println("homeTeam = " + homeTeam);
                System.out.println("awayTeam = " + awayTeam);
                System.out.println("homeTeamGoals = " + homeTeamGoals);
                System.out.println("awayTeamGoals = " + awayTeamGoals);
                System.out.println("finalOutcome = " + finalOutcome);
                System.out.println("halfTimeScore = " + halfTimeScore);
                System.out.println("homeTeamHalfTimeGoals = " + homeTeamHalfTimeGoals);
                System.out.println("awayTeamHalfTimeGoals = " + awayTeamHalfTimeGoals);
                System.out.println("halfTimeOutCome = " + halfTimeOutCome);
                System.out.println("matchDetails = " + matchDetails);

                DatabaseMatch databaseMatch = new DatabaseMatch(
                        leagueCode,
                        leagueName,
                        countryName,
                        currentRound,
                        seasonYear,
                        homeTeam,
                        awayTeam,
                        homeTeamGoals,
                        awayTeamGoals,
                        finalOutcome,
                        homeTeamHalfTimeGoals,
                        awayTeamHalfTimeGoals,
                        halfTimeOutCome,
                        matchDetails
                );

                databaseMatches.add(databaseMatch);
            }

        });

        return databaseMatches;
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
                        .forEach(aTag -> {
                                    if (!aTag.getElementsByClass("scorelink").attr("href").equals("")) {
                                        individualMatchUrls.add(BASE_URL + aTag.getElementsByClass("scorelink").attr("href"));
                                    }
                                }

                        ));

        return individualMatchUrls;
    }

    private String determineOutcome(int homeTeamGoals, int awayTeamGoals) {
        return homeTeamGoals > awayTeamGoals ? HOME_TEAM : awayTeamGoals > homeTeamGoals ? AWAY_TEAM : DRAW;
    }

    private String formatHalfTimeScoreString(String halfTimeScore) {
        return halfTimeScore.replaceAll("[^0-9]+", " ").trim();
    }

}