import aggregator.NumberOfGoalsAggregator;
import model.Match;
import org.junit.Before;
import org.junit.Test;
import viewmodel.NumberOfGoalsModel;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class NumberOfGoalsAggregatorTest {

    private NumberOfGoalsAggregator numberOfGoalsAggregator;
    private NumberOfGoalsModel numberOfGoalsView;
    private List<Match> matches;
    private int expectedCount;
    private int numberOfGoalsScoredOnMatch;

    @Before
    public void setUp() {
        this.matches = new ArrayList<>();
    }

    @Test
    public void countOfNoGoalsIsCorrectWhenNoGoalsAreScored() {

        expectedCount = 9;
        numberOfGoalsScoredOnMatch = 0;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        long noGoals = numberOfGoalsView.getBothTeams().get("noGoals");
        long noGoalsHalfTime = numberOfGoalsView.getBothTeams().get("noGoalsHalfTime");

        assertEquals(expectedCount, noGoals);
        assertEquals(expectedCount, noGoalsHalfTime);


    }

    @Test
    public void countOfOneGoalIsCorrectWhenOneGoalIsScored() {

        expectedCount = 3;
        numberOfGoalsScoredOnMatch = 1;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        long oneGoal = numberOfGoalsView.getBothTeams().get("oneGoal");
        long oneGoalHalfTime = numberOfGoalsView.getBothTeams().get("oneGoalHalfTime");

        assertEquals(expectedCount, oneGoal);
        assertEquals(expectedCount, oneGoalHalfTime);

    }

    @Test
    public void countOfTwoGoalsIsCorrectWhenTwoGoalsAreScored() {

        expectedCount = 6;
        numberOfGoalsScoredOnMatch = 2;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        long twoGoal = numberOfGoalsView.getBothTeams().get("twoGoals");
        long twoGoalHalfTime = numberOfGoalsView.getBothTeams().get("twoGoalsHalfTime");

        assertEquals(expectedCount, twoGoal);
        assertEquals(expectedCount, twoGoalHalfTime);

    }

    @Test
    public void countOfThreeGoalsIsCorrectWhenThreeGoalsAreScored() {

        expectedCount = 1;
        numberOfGoalsScoredOnMatch = 3;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        long threeGoals = numberOfGoalsView.getBothTeams().get("threeGoals");
        long threeGoalsHalfTime = numberOfGoalsView.getBothTeams().get("threeGoalsHalfTime");

        assertEquals(expectedCount, threeGoals);
        assertEquals(expectedCount, threeGoalsHalfTime);


    }

    @Test
    public void countOfFourOrMoreGoalsIsCorrectWhenFourOrMoreGoalsAreScored() {

        expectedCount = 5;
        numberOfGoalsScoredOnMatch = 4;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        long fourOrMoreGoals = numberOfGoalsView.getBothTeams().get("fourOrMoreGoals");
        long fourOrMoreGoalsHalfTime = numberOfGoalsView.getBothTeams().get("fourOrMoreGoalsHalfTime");

        assertEquals(expectedCount, fourOrMoreGoals);
        assertEquals(expectedCount, fourOrMoreGoalsHalfTime);


    }

    @Test
    public void countIsZeroWhenNoGoalsOnMatch() {

        long numberOfMatchesToAdd = 2;
        expectedCount = 0;
        numberOfGoalsScoredOnMatch = 0;
        createMatches(numberOfMatchesToAdd, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        long noGoals = numberOfGoalsView.getBothTeams().get("noGoals");
        long oneGoal = numberOfGoalsView.getBothTeams().get("oneGoal");
        long twoGoals = numberOfGoalsView.getBothTeams().get("twoGoals");
        long threeGoals = numberOfGoalsView.getBothTeams().get("threeGoals");
        long fourOrMoreGoals = numberOfGoalsView.getBothTeams().get("fourOrMoreGoals");

        assertEquals(numberOfMatchesToAdd, noGoals);
        assertEquals(expectedCount, oneGoal);
        assertEquals(expectedCount, twoGoals);
        assertEquals(expectedCount, threeGoals);
        assertEquals(expectedCount, fourOrMoreGoals);


    }

    private void createMatches(long numberOfMatchesToCreate, int numberOfGoals) {

        for(int i=0; i < numberOfMatchesToCreate; i++) {

            Match match = new Match();

            match.setHomeTeamGoals(numberOfGoals);
            match.setHomeTeamHalftimeGoals(numberOfGoals);

            matches.add(match);

        }

    }


}
