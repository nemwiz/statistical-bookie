package livescore;

import csv.helper.ScrapperHelper;
import csv.model.DatabaseMatch;
import dao.LeaguesDAO;
import dao.MatchDAO;
import dao.model.League;
import io.webfolder.ui4j.api.browser.Page;
import livescore.model.LiveScoreMatchDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static io.webfolder.ui4j.api.browser.BrowserFactory.getWebKit;


public class MatchScraper extends LiveScoreScraper {

    public static String SCRAPPER_ID = "matchScrapper";
    private static final String HOME_TEAM = "H";
    private static final String AWAY_TEAM = "A";
    private static final String DRAW = "D";
    private MatchDetailScraper matchDetailScraper;
    private MatchDAO matchDAO;

    public MatchScraper(LeaguesDAO leaguesDAO, MatchDAO matchDAO, int maxMiliseconds, int minMiliseconds) {
        super(leaguesDAO, maxMiliseconds, minMiliseconds);
        this.matchDAO = matchDAO;
        this.matchDetailScraper = new MatchDetailScraper();
    }

    public void scrapeAll() {

        List<League> leagueList = getLeaguesDAO().getAllLeagues();

        leagueList.forEach(league -> {
            int currentRound = this.getCurrentRound(league);
            scrape(league, currentRound);
        });

        this.shutdownBrowser();
    }

    public void scrapeSpecificLeague(String leagueCode) {

        League league = getLeaguesDAO().getLeagueByLeagueCode(leagueCode);

        int currentRound = this.getCurrentRound(league);

        for (int i = 1; i < currentRound; i++) {
            scrape(league, i);
        }

        this.shutdownBrowser();
    }


    public void scrapeAllMatchesFromSpecifiedRoundToCurrentRound(int round) {
        List<League> leagueList = getLeaguesDAO().getAllLeagues();

        for (League league : leagueList) {
            int currentRound = this.getCurrentRound(league);

            for (int i = round; i < currentRound; i++) {
                scrape(league, i);
            }

        }

        this.shutdownBrowser();
    }

    public void scrapeSpecificLeagueFromSpecificRound(int round, String leagueCode) {
        League league = getLeaguesDAO().getLeagueByLeagueCode(leagueCode);

        int currentRound = this.getCurrentRound(league);

        for (int i = round; i <= currentRound; i++) {
            scrape(league, i);
        }

        this.shutdownBrowser();
    }

    public void scrapeLeagueForSpecifiedRound(int round, String leagueCode) {
        League league = getLeaguesDAO().getLeagueByLeagueCode(leagueCode);

        scrape(league, round);

        this.shutdownBrowser();
    }

    private void scrape(League league, int round) {

        Document liveScorePageWithMatchesForCurrentRound = this.navigateToAndReturnRoundPage(league, round);

        List<String> individualMatches = this.getUrlForEachMatch(liveScorePageWithMatchesForCurrentRound);

        System.out.println("individualMatchesUrls = " + individualMatches);
        System.out.println("count = " + individualMatches.size());

        List<DatabaseMatch> databaseMatches = scrapeIndividualMatches(individualMatches, round, league);

        this.matchDAO.insertMatchesIfNotAlreadyPresent(databaseMatches);
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

    private List<DatabaseMatch> scrapeIndividualMatches(List<String> individualMatchUrls, int round, League league) {

        List<DatabaseMatch> databaseMatches = new ArrayList<>();

        individualMatchUrls.forEach(url -> {

            System.out.println("url = " + url);

            Page detailPage = getWebKit().navigate(url);

            try {
                Thread.sleep(getMilisecondsRandomizer().randomize());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String detailPageHtml = detailPage.getDocument().getBody().getInnerHTML();

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

                List<LiveScoreMatchDetail> matchDetails = this.matchDetailScraper.getMatchDetails(detailDocument);

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
                        league.getLeagueCode(),
                        league.getLeagueName(),
                        league.getCountryName(),
                        round,
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

    private String determineOutcome(int homeTeamGoals, int awayTeamGoals) {
        return homeTeamGoals > awayTeamGoals ? HOME_TEAM : awayTeamGoals > homeTeamGoals ? AWAY_TEAM : DRAW;
    }

    private String formatHalfTimeScoreString(String halfTimeScore) {
        return halfTimeScore.replaceAll("[^0-9]+", " ").trim();
    }


}
