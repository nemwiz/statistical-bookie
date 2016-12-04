package collecter;

import model.Match;

public class NumberOfGoalsCollecter {


    public static int sumGoalsFullTime(Match match) {
        return match.getHomeTeamGoals() + match.getAwayTeamGoals();
    }

    public static int sumGoalsHalfTime(Match match) {
        return match.getHomeTeamHalftimeGoals() + match.getAwayTeamHalftimeGoals();
    }

    public static int sumGoalsInSecondHalfTime(Match match) {
        return getHomeTeamGoalsScoredInSecondHalfTime(match) + getAwayTeamGoalsScoredInSecondHalfTime(match);
    }

    public static int sumGoalsOfHomeTeam(Match match) {
        return match.getHomeTeamHalftimeGoals() + getHomeTeamGoalsScoredInSecondHalfTime(match);
    }

    public static int sumGoalsOfAwayTeam(Match match) {
        return match.getAwayTeamHalftimeGoals() + getAwayTeamGoalsScoredInSecondHalfTime(match);
    }

    public static int getAwayTeamGoalsScoredInSecondHalfTime(Match match) {
        return match.getAwayTeamGoals() - match.getAwayTeamHalftimeGoals();
    }

    public static int getHomeTeamGoalsScoredInSecondHalfTime(Match match) {
        return match.getHomeTeamGoals() - match.getHomeTeamHalftimeGoals();
    }

}
