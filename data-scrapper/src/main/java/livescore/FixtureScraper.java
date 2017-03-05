package livescore;

import csv.helper.ScrapperHelper;
import dao.FixtureDAO;
import dao.LeaguesDAO;
import dao.model.League;
import livescore.model.Fixture;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FixtureScraper extends LiveScoreScraper {

    private FixtureDAO fixtureDAO;

    public FixtureScraper(LeaguesDAO leaguesDAO, FixtureDAO fixtureDAO, int maxMiliseconds, int minMiliseconds) {
        super(leaguesDAO, maxMiliseconds, minMiliseconds);
        this.fixtureDAO = fixtureDAO;
    }

    public void scrapeUpcomingFixtures() {

        List<League> leagues = getLeaguesDAO().getAllLeagues();
        leagues.forEach(this::scrape);

        this.shutdownBrowser();
    }

    public void scrapeUpcomingFixturesForSpecificLeague(String leagueCode) {

        League league = getLeaguesDAO().getLeagueByLeagueCode(leagueCode);
        scrape(league);

        this.shutdownBrowser();
    }

    private void scrape(League league) {

        int currentRound = this.getCurrentRound(league);
        int nextRound = currentRound + 1;

        Document liveScorePageWithMatchesForCurrentRound = this.navigateToAndReturnRoundPage(league, nextRound);

        Map<String, String> fixtureId = this.getDatesWithFixturesIds(liveScorePageWithMatchesForCurrentRound);
        this.getUpcomingFixtures(fixtureId, liveScorePageWithMatchesForCurrentRound, league, nextRound);

    }

    private Map<String, String> getDatesWithFixturesIds(Document document) {

        List<String> datesAndMatchIds = new ArrayList<>();

        document.getElementsByAttributeValue(DATA_TYPE, "container").
                forEach(
                        element -> element.getElementsByAttribute(DATA_ID)
                                .forEach(row -> datesAndMatchIds.add(row.attr(DATA_ID)))
                );

        return mapEachFixtureIdToCorrespondingDate(datesAndMatchIds);
    }

    private Map<String, String> mapEachFixtureIdToCorrespondingDate(List<String> datesAndMatchIds) {
        Map<String, String> dateWithFixtureId = new LinkedHashMap<>();

        int currentIndex = 0;

        for (int i = 0; i < datesAndMatchIds.size(); i++) {

            if (!datesAndMatchIds.get(i).contains("soccer")) {
                currentIndex = i;
            }
            if (datesAndMatchIds.get(i).contains("soccer")) {
                dateWithFixtureId.put(datesAndMatchIds.get(i), datesAndMatchIds.get(currentIndex));
            }
        }

        return dateWithFixtureId;
    }

    private void getUpcomingFixtures(Map<String, String> fixtureIdsWithDates,
                                     Document currentRoundPage,
                                     League league,
                                     int round) {

        List<Fixture> fixtures = new ArrayList<>();

        fixtureIdsWithDates.forEach(
                (fixtureId, fixtureDate) -> fixtures.add(
                        mapMatchRowToFixture(
                                currentRoundPage.getElementsByAttributeValue(DATA_ID, fixtureId).get(0),
                                fixtureDate,
                                league,
                                round)
                )
        );

        System.out.println("fixtures = " + fixtures);

        this.fixtureDAO.insertFixtures(fixtures);
    }

    private Fixture mapMatchRowToFixture(Element rowElement, String fixtureDate, League league, int round) {

        String time = rowElement.getElementsByClass("min").text().replace("Limited coverage", "");
        String homeTeam = rowElement.getElementsByClass("ply tright name").text();
        String awayTeam = rowElement.getElementsByClass("ply name").text();

        return new Fixture(
                league.getId(),
                league.getLeagueCode(),
                league.getLeagueName(),
                league.getCountryName(),
                round,
                this.convertToStringDateFormat(fixtureDate),
                time,
                ScrapperHelper.getCurrentSeasonYearWithDash(LocalDate.now()),
                homeTeam,
                awayTeam
        );

    }

    private String convertToStringDateFormat(String fixtureDate) {

        String year = fixtureDate.substring(0, 4).substring(2, 4);
        String month = fixtureDate.substring(4, 6);
        String day = fixtureDate.substring(6, 8);

        return day + "/" + month + "/" + year;

    }
}
