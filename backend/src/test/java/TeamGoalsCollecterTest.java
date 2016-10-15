import collecter.TeamGoalsCollecter;
import collecter.model.TeamGoalsModel;
import model.Match;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TeamGoalsCollecterTest {

    private Match match;
    int homeTeamGoals;
    int awayTeamGoals;
    int totalGoals;
    private TeamGoalsModel teamGoalsModel;

    @Before
    public void setUp() {
        match = new Match();
    }

    @Test
    public void homeTeamScoredIsSetToTrueWhenHomeTeamHasGoals() {

        setUpMatch(3, 0);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isHomeTeamScored());

    }

    @Test
    public void homeTeamScoredIsSetToFalseWhenHomeTeamHasZeroGoals() {

        setUpMatch(0, 2);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isHomeTeamScored());

    }

    @Test
    public void awayTeamScoredIsSetToTrueWhenAwayTeamHasGoals() {

        setUpMatch(1, 2);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isAwayTeamScored());

    }

    @Test
    public void awayTeamScoredIsSetToFalseWhenAwayTeamHasZeroGoals() {

        setUpMatch(1, 0);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isAwayTeamScored());

    }

    @Test
    public void bothTeamsScoredIsSetToTrueWhenBothTeamsHaveGoals() {

        setUpMatch(1, 2);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isHomeTeamScored());
        assertTrue(teamGoalsModel.isAwayTeamScored());
        assertTrue(teamGoalsModel.isBothTeamsScored());

    }

    @Test
    public void bothTeamsScoredIsSetToFalseWhenOnlyOneTeamHasGoals() {

        setUpMatch(1, 0);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isBothTeamsScored());

    }

    @Test
    public void homeTeamHalfTimeScoredIsSetToTrueWhenHomeTeamHasGoalsOnHalftime() {

        setUpMatchHalfTimeGoals(3, 0);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isHomeTeamScoredOnHalfTime());

    }

    @Test
    public void homeTeamHalfTimeScoredIsSetToFalseWhenHomeTeamHasZeroGoalsOnHalftime() {

        setUpMatchHalfTimeGoals(0, 2);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isHomeTeamScoredOnHalfTime());

    }

    @Test
    public void awayTeamHalfTimeScoredIsSetToTrueWhenAwayTeamHasGoalsOnHalftime() {

        setUpMatchHalfTimeGoals(3, 1);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isAwayTeamScoredOnHalfTime());

    }

    @Test
    public void awayTeamHalfTimeScoredIsSetToFalseWhenAwayTeamHasZeroGoalsOnHalftime() {

        setUpMatchHalfTimeGoals(0, 0);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isAwayTeamScoredOnHalfTime());

    }

    @Test
    public void bothTeamsScoredOnHalfTimeIsSetToTrueWhenBothTeamsHaveGoalsOnHalfTime() {

        setUpMatchHalfTimeGoals(2, 2);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isHomeTeamScoredOnHalfTime());
        assertTrue(teamGoalsModel.isAwayTeamScoredOnHalfTime());
        assertTrue(teamGoalsModel.isBothTeamsScoredOnHalfTime());

    }

    @Test
    public void bothTeamsScoredOnHalfTimeIsSetToFalseWhenOnlyOneTeamHasGoalsOnHalfTime() {

        setUpMatchHalfTimeGoals(0, 2);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isBothTeamsScoredOnHalfTime());

    }

    @Test
    public void allPropertiesAreSetToTrueWhenBothTeamsHaveGoalsOnHalfTimeAndEndOfMatch() {

        setUpMatch(1, 3);
        setUpMatchHalfTimeGoals(2, 2);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isHomeTeamScored());
        assertTrue(teamGoalsModel.isAwayTeamScored());
        assertTrue(teamGoalsModel.isBothTeamsScored());
        assertTrue(teamGoalsModel.isHomeTeamScoredOnHalfTime());
        assertTrue(teamGoalsModel.isAwayTeamScoredOnHalfTime());
        assertTrue(teamGoalsModel.isBothTeamsScoredOnHalfTime());

    }

    @Test
    public void allPropertiesAreFalseWhenBothTeamsHaveScoredZeroGoals() {

        setUpMatch(0, 0);
        setUpMatchHalfTimeGoals(0, 0);

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isHomeTeamScored());
        assertFalse(teamGoalsModel.isAwayTeamScored());
        assertFalse(teamGoalsModel.isBothTeamsScored());
        assertFalse(teamGoalsModel.isHomeTeamScoredOnHalfTime());
        assertFalse(teamGoalsModel.isAwayTeamScoredOnHalfTime());
        assertFalse(teamGoalsModel.isBothTeamsScoredOnHalfTime());

    }

    private void setUpMatch(int homeGoals, int awayGoals) {
        homeTeamGoals = homeGoals;
        awayTeamGoals = awayGoals;
        totalGoals = homeTeamGoals + awayTeamGoals;

        match.setHomeTeamGoals(homeTeamGoals);
        match.setAwayTeamGoals(awayTeamGoals);
    }

    private void setUpMatchHalfTimeGoals(int homeHalfTimeGoals, int awayHalfTimeGoals) {
        match.setHomeTeamHalftimeGoals(homeHalfTimeGoals);
        match.setAwayTeamHalftimeGoals(awayHalfTimeGoals);
    }


}
