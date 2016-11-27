import aggregator.NumberOfGoalsAggregator;
import model.Match;
import org.junit.Before;
import org.junit.Test;
import viewmodel.NumberOfGoalsMetaView;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class NumberOfGoalsAggregatorTest {

    private NumberOfGoalsAggregator numberOfGoalsAggregator;
    private NumberOfGoalsMetaView numberOfGoalsView;
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

        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getNoGoals());
        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getNoGoalsHalfTime());


    }

    @Test
    public void countOfOneGoalIsCorrectWhenOneGoalIsScored() {

        expectedCount = 3;
        numberOfGoalsScoredOnMatch = 1;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getOneGoal());
        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getOneGoalHalfTime());

    }

    @Test
    public void countOfTwoGoalsIsCorrectWhenTwoGoalsAreScored() {

        expectedCount = 6;
        numberOfGoalsScoredOnMatch = 2;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getTwoGoals());
        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getTwoGoalsHalfTime());

    }

    @Test
    public void countOfThreeGoalsIsCorrectWhenThreeGoalsAreScored() {

        expectedCount = 1;
        numberOfGoalsScoredOnMatch = 3;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getThreeGoals());
        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getThreeGoalsHalfTime());


    }

    @Test
    public void countOfFourOrMoreGoalsIsCorrectWhenFourOrMoreGoalsAreScored() {

        expectedCount = 5;
        numberOfGoalsScoredOnMatch = 4;
        createMatches(expectedCount, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getFourOrMoreGoals());
        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getFourOrMoreGoalsHalfTime());


    }

    @Test
    public void countIsZeroWhenNoGoalsOnMatch() {

        long numberOfMatchesToAdd = 2;
        expectedCount = 0;
        numberOfGoalsScoredOnMatch = 0;
        createMatches(numberOfMatchesToAdd, numberOfGoalsScoredOnMatch);
        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);

        numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();

        assertEquals(numberOfMatchesToAdd, numberOfGoalsView.getBothTeams().getNoGoals());
        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getOneGoal());
        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getTwoGoalsHalfTime());
        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getThreeGoals());
        assertEquals(expectedCount, numberOfGoalsView.getBothTeams().getFourOrMoreGoalsHalfTime());


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
