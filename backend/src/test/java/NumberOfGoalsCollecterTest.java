import collecter.NumberOfGoalsCollecter;
import model.Match;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class NumberOfGoalsCollecterTest {

    private Match match;
    private MatchHelper matchHelper;

    @Before
    public void setUp() {
        match = new Match();
        matchHelper = new MatchHelper(match);
    }

    @Test
    public void getSumOfGoals() {

        int homeTeamGoals = 3;
        int awayTeamGoals = 2;
        int totalGoals = homeTeamGoals + awayTeamGoals;
        matchHelper.setUpMatchGoals(homeTeamGoals, awayTeamGoals);

        int sumOfGoals = NumberOfGoalsCollecter.sumGoalsFullTime(match);

        assertEquals(sumOfGoals, totalGoals);
    }

    @Test
    public void getSumOfGoalsWhenNoGoals() {

        int homeTeamGoals = 0;
        int awayTeamGoals = 0;
        int totalGoals = homeTeamGoals + awayTeamGoals;
        matchHelper.setUpMatchGoals(homeTeamGoals, awayTeamGoals);

        int sumOfGoals = NumberOfGoalsCollecter.sumGoalsFullTime(match);

        assertEquals(sumOfGoals, totalGoals);
    }

    @Test
    public void getSumOfGoalsOnHalfTime() {

        int homeTeamHalfTimeGoals = 2;
        int awayTeamHalfTimeGoals = 1;
        int totalGoalsHalfTime = homeTeamHalfTimeGoals + awayTeamHalfTimeGoals;
        matchHelper.setUpMatchHalfTimeGoals(homeTeamHalfTimeGoals, awayTeamHalfTimeGoals);

        int sumOfGoalsOnHalfTime = NumberOfGoalsCollecter.sumGoalsHalfTime(match);

        assertEquals(sumOfGoalsOnHalfTime, totalGoalsHalfTime);
    }

    @Test
    public void getSumOfGoalsOnHalfTimeWhenNoGoals() {

        int homeTeamHalfTimeGoals = 0;
        int awayTeamHalfTimeGoals = 0;
        int totalGoalsHalfTime = homeTeamHalfTimeGoals + awayTeamHalfTimeGoals;
        matchHelper.setUpMatchHalfTimeGoals(homeTeamHalfTimeGoals, awayTeamHalfTimeGoals);

        int sumOfGoalsOnHalfTime = NumberOfGoalsCollecter.sumGoalsHalfTime(match);

        assertEquals(sumOfGoalsOnHalfTime, totalGoalsHalfTime);
    }

    @Test
    public void homeTeamGoalsInSecondHalfTime() {

        int homeTeamHalfTimeGoals = 3;
        int homeTeamFullTimeGoals = 6;
        matchHelper.setUpMatchHalfTimeGoals(homeTeamHalfTimeGoals, 0);
        matchHelper.setUpMatchGoals(homeTeamFullTimeGoals, 0);

        int numberOfGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match);

        assertEquals(numberOfGoalsScoredInSecondHalfTime, homeTeamFullTimeGoals - homeTeamHalfTimeGoals);
    }

    @Test
    public void getSumOfGoalsInSecondHalfTime() {

        int homeTeamHalfTimeGoals = 2;
        int homeTeamFullTimeGoals = 5;
        int awayTeamFullTimeGoals = 3;
        matchHelper.setUpMatchHalfTimeGoals(homeTeamHalfTimeGoals, 0);
        matchHelper.setUpMatchGoals(homeTeamFullTimeGoals, awayTeamFullTimeGoals);

        int numberOfGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.sumGoalsInSecondHalfTime(match);
        int secondHalfTimeGoals = (homeTeamFullTimeGoals - homeTeamHalfTimeGoals) + awayTeamFullTimeGoals;

        assertEquals(numberOfGoalsScoredInSecondHalfTime, secondHalfTimeGoals);
    }

    @Test
    public void awayTeamGoalsInSecondHalfTime() {

        int awayTeamHalfTimeGoals = 12;
        int awayTeamFullTimeGoals = 16;
        matchHelper.setUpMatchHalfTimeGoals(0, awayTeamHalfTimeGoals);
        matchHelper.setUpMatchGoals(0, awayTeamFullTimeGoals);

        int numberOfGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match);

        assertEquals(numberOfGoalsScoredInSecondHalfTime, awayTeamFullTimeGoals - awayTeamHalfTimeGoals);
    }

}
