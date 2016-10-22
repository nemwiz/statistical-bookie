package collecter;

import collecter.model.TeamGoalsModel;
import model.Match;

public class TeamGoalsCollecter {

    public static TeamGoalsModel getTeamGoalsScored(Match match) {

        return new TeamGoalsModel(
                isHomeTeamScored(match),
                isAwayTeamScored(match),
                isBothTeamsScored(match),
                isHomeTeamScoredOnHalfTime(match),
                isAwayTeamScoredOnHalfTime(match),
                isBothTeamsScoredOnHalfTime(match)
        );
    }

    public static boolean isBothTeamsScoredOnHalfTime(Match match) {
        return match.getHomeTeamHalftimeGoals() > 0 && match.getAwayTeamHalftimeGoals() > 0;
    }

    public static boolean isAwayTeamScoredOnHalfTime(Match match) {
        return match.getAwayTeamHalftimeGoals() > 0;
    }

    public static boolean isHomeTeamScoredOnHalfTime(Match match) {
        return match.getHomeTeamHalftimeGoals() > 0;
    }

    public static boolean isBothTeamsScored(Match match) {
        return match.getHomeTeamGoals() > 0 && match.getAwayTeamGoals() > 0;
    }

    public static boolean isAwayTeamScored(Match match) {
        return match.getAwayTeamGoals() > 0;
    }

    public static boolean isHomeTeamScored(Match match) {
        return match.getHomeTeamGoals() > 0;
    }

}
