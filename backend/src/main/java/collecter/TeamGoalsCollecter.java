package collecter;

import collecter.model.TeamGoalsModel;
import model.Match;

public class TeamGoalsCollecter {

    public static TeamGoalsModel getTeamGoalsScored(Match match) {

        boolean isHomeTeamScored = match.getHomeTeamGoals() > 0;
        boolean isAwayTeamScored = match.getAwayTeamGoals() > 0;
        boolean isBothTeamsScored =  match.getHomeTeamGoals() > 0 && match.getAwayTeamGoals() > 0;
        boolean isHomeTeamScoredOnHalfTime = match.getHomeTeamHalftimeGoals() > 0;
        boolean isAwayTeamScoredOnHalfTime = match.getAwayTeamHalftimeGoals() > 0;
        boolean isBothTeamsScoredOnHalfTime = match.getHomeTeamHalftimeGoals() > 0 && match.getAwayTeamHalftimeGoals() > 0;

        return new TeamGoalsModel(
                isHomeTeamScored,
                isAwayTeamScored,
                isBothTeamsScored,
                isHomeTeamScoredOnHalfTime,
                isAwayTeamScoredOnHalfTime,
                isBothTeamsScoredOnHalfTime
        );
    }

}
