import collecter.TeamGoalsCollecter;
import collecter.model.TeamGoalsModel;
import model.Match;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TeamGoalsCollecterTest {

    private Match match;
    private MatchHelper matchHelper;
    private TeamGoalsModel teamGoalsModel;

    @Before
    public void setUp() {
        match = new Match();
        matchHelper = new MatchHelper(match);
    }

    @Test
    public void homeTeamScoredIsSetToTrueWhenHomeTeamHasGoals() {

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(3),
                MatchHelper.awayTeamGoals(0));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isHomeTeamScored());

    }

    @Test
    public void homeTeamScoredIsSetToFalseWhenHomeTeamHasZeroGoals() {

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(0),
                MatchHelper.awayTeamGoals(2));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isHomeTeamScored());

    }

    @Test
    public void awayTeamScoredIsSetToTrueWhenAwayTeamHasGoals() {

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(1),
                MatchHelper.awayTeamGoals(2));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isAwayTeamScored());

    }

    @Test
    public void awayTeamScoredIsSetToFalseWhenAwayTeamHasZeroGoals() {

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(1),
                MatchHelper.awayTeamGoals(0));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isAwayTeamScored());

    }

    @Test
    public void bothTeamsScoredIsSetToTrueWhenBothTeamsHaveGoals() {

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(1),
                MatchHelper.awayTeamGoals(2));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isHomeTeamScored());
        assertTrue(teamGoalsModel.isAwayTeamScored());
        assertTrue(teamGoalsModel.isBothTeamsScored());

    }

    @Test
    public void bothTeamsScoredIsSetToFalseWhenOnlyOneTeamHasGoals() {

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(1),
                MatchHelper.awayTeamGoals(0));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isBothTeamsScored());

    }

    @Test
    public void homeTeamHalfTimeScoredIsSetToTrueWhenHomeTeamHasGoalsOnHalftime() {

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(3),
                MatchHelper.awayTeamHalfTimeGoals(0));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isHomeTeamScoredOnHalfTime());

    }

    @Test
    public void homeTeamHalfTimeScoredIsSetToFalseWhenHomeTeamHasZeroGoalsOnHalftime() {

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(0),
                MatchHelper.awayTeamHalfTimeGoals(2));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isHomeTeamScoredOnHalfTime());

    }

    @Test
    public void awayTeamHalfTimeScoredIsSetToTrueWhenAwayTeamHasGoalsOnHalftime() {

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(3),
                MatchHelper.awayTeamHalfTimeGoals(1));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isAwayTeamScoredOnHalfTime());

    }

    @Test
    public void awayTeamHalfTimeScoredIsSetToFalseWhenAwayTeamHasZeroGoalsOnHalftime() {

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(0),
                MatchHelper.awayTeamHalfTimeGoals(0));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isAwayTeamScoredOnHalfTime());

    }

    @Test
    public void bothTeamsScoredOnHalfTimeIsSetToTrueWhenBothTeamsHaveGoalsOnHalfTime() {

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(2),
                MatchHelper.awayTeamHalfTimeGoals(2));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertTrue(teamGoalsModel.isHomeTeamScoredOnHalfTime());
        assertTrue(teamGoalsModel.isAwayTeamScoredOnHalfTime());
        assertTrue(teamGoalsModel.isBothTeamsScoredOnHalfTime());

    }

    @Test
    public void bothTeamsScoredOnHalfTimeIsSetToFalseWhenOnlyOneTeamHasGoalsOnHalfTime() {

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(0),
                MatchHelper.awayTeamHalfTimeGoals(2));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isBothTeamsScoredOnHalfTime());

    }

    @Test
    public void allPropertiesAreSetToTrueWhenBothTeamsHaveGoalsOnHalfTimeAndEndOfMatch() {

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(1),
                MatchHelper.awayTeamGoals(3));

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(2),
                MatchHelper.awayTeamHalfTimeGoals(2));

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

        matchHelper.setUpMatchGoals(
                MatchHelper.homeTeamGoals(0),
                MatchHelper.awayTeamGoals(0));

        matchHelper.setUpMatchHalfTimeGoals(
                MatchHelper.homeTeamHalfTimeGoals(0),
                MatchHelper.awayTeamHalfTimeGoals(0));

        teamGoalsModel = TeamGoalsCollecter.getTeamGoalsScored(match);

        assertFalse(teamGoalsModel.isHomeTeamScored());
        assertFalse(teamGoalsModel.isAwayTeamScored());
        assertFalse(teamGoalsModel.isBothTeamsScored());
        assertFalse(teamGoalsModel.isHomeTeamScoredOnHalfTime());
        assertFalse(teamGoalsModel.isAwayTeamScoredOnHalfTime());
        assertFalse(teamGoalsModel.isBothTeamsScoredOnHalfTime());

    }

}
