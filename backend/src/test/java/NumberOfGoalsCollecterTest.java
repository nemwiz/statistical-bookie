import collecter.NumberOfGoalsCollecter;
import collecter.model.NumberOfGoalsModel;
import model.Match;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class NumberOfGoalsCollecterTest {

    private Match match;
    private MatchHelper matchHelper;
    private NumberOfGoalsModel numberOfGoalsModel;

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
    public void isOneGoalScoredIsSetToTrueAndOtherPropertiesToFalseWhenOnlyOneGoalIsScored() {

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(0),
                MatchHelper.awayTeamGoals(1));

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScored());
        assertFalse(numberOfGoalsModel.isTwoGoalsScored());
        assertFalse(numberOfGoalsModel.isThreeGoalsScored());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScored());

    }

    @Test
    public void isTwoGoalScoredIsSetToTrueAndPropertiesAfterToFalseWhenTwoGoalsAreScored() {

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(1),
                MatchHelper.awayTeamGoals(1));

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScored());
        assertTrue(numberOfGoalsModel.isTwoGoalsScored());
        assertFalse(numberOfGoalsModel.isThreeGoalsScored());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScored());

    }

    @Test
    public void isThreeGoalsScoredIsSetToTrueAndPropertiesAfterToFalseWhenThreeGoalsAreScored() {

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(2),
                MatchHelper.awayTeamGoals(1));

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScored());
        assertTrue(numberOfGoalsModel.isTwoGoalsScored());
        assertTrue(numberOfGoalsModel.isThreeGoalsScored());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScored());

    }

    @Test
    public void allPropertiesAreSetToTrueWhenFourOrMoreGoalsAreScored() {

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(2),
                MatchHelper.awayTeamGoals(3));

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScored());
        assertTrue(numberOfGoalsModel.isTwoGoalsScored());
        assertTrue(numberOfGoalsModel.isThreeGoalsScored());
        assertTrue(numberOfGoalsModel.isFourOrMoreGoalsScored());

    }

    @Test
    public void isOneGoalScoredOnHalfTimeIsSetToTrueAndOtherPropertiesToFalseWhenOnlyOneGoalIsScoredOnHalfTime() {

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(0),
                MatchHelper.awayTeamHalfTimeGoals(1));

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScoredOnHalfTime());
        assertFalse(numberOfGoalsModel.isTwoGoalsScoredOnHalfTime());
        assertFalse(numberOfGoalsModel.isThreeGoalsScoredOnHalfTime());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScoredOnHalfTime());

    }

    @Test
    public void isTwoGoalScoredOnHalfTimeIsSetToTrueAndOtherPropertiesToFalseWhenTwoGoalsAreScoredOnHalfTime() {

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(1),
                MatchHelper.awayTeamHalfTimeGoals(1));

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScoredOnHalfTime());
        assertTrue(numberOfGoalsModel.isTwoGoalsScoredOnHalfTime());
        assertFalse(numberOfGoalsModel.isThreeGoalsScoredOnHalfTime());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScoredOnHalfTime());

    }

    @Test
    public void isThreeGoalScoredOnHalfTimeIsSetToTrueAndOtherPropertiesToFalseWhenThreeGoalsAreScoredOnHalfTime() {

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(3),
                MatchHelper.awayTeamHalfTimeGoals(0));

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScoredOnHalfTime());
        assertTrue(numberOfGoalsModel.isTwoGoalsScoredOnHalfTime());
        assertTrue(numberOfGoalsModel.isThreeGoalsScoredOnHalfTime());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScoredOnHalfTime());

    }

    @Test
    public void isFourOrMoreGoalsScoredOnHalfTimeIsSetToTrueAndOtherPropertiesToFalseWhenFourOrMoreGoalsAreScoredOnHalfTime() {

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(3),
                MatchHelper.awayTeamHalfTimeGoals(2));

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScoredOnHalfTime());
        assertTrue(numberOfGoalsModel.isTwoGoalsScoredOnHalfTime());
        assertTrue(numberOfGoalsModel.isThreeGoalsScoredOnHalfTime());
        assertTrue(numberOfGoalsModel.isFourOrMoreGoalsScoredOnHalfTime());

    }

    @Test
    public void allHalfTimePropertiesAreFalseWhenNumberOfGoalsOnHalfTimeIsZero() {

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(0),
                MatchHelper.awayTeamHalfTimeGoals(0));

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertFalse(numberOfGoalsModel.isOneGoalsScoredOnHalfTime());
        assertFalse(numberOfGoalsModel.isTwoGoalsScoredOnHalfTime());
        assertFalse(numberOfGoalsModel.isThreeGoalsScoredOnHalfTime());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScoredOnHalfTime());
    }

    @Test
    public void allPropertiesAreSetToFalseWhenNumberOfGoalsIsZero() {

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(0),
                MatchHelper.awayTeamGoals(0));

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertFalse(numberOfGoalsModel.isOneGoalsScored());
        assertFalse(numberOfGoalsModel.isTwoGoalsScored());
        assertFalse(numberOfGoalsModel.isThreeGoalsScored());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScored());

    }

    @Test
    public void goalsOnHalfTimeAlsoAffectFullTimeGoalProperties() {

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(1),
                MatchHelper.awayTeamHalfTimeGoals(1));

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(2),
                MatchHelper.awayTeamGoals(1));

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScoredOnHalfTime());
        assertTrue(numberOfGoalsModel.isTwoGoalsScoredOnHalfTime());
        assertFalse(numberOfGoalsModel.isThreeGoalsScoredOnHalfTime());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScoredOnHalfTime());
        assertTrue(numberOfGoalsModel.isOneGoalsScored());
        assertTrue(numberOfGoalsModel.isTwoGoalsScored());
        assertTrue(numberOfGoalsModel.isThreeGoalsScored());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScored());
    }

}
