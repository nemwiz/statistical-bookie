import aggregator.GoalsAggregator;
import model.Match;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class GoalsAggregatorTest {

    private Match match;
    int homeTeamGoals;
    int awayTeamGoals;
    int totalGoals;

    @Before
    public void setUp() {
        match = new Match();
    }

    @Test
    public void getSumOfGoals() {

        setUpMatch(3, 2);

        int sumOfGoals = GoalsAggregator.sumGoals(match);

        assertEquals(sumOfGoals, totalGoals);
    }

    @Test
    public void getSumOfGoalsWhenNoGoals() {

        setUpMatch(0, 0);

        int sumOfGoals = GoalsAggregator.sumGoals(match);

        assertEquals(sumOfGoals, totalGoals);
    }

    @Test
    public void getAggregatedGoalsForAMatch() {

        int homeTeamGoals = 2;
        int awayTeamGoals = 1;
        setUpMatch(homeTeamGoals, awayTeamGoals);

        List<Integer> aggregatedGoals = GoalsAggregator.aggregateGoals(match);

        long goalsAggregation = aggregatedGoals
                .stream()
                .filter(num -> num == 1)
                .count();

        long nonGoalAggregation = aggregatedGoals
                .stream()
                .filter(num -> num == 0)
                .count();

        assertEquals(homeTeamGoals + awayTeamGoals, goalsAggregation);
        assertEquals(1, nonGoalAggregation);

    }

    @Test
    public void getAggregatedGoalsForAMatchWithoutGoals() {

        setUpMatch(0, 0);

        List<Integer> aggregatedGoals = GoalsAggregator.aggregateGoals(match);

        long goalsAggregation = aggregatedGoals
                .stream()
                .filter(num -> num == 1)
                .count();

        assertEquals(goalsAggregation, 0);

    }

    private void setUpMatch(int homeGoals, int awayGoals) {
        homeTeamGoals = homeGoals;
        awayTeamGoals = awayGoals;
        totalGoals = homeTeamGoals + awayTeamGoals;

        match.setHomeTeamGoals(homeTeamGoals);
        match.setAwayTeamGoals(awayTeamGoals);
    }

}
