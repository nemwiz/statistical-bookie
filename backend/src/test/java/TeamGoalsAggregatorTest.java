import aggregator.TeamGoalsAggregator;
import model.Match;
import org.junit.Before;
import org.junit.Test;
import viewmodel.TeamScoredModel;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TeamGoalsAggregatorTest {

    private static int ONLY_HOME_TEAM_SCORED = 0;
    private static int BOTH_TEAMS_SCORED = 1;
    private static int ONLY_AWAY_TEAM_SCORED = 2;

    private TeamGoalsAggregator teamGoalsAggregator;
    private TeamScoredModel teamGoalsView;
    private List<Match> matches;
    private int expectedCount;

    @Before
    public void setUp() {
        matches = new ArrayList<>();
    }

    @Test
    public void countOfHomeTeamScoredIsCorrectWhenOnlyHomeTeamScored() {

        expectedCount = 14;
        createMatches(expectedCount, ONLY_HOME_TEAM_SCORED);
        teamGoalsAggregator = new TeamGoalsAggregator();

        teamGoalsView = teamGoalsAggregator.getAggregatedCount(this.matches);

        long countOfHomeTeamScored = teamGoalsView.getHomeTeam().get("scoredInTheWholeGame");
        long countOfHomeTeamScoredInFirstHalftime = teamGoalsView.getHomeTeam().get("scoredInFirstHalftime");

        assertEquals(expectedCount, countOfHomeTeamScored);
        assertEquals(expectedCount, countOfHomeTeamScoredInFirstHalftime);
    }

    @Test
    public void countOfAwayTeamScoredIsZeroWhenOnlyHomeTeamScored() {

        expectedCount = 0;
        int numberOfMatchesToCreate = 5;
        createMatches(numberOfMatchesToCreate, ONLY_HOME_TEAM_SCORED);
        teamGoalsAggregator = new TeamGoalsAggregator();

        teamGoalsView = teamGoalsAggregator.getAggregatedCount(this.matches);

        long countOfAwayTeamScored = teamGoalsView.getAwayTeam().get("scoredInTheWholeGame");
        long countOfAwayTeamScoredInFirstHalftime = teamGoalsView.getAwayTeam().get("scoredInFirstHalftime");

        assertEquals(expectedCount, countOfAwayTeamScored);
        assertEquals(expectedCount, countOfAwayTeamScoredInFirstHalftime);
    }

    @Test
    public void countOfBothTeamsScoredIsCorrectWhenBothTeamsHaveScored() {

        expectedCount = 3;
        createMatches(expectedCount, BOTH_TEAMS_SCORED);
        teamGoalsAggregator = new TeamGoalsAggregator();

        teamGoalsView = teamGoalsAggregator.getAggregatedCount(this.matches);

        long countOfBothTeamsScored = teamGoalsView.getBothTeams().get("scoredInTheWholeGame");
        long countOfBothTeamsScoredInFirstHalftime = teamGoalsView.getBothTeams().get("scoredInFirstHalftime");

        assertEquals(expectedCount, countOfBothTeamsScored);
        assertEquals(expectedCount, countOfBothTeamsScoredInFirstHalftime);
    }

    @Test
    public void countOfAwayTeamScoredIsCorrectWhenOnlyAwayTeamScored() {

        expectedCount = 5;
        createMatches(expectedCount, ONLY_AWAY_TEAM_SCORED);
        teamGoalsAggregator = new TeamGoalsAggregator();

        teamGoalsView = teamGoalsAggregator.getAggregatedCount(this.matches);

        long countOfAwayTeamScored = teamGoalsView.getAwayTeam().get("scoredInTheWholeGame");
        long countOfAwayTeamScoredInFirstHalftime = teamGoalsView.getAwayTeam().get("scoredInFirstHalftime");

        assertEquals(expectedCount, countOfAwayTeamScored);
        assertEquals(expectedCount, countOfAwayTeamScoredInFirstHalftime);
    }

    @Test
    public void countOfHomeTeamScoredIsZeroWhenOnlyAwayTeamScored() {

        expectedCount = 0;
        int numberOfMatchesToCreate = 3;
        createMatches(numberOfMatchesToCreate, ONLY_AWAY_TEAM_SCORED);
        teamGoalsAggregator = new TeamGoalsAggregator();

        teamGoalsView = teamGoalsAggregator.getAggregatedCount(this.matches);

        long countOfHomeTeamScored = teamGoalsView.getHomeTeam().get("scoredInTheWholeGame");
        long countOfHomeTeamScoredInFirstHalftime = teamGoalsView.getHomeTeam().get("scoredInFirstHalftime");

        assertEquals(expectedCount, countOfHomeTeamScored);
        assertEquals(expectedCount, countOfHomeTeamScoredInFirstHalftime);
    }

    @Test
    public void allPropertiesHaveCountWhenBothTeamsHaveScored() {

        expectedCount = 6;
        createMatches(expectedCount, BOTH_TEAMS_SCORED);
        teamGoalsAggregator = new TeamGoalsAggregator();

        teamGoalsView = teamGoalsAggregator.getAggregatedCount(this.matches);

        long countOfHomeTeamScored = teamGoalsView.getHomeTeam().get("scoredInTheWholeGame");
        long countOfHomeTeamScoredInFirstHalftime = teamGoalsView.getHomeTeam().get("scoredInFirstHalftime");

        long countOfAwayTeamScored = teamGoalsView.getAwayTeam().get("scoredInTheWholeGame");
        long countOfAwayTeamScoredInFirstHalftime = teamGoalsView.getAwayTeam().get("scoredInFirstHalftime");

        long countOfBothTeamsScored = teamGoalsView.getBothTeams().get("scoredInTheWholeGame");
        long countOfBothTeamsScoredInFirstHalftime = teamGoalsView.getBothTeams().get("scoredInFirstHalftime");

        assertEquals(expectedCount, countOfHomeTeamScored);
        assertEquals(expectedCount, countOfHomeTeamScoredInFirstHalftime);
        assertEquals(expectedCount, countOfAwayTeamScored);
        assertEquals(expectedCount, countOfAwayTeamScoredInFirstHalftime);
        assertEquals(expectedCount, countOfBothTeamsScored);
        assertEquals(expectedCount, countOfBothTeamsScoredInFirstHalftime);
    }

    private void createMatches(int numberOfMatchesToCreate, int outcome) {

        for(int i=0; i < numberOfMatchesToCreate; i++) {

            Match match = new Match();

            switch (outcome) {

                case 0:
                    match.setHomeTeamGoals(numberOfMatchesToCreate);
                    match.setHomeTeamHalftimeGoals(numberOfMatchesToCreate);
                    break;
                case 1:
                    match.setHomeTeamGoals(numberOfMatchesToCreate);
                    match.setHomeTeamHalftimeGoals(numberOfMatchesToCreate);
                    match.setAwayTeamGoals(numberOfMatchesToCreate);
                    match.setAwayTeamHalftimeGoals(numberOfMatchesToCreate);
                    break;
                case 2:
                    match.setAwayTeamGoals(numberOfMatchesToCreate);
                    match.setAwayTeamHalftimeGoals(numberOfMatchesToCreate);
                    break;
            }

            matches.add(match);

        }

    }
}
