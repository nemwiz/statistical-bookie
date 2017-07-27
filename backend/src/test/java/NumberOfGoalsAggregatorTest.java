import aggregator.NumberOfGoalsAggregator;
import helper.Constants;
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
        numberOfGoalsAggregator = new NumberOfGoalsAggregator();

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount(this.matches, Constants.FULLTIME);

        long noGoals = numberOfGoalsView.getBothTeams().get("noGoals");

        assertEquals(expectedCount, noGoals);

    }

    @Test
    public void countOfOneGoalIsCorrectWhenOneGoalIsScored() {

        expectedCount = 3;
        numberOfGoalsScoredOnMatch = 1;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator();

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount(this.matches, Constants.FULLTIME);

        long oneGoal = numberOfGoalsView.getBothTeams().get("oneGoal");

        assertEquals(expectedCount, oneGoal);

    }

    @Test
    public void countOfTwoGoalsIsCorrectWhenTwoGoalsAreScored() {

        expectedCount = 6;
        numberOfGoalsScoredOnMatch = 2;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator();

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount(this.matches, Constants.FULLTIME);

        long twoGoal = numberOfGoalsView.getBothTeams().get("twoGoals");

        assertEquals(expectedCount, twoGoal);

    }

    @Test
    public void countOfThreeGoalsIsCorrectWhenThreeGoalsAreScored() {

        expectedCount = 1;
        numberOfGoalsScoredOnMatch = 3;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator();

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount(this.matches, Constants.FULLTIME);

        long threeGoals = numberOfGoalsView.getBothTeams().get("threeGoals");

        assertEquals(expectedCount, threeGoals);


    }

    @Test
    public void countOfFourOrMoreGoalsIsCorrectWhenFourOrMoreGoalsAreScored() {

        expectedCount = 5;
        numberOfGoalsScoredOnMatch = 4;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator();

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount(this.matches, Constants.FULLTIME);

        long fourOrMoreGoals = numberOfGoalsView.getBothTeams().get("fourOrMoreGoals");

        assertEquals(expectedCount, fourOrMoreGoals);

    }

    @Test
    public void countIsZeroWhenNoGoalsOnMatch() {

        long numberOfMatchesToAdd = 2;
        expectedCount = 0;
        numberOfGoalsScoredOnMatch = 0;
        createMatches(numberOfMatchesToAdd, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator();

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount(this.matches, Constants.FULLTIME);

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
