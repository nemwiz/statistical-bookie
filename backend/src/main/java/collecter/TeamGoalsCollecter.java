package collecter;

import collecter.model.TeamGoalsModel;
import model.Match;

public class TeamGoalsCollecter {

    private static final int ZERO_GOALS = 0;

    public static TeamGoalsModel getTeamGoalsScored(Match match) {

        return new TeamGoalsModel(
                isHomeTeamScored(match),
                isAwayTeamScored(match),
                isBothTeamsScored(match),
                isHomeTeamScoredOnHalfTime(match),
                isAwayTeamScoredOnHalfTime(match),
                isBothTeamsScoredOnHalfTime(match),
                isHomeTeamScoredInSecondHalfTime(match),
                isAwayTeamScoredInSecondHalfTime(match),
                isBothTeamsScoredInSecondHalfTime(match)
        );
    }

    public static boolean isHomeTeamScoredOnHalfTime(Match match) {
        return match.getHomeTeamHalftimeGoals() > ZERO_GOALS;
    }

    public static boolean isAwayTeamScoredOnHalfTime(Match match) {
        return match.getAwayTeamHalftimeGoals() > ZERO_GOALS;
    }

    public static boolean isBothTeamsScoredOnHalfTime(Match match) {
        return match.getHomeTeamHalftimeGoals() > ZERO_GOALS && match.getAwayTeamHalftimeGoals() > ZERO_GOALS;
    }

    public static boolean isHomeTeamScored(Match match) {
        return match.getHomeTeamGoals() > ZERO_GOALS;
    }

    public static boolean isAwayTeamScored(Match match) {
        return match.getAwayTeamGoals() > ZERO_GOALS;
    }

    public static boolean isBothTeamsScored(Match match) {
        return match.getHomeTeamGoals() > ZERO_GOALS && match.getAwayTeamGoals() > ZERO_GOALS;
    }

    public static boolean isHomeTeamScoredInSecondHalfTime(Match match) {
        return NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match) > ZERO_GOALS;
    }

    public static boolean isAwayTeamScoredInSecondHalfTime(Match match) {
        return NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match) > ZERO_GOALS;
    }

    public static boolean isBothTeamsScoredInSecondHalfTime(Match match) {
        return NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match) > ZERO_GOALS
                && NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match) > ZERO_GOALS;
    }

}
