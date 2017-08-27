import aggregator.MatchOutcomeAggregator;
import model.Match;
import org.junit.Before;
import org.junit.Test;
import viewmodel.HalftimesOutcomeAndMatchOutcomeModel;

import java.util.ArrayList;
import java.util.List;

import static helper.Constants.*;
import static junit.framework.TestCase.assertEquals;

public class MatchOutcomeAggregatorTest {

    private MatchOutcomeAggregator matchOutcomeAggregator;
    private HalftimesOutcomeAndMatchOutcomeModel matchOutcomeView;
    private List<Match> matches;

    @Before
    public void setUp() {
        matches = new ArrayList<>();
    }

    @Test
    public void checkThatCountOfHomeTeamWinAtFullAndHalfTimeIsCorrect() {

        long countOfMatches = 3;
        createMatches(countOfMatches, HOME_TEAM_WIN);
        matchOutcomeAggregator = new MatchOutcomeAggregator();

        matchOutcomeView = matchOutcomeAggregator.getAggregatedCount(this.matches);

        long fullTimeCount = matchOutcomeView.getHomeTeam().get("winFulltime");
        long halfTimeCount = matchOutcomeView.getHomeTeam().get("winFirstHalftime");

        assertEquals(countOfMatches, fullTimeCount);
        assertEquals(countOfMatches, halfTimeCount);
    }

    @Test
    public void checkThatCountOfDrawMatchesAtFullAndHalfTimeIsCorrect() {

        long countOfDrawMatches = 2;
        createMatches(countOfDrawMatches, DRAW);
        matchOutcomeAggregator = new MatchOutcomeAggregator();

        matchOutcomeView = matchOutcomeAggregator.getAggregatedCount(this.matches);

        long fullTimeCount = matchOutcomeView.getDraw().get("winFulltime");
        long halfTimeCount = matchOutcomeView.getDraw().get("winFirstHalftime");

        assertEquals(countOfDrawMatches, fullTimeCount);
        assertEquals(countOfDrawMatches, halfTimeCount);
    }

    @Test
    public void checkThatCountOfAwayTeamWinAtFullAndHalfTimeIsCorrect() {

        long countOfAwayTeamWinMatches = 5;
        createMatches(countOfAwayTeamWinMatches, AWAY_TEAM_WIN);
        matchOutcomeAggregator = new MatchOutcomeAggregator();

        matchOutcomeView = matchOutcomeAggregator.getAggregatedCount(this.matches);

        long fullTimeCount = matchOutcomeView.getAwayTeam().get("winFulltime");
        long halfTimeCount = matchOutcomeView.getAwayTeam().get("winFirstHalftime");

        assertEquals(countOfAwayTeamWinMatches, fullTimeCount);
        assertEquals(countOfAwayTeamWinMatches, halfTimeCount);
    }

    @Test
    public void checkThatCountOfMatchWinAtFullAndHalfTimeIsZeroWhenNoMatches() {

        long expectedCount = 0;
        matchOutcomeAggregator = new MatchOutcomeAggregator();

        matchOutcomeView = matchOutcomeAggregator.getAggregatedCount(this.matches);

        long fullTimeCountAwayTeam = matchOutcomeView.getAwayTeam().get("winFulltime");
        long halfTimeCountAwayTeam = matchOutcomeView.getAwayTeam().get("winFirstHalftime");

        long fullTimeCountHomeTeam = matchOutcomeView.getHomeTeam().get("winFulltime");
        long halfTimeCountHomeTeam = matchOutcomeView.getHomeTeam().get("winFirstHalftime");

        long fullTimeCountDraw = matchOutcomeView.getDraw().get("winFulltime");
        long halfTimeCountDraw = matchOutcomeView.getDraw().get("winFirstHalftime");

        assertEquals(expectedCount, fullTimeCountAwayTeam);
        assertEquals(expectedCount, halfTimeCountAwayTeam);
        assertEquals(expectedCount, fullTimeCountHomeTeam);
        assertEquals(expectedCount, halfTimeCountHomeTeam);
        assertEquals(expectedCount, fullTimeCountDraw);
        assertEquals(expectedCount, halfTimeCountDraw);
    }

    private void createMatches(long numberOfMatchesToCreate, String halfTimeAndFinalOutcome) {

        for(int i=0; i < numberOfMatchesToCreate; i++) {

            Match match = new Match();

            switch (halfTimeAndFinalOutcome) {

                case "H":
                    match.setFinalOutcome(HOME_TEAM_WIN);
                    match.setHalfTimeOutcome(HOME_TEAM_WIN);
                    break;
                case "D":
                    match.setFinalOutcome(DRAW);
                    match.setHalfTimeOutcome(DRAW);
                    break;
                case "A":
                    match.setFinalOutcome(AWAY_TEAM_WIN);
                    match.setHalfTimeOutcome(AWAY_TEAM_WIN);
                    break;
            }

            matches.add(match);

        }

    }


}
