import aggregator.NumberOfGoalsAndWinsAggregator;
import helper.Constants;
import model.Match;
import org.junit.Before;
import org.junit.Test;
import viewmodel.NumberOfGoalsAndWinsModel;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

public class NumberOfGoalsAndWinsAggregatorTest {

    private NumberOfGoalsAndWinsAggregator numberOfGoalsAndWinsAggregator;
    private NumberOfGoalsAndWinsModel numberOfGoalsAndWinsView;
    private List<Match> matches;
    private int expectedCount;

    @Before
    public void setUp() {
        this.matches = new ArrayList<>();
    }

    @Test
    public void testAggregationWithThreeHomeTeamWinMatchesAndThreeGoalsOnEachMatch() {

        expectedCount = 3;
        int numberOfGoalsScoredOnMatch = 3;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch, Constants.HOME_TEAM_WIN);
        numberOfGoalsAndWinsAggregator = new NumberOfGoalsAndWinsAggregator();

        numberOfGoalsAndWinsView = numberOfGoalsAndWinsAggregator.getAggregatedCount(this.matches);

        long winAndOneGoalScored = numberOfGoalsAndWinsView.getHomeTeam().get("winAndOneGoalScored");
        long winAndTwoGoalsScored = numberOfGoalsAndWinsView.getHomeTeam().get("winAndTwoGoalsScored");
        long winAndThreeGoalsScored =numberOfGoalsAndWinsView.getHomeTeam().get("winAndThreeGoalsScored");
        long winAndFourOrMoreGoalsScored = numberOfGoalsAndWinsView.getHomeTeam().get("winAndFourOrMoreGoalsScored");

        assertEquals(expectedCount, winAndOneGoalScored);
        assertEquals(expectedCount, winAndTwoGoalsScored);
        assertEquals(expectedCount, winAndThreeGoalsScored);
        assertFalse(expectedCount == winAndFourOrMoreGoalsScored);
    }

    @Test
    public void testAggregationWithMixOfDrawAndAwayMatchesWithOneGoalOnEachMatch() {

        int expectedCountOfDrawMatches = 2;
        int expectedCountOfAwayMatches = 5;
        int numberOfGoalsScoredOnMatch = 1;
        createMatches(expectedCountOfDrawMatches, numberOfGoalsScoredOnMatch, Constants.DRAW);
        createMatches(expectedCountOfAwayMatches, numberOfGoalsScoredOnMatch, Constants.AWAY_TEAM_WIN);
        numberOfGoalsAndWinsAggregator = new NumberOfGoalsAndWinsAggregator();

        numberOfGoalsAndWinsView = numberOfGoalsAndWinsAggregator.getAggregatedCount(this.matches);

        long drawAndOneGoalScored = numberOfGoalsAndWinsView.getDraw().get("winAndOneGoalScored");
        long drawAndTwoGoalsScored = numberOfGoalsAndWinsView.getDraw().get("winAndTwoGoalsScored");
        long awayWinAndOneGoalScored =numberOfGoalsAndWinsView.getAwayTeam().get("winAndOneGoalScored");
        long awayWinAndTwoGoalsScored = numberOfGoalsAndWinsView.getAwayTeam().get("winAndTwoGoalsScored");


        assertEquals(expectedCountOfDrawMatches, drawAndOneGoalScored);
        assertFalse(expectedCountOfDrawMatches == drawAndTwoGoalsScored);
        assertEquals(expectedCountOfAwayMatches, awayWinAndOneGoalScored);
        assertFalse(expectedCountOfAwayMatches == awayWinAndTwoGoalsScored);
    }

    @Test
    public void testAggregationWithMixOfAllMatchesWithFourOrMoreGoalsOnEachMatch() {

        int expectedCountOfHomeMatches = 8;
        int expectedCountOfDrawMatches = 2;
        int expectedCountOfAwayMatches = 5;
        int numberOfGoalsScoredOnMatch = 6;
        createMatches(expectedCountOfHomeMatches, numberOfGoalsScoredOnMatch, Constants.HOME_TEAM_WIN);
        createMatches(expectedCountOfDrawMatches, numberOfGoalsScoredOnMatch, Constants.DRAW);
        createMatches(expectedCountOfAwayMatches, numberOfGoalsScoredOnMatch, Constants.AWAY_TEAM_WIN);
        numberOfGoalsAndWinsAggregator = new NumberOfGoalsAndWinsAggregator();

        numberOfGoalsAndWinsView = numberOfGoalsAndWinsAggregator.getAggregatedCount(this.matches);

        long winAndOneGoalScored = numberOfGoalsAndWinsView.getHomeTeam().get("winAndOneGoalScored");
        long drawAndOneGoalScored = numberOfGoalsAndWinsView.getDraw().get("winAndOneGoalScored");
        long awayWinAndOneGoalScored =numberOfGoalsAndWinsView.getAwayTeam().get("winAndOneGoalScored");

        long homeWinAndFourOrMoreGoalsScored = numberOfGoalsAndWinsView.getHomeTeam().get("winAndFourOrMoreGoalsScored");
        long drawAndFourOrMoreGoalsScored = numberOfGoalsAndWinsView.getDraw().get("winAndFourOrMoreGoalsScored");
        long awayWinAndFourOrMoreGoalsScored =numberOfGoalsAndWinsView.getAwayTeam().get("winAndFourOrMoreGoalsScored");

        assertEquals(expectedCountOfHomeMatches, winAndOneGoalScored);
        assertEquals(expectedCountOfDrawMatches, drawAndOneGoalScored);
        assertEquals(expectedCountOfAwayMatches, awayWinAndOneGoalScored);
        assertEquals(expectedCountOfHomeMatches, homeWinAndFourOrMoreGoalsScored);
        assertEquals(expectedCountOfDrawMatches, drawAndFourOrMoreGoalsScored);
        assertEquals(expectedCountOfAwayMatches, awayWinAndFourOrMoreGoalsScored);
    }

    private void createMatches(long numberOfMatchesToCreate, int numberOfGoals, String finalOutcome) {

        for(int i=0; i < numberOfMatchesToCreate; i++) {

            Match match = new Match();

            match.setHomeTeamGoals(numberOfGoals);
            match.setFinalOutcome(finalOutcome);

            matches.add(match);

        }

    }
}
