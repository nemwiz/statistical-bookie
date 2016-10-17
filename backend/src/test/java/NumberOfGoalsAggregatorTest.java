import aggregator.NumberOfGoalsAggregator;
import model.Match;
import org.junit.Before;
import org.junit.Test;
import viewmodel.NumberOfGoalsView;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class NumberOfGoalsAggregatorTest {

    private NumberOfGoalsAggregator numberOfGoalsAggregator;
    private NumberOfGoalsView numberOfGoalsView;
    private List<Match> matches;
    private long expectedCount;
    private int numberOfGoalsScoredOnMatch;

    @Before
    public void setUp() {
        this.matches = new ArrayList<>();
    }

    @Test
    public void countOfOneGoalIsCorrectWhenOneGoalIsScored() {

        expectedCount = 3;
        numberOfGoalsScoredOnMatch = 1;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        assertEquals(expectedCount, numberOfGoalsView.getOneGoal());
        assertEquals(expectedCount, numberOfGoalsView.getOneGoalHalfTime());

    }

    @Test
    public void countOfTwoGoalsIsCorrectWhenTwoGoalsAreScored() {

        expectedCount = 6;
        numberOfGoalsScoredOnMatch = 2;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        assertEquals(expectedCount, numberOfGoalsView.getTwoGoals());
        assertEquals(expectedCount, numberOfGoalsView.getTwoGoalsHalfTime());

    }

    @Test
    public void countOfThreeGoalsIsCorrectWhenThreeGoalsAreScored() {

        expectedCount = 1;
        numberOfGoalsScoredOnMatch = 3;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        assertEquals(expectedCount, numberOfGoalsView.getThreeGoals());
        assertEquals(expectedCount, numberOfGoalsView.getThreeGoalsHalfTime());


    }

    @Test
    public void countOfFourOrMoreGoalsIsCorrectWhenFourOrMoreGoalsAreScored() {

        expectedCount = 5;
        numberOfGoalsScoredOnMatch = 4;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        assertEquals(expectedCount, numberOfGoalsView.getFourOrMoreGoals());
        assertEquals(expectedCount, numberOfGoalsView.getFourOrMoreGoalsHalfTime());


    }

    @Test
    public void countIsZeroWhenNoGoalsOnMatch() {

        long numberOfMatchesToAdd = 2;
        expectedCount = 0;
        numberOfGoalsScoredOnMatch = 0;
        createMatches(numberOfMatchesToAdd, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        assertEquals(expectedCount, numberOfGoalsView.getOneGoal());
        assertEquals(expectedCount, numberOfGoalsView.getTwoGoalsHalfTime());
        assertEquals(expectedCount, numberOfGoalsView.getThreeGoals());
        assertEquals(expectedCount, numberOfGoalsView.getFourOrMoreGoalsHalfTime());


    }

    private void createMatches(long numberOfMatchesToAdd, int numberOfGoals) {

        for(int i=0; i < numberOfMatchesToAdd; i++) {

            Match match = new Match();

            match.setHomeTeamGoals(numberOfGoals);
            match.setHomeTeamHalftimeGoals(numberOfGoals);

            matches.add(match);

        }

    }


}
