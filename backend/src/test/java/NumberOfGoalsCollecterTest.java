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
    private NumberOfGoalsModel numberOfGoalsModel;
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

        int sumOfGoals = NumberOfGoalsCollecter.sumGoals(match);

        assertEquals(sumOfGoals, totalGoals);
    }

    @Test
    public void getSumOfGoalsWhenNoGoals() {

        setUpMatch(0, 0);

        int sumOfGoals = NumberOfGoalsCollecter.sumGoals(match);

        assertEquals(sumOfGoals, totalGoals);
    }

    @Test
    public void isOneGoalScoredIsSetToTrueAndOtherPropertiesToFalseWhenOnlyOneGoalIsScored() {

        setUpMatch(0, 1);

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScored());
        assertFalse(numberOfGoalsModel.isTwoGoalsScored());
        assertFalse(numberOfGoalsModel.isThreeGoalsScored());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScored());

    }

    @Test
    public void isTwoGoalScoredIsSetToTrueAndPropertiesAfterToFalseWhenTwoGoalsAreScored() {

        setUpMatch(1, 1);

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScored());
        assertTrue(numberOfGoalsModel.isTwoGoalsScored());
        assertFalse(numberOfGoalsModel.isThreeGoalsScored());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScored());

    }

    @Test
    public void isThreeGoalsScoredIsSetToTrueAndPropertiesAfterToFalseWhenThreeGoalsAreScored() {

        setUpMatch(2, 1);

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScored());
        assertTrue(numberOfGoalsModel.isTwoGoalsScored());
        assertTrue(numberOfGoalsModel.isThreeGoalsScored());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScored());

    }

    @Test
    public void allPropertiesAreSetToTrueWhenFourOrMoreGoalsAreScored() {

        setUpMatch(2, 3);

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertTrue(numberOfGoalsModel.isOneGoalsScored());
        assertTrue(numberOfGoalsModel.isTwoGoalsScored());
        assertTrue(numberOfGoalsModel.isThreeGoalsScored());
        assertTrue(numberOfGoalsModel.isFourOrMoreGoalsScored());

    }

    @Test
    public void allPropertiesAreSetToFalseWhenNumberOfGoalsIsZero() {

        setUpMatch(0, 0);

        numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        assertFalse(numberOfGoalsModel.isOneGoalsScored());
        assertFalse(numberOfGoalsModel.isTwoGoalsScored());
        assertFalse(numberOfGoalsModel.isThreeGoalsScored());
        assertFalse(numberOfGoalsModel.isFourOrMoreGoalsScored());

    }


    private void setUpMatch(int homeGoals, int awayGoals) {
        homeTeamGoals = homeGoals;
        awayTeamGoals = awayGoals;
        totalGoals = homeTeamGoals + awayTeamGoals;

        match.setHomeTeamGoals(homeTeamGoals);
        match.setAwayTeamGoals(awayTeamGoals);
    }

}
