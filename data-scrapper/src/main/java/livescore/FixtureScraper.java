package livescore;

import dao.LeaguesDAO;
import dao.model.League;
import livescore.model.Fixture;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FixtureScraper extends LiveScoreScraper {

    public FixtureScraper(LeaguesDAO leaguesDAO, int maxMiliseconds, int minMiliseconds) {
        super(leaguesDAO, maxMiliseconds, minMiliseconds);
    }

    public void scrapeUpcomingFixtures() {

        List<League> leagues = getLeaguesDAO().getAllLeagues();
        leagues.forEach(this::scrape);
    }

    public void scrapeUpcomingFixturesForSpecificLeague(String leagueCode) {

        League league = getLeaguesDAO().getLeagueByLeagueCode(leagueCode);
        scrape(league);
    }

    private void scrape(League league) {

        int currentRound = this.getCurrentRound(league);
        int nextRound = currentRound + 1;

        Document liveScorePageWithMatchesForCurrentRound = this.navigateToAndReturnRoundPage(league, currentRound);

        Map<String, String> fixtureId = this.getDatesWithFixturesIds(liveScorePageWithMatchesForCurrentRound);
        this.getUpcomingFixtures(fixtureId, liveScorePageWithMatchesForCurrentRound, league, nextRound);

        this.shutdownBrowser();
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
                (fixtureId, fixtureDate) -> {
                    mapMatchRowToFixture(currentRoundPage.getElementsByAttributeValue(DATA_ID, fixtureId).get(0), fixtureDate);
                }
        );

    }

    private void mapMatchRowToFixture(Element rowElement, String fixtureDate) {

//        Fixture fixture = new Fixture()

        String time = rowElement.getElementsByClass("min").text().replace("Limited coverage", "");
        String homeTeam = rowElement.getElementsByClass("ply tright name").text();
        String awayTeam = rowElement.getElementsByClass("ply name").text();

        System.out.println("awayTeam = " + awayTeam);
        System.out.println("homeTeam = " + homeTeam);
        System.out.println("time = " + time + " date " + this.convertToStringDateFormat(fixtureDate));


    }

    private String convertToStringDateFormat(String fixtureDate) {

        String year = fixtureDate.substring(0, 4).substring(2, 4);
        String month = fixtureDate.substring(4, 6);
        String day = fixtureDate.substring(6, 8);

        return day + "/" + month + "/" + year;

    }
}
