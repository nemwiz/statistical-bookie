import aggregator.NumberOfGoalsAndWinsAggregator;
import helper.Constants;
import model.Match;
import org.junit.Before;
import org.junit.Test;
import viewmodel.NumberOfGoalsAndWinsView;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class NumberOfGoalsAndWinsAggregatorTest {

    private NumberOfGoalsAndWinsAggregator numberOfGoalsAndWinsAggregator;
    private NumberOfGoalsAndWinsView numberOfGoalsAndWinsView;
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
        numberOfGoalsAndWinsAggregator = new NumberOfGoalsAndWinsAggregator(matches);

        numberOfGoalsAndWinsView = numberOfGoalsAndWinsAggregator.getAggregatedCount();

        assertEquals(expectedCount, numberOfGoalsAndWinsView.getHomeTeamWinAndOneGoalScored());
        assertEquals(expectedCount, numberOfGoalsAndWinsView.getHomeTeamWinAndTwoGoalsScored());
        assertEquals(expectedCount, numberOfGoalsAndWinsView.getHomeTeamWinAndThreeGoalsScored());
        assertFalse(expectedCount == numberOfGoalsAndWinsView.getHomeTeamWinAndFourOrMoreGoalsScored());
    }

    @Test
    public void testAggregationWithMixOfDrawAndAwayMatchesWithOneGoalOnEachMatch() {

        int expectedCountOfDrawMatches = 2;
        int expectedCountOfAwayMatches = 5;
        int numberOfGoalsScoredOnMatch = 1;
        createMatches(expectedCountOfDrawMatches, numberOfGoalsScoredOnMatch, Constants.DRAW);
        createMatches(expectedCountOfAwayMatches, numberOfGoalsScoredOnMatch, Constants.AWAY_TEAM_WIN);
        numberOfGoalsAndWinsAggregator = new NumberOfGoalsAndWinsAggregator(matches);

        numberOfGoalsAndWinsView = numberOfGoalsAndWinsAggregator.getAggregatedCount();

        assertEquals(expectedCountOfDrawMatches, numberOfGoalsAndWinsView.getDrawAndOneGoalScored());
        assertFalse(expectedCountOfDrawMatches == numberOfGoalsAndWinsView.getDrawAndTwoGoalsScored());
        assertEquals(expectedCountOfAwayMatches, numberOfGoalsAndWinsView.getAwayTeamWinAndOneGoalScored());
        assertFalse(expectedCountOfAwayMatches == numberOfGoalsAndWinsView.getAwayTeamWinAndTwoGoalsScored());
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
        numberOfGoalsAndWinsAggregator = new NumberOfGoalsAndWinsAggregator(matches);

        numberOfGoalsAndWinsView = numberOfGoalsAndWinsAggregator.getAggregatedCount();

        assertEquals(expectedCountOfHomeMatches, numberOfGoalsAndWinsView.getHomeTeamWinAndOneGoalScored());
        assertEquals(expectedCountOfDrawMatches, numberOfGoalsAndWinsView.getDrawAndOneGoalScored());
        assertEquals(expectedCountOfAwayMatches, numberOfGoalsAndWinsView.getAwayTeamWinAndOneGoalScored());
        assertEquals(expectedCountOfHomeMatches, numberOfGoalsAndWinsView.getHomeTeamWinAndFourOrMoreGoalsScored());
        assertEquals(expectedCountOfDrawMatches, numberOfGoalsAndWinsView.getDrawAndFourOrMoreGoalsScored());
        assertEquals(expectedCountOfAwayMatches, numberOfGoalsAndWinsView.getAwayTeamWinAndFourOrMoreGoalsScored());
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
