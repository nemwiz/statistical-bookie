package collecter;

import collecter.model.NumberOfGoalsModel;
import helper.Constants;
import model.Match;

public class NumberOfGoalsCollecter {

    private static final int GOALS_PER_MATCH_LIMIT = 4;

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

    public static NumberOfGoalsModel getNumberOfGoals(Match match) {

        int totalGoals = sumGoalsFullTime(match);
        int totalGoalsHalfTime = sumGoalsHalfTime(match);
        int totalGoalsSecondHalfTime = sumGoalsInSecondHalfTime(match);

        return getNumberOfGoalsModel(totalGoals, totalGoalsHalfTime, totalGoalsSecondHalfTime);
    }

    public static NumberOfGoalsModel getNumberOfGoalsPerTeam(Match match, String team) {

        int totalGoalsPerTeam = 0;
        int totalGoalsHalfTime = 0;
        int totalGoalsSecondHalfTime = 0;

        if (team.equals(Constants.HOME_TEAM)) {
            totalGoalsPerTeam = sumGoalsOfHomeTeam(match);
            totalGoalsHalfTime = match.getHomeTeamHalftimeGoals();
            totalGoalsSecondHalfTime = getHomeTeamGoalsScoredInSecondHalfTime(match);
        } else if (team.equals(Constants.AWAY_TEAM)){
            totalGoalsPerTeam = sumGoalsOfAwayTeam(match);
            totalGoalsHalfTime = match.getAwayTeamHalftimeGoals();
            totalGoalsSecondHalfTime = getAwayTeamGoalsScoredInSecondHalfTime(match);
        }

        return getNumberOfGoalsModel(totalGoalsPerTeam, totalGoalsHalfTime, totalGoalsSecondHalfTime);

    }

    private static NumberOfGoalsModel getNumberOfGoalsModel(int totalGoals, int totalGoalsHalfTime, int totalGoalsSecondHalfTime) {
        boolean[] isGoalsScoredFullTime = new boolean[GOALS_PER_MATCH_LIMIT];
        boolean[] isGoalsScoredHalfTime = new boolean[GOALS_PER_MATCH_LIMIT];
        boolean[] isGoalsScoredInSecondHalfTime = new boolean[GOALS_PER_MATCH_LIMIT];

        checkIfCertainNumberOfGoalsIsScored(totalGoals, isGoalsScoredFullTime);
        checkIfCertainNumberOfGoalsIsScored(totalGoalsHalfTime, isGoalsScoredHalfTime);
        checkIfCertainNumberOfGoalsIsScored(totalGoalsSecondHalfTime, isGoalsScoredInSecondHalfTime);

        return mapArraysToModel(
                isGoalsScoredFullTime,
                isGoalsScoredHalfTime,
                isGoalsScoredInSecondHalfTime);
    }

    private static void checkIfCertainNumberOfGoalsIsScored(int totalGoals, boolean[] isGoalsScored) {
        for (int i = 1; i <= GOALS_PER_MATCH_LIMIT; i++) {

            if (totalGoals >= i) {
                isGoalsScored[i - 1] = true;
            }

        }
    }

    private static NumberOfGoalsModel mapArraysToModel(
            boolean[] isGoalsScoredFullTime,
            boolean[] isGoalsScoredHalfTime,
            boolean[] isGoalsScoredInSecondHalfTime
    ) {

        return new NumberOfGoalsModel(
                isGoalsScoredFullTime[0],
                isGoalsScoredFullTime[1],
                isGoalsScoredFullTime[2],
                isGoalsScoredFullTime[3],
                isGoalsScoredHalfTime[0],
                isGoalsScoredHalfTime[1],
                isGoalsScoredHalfTime[2],
                isGoalsScoredHalfTime[3],
                isGoalsScoredInSecondHalfTime[0],
                isGoalsScoredInSecondHalfTime[1],
                isGoalsScoredInSecondHalfTime[2],
                isGoalsScoredInSecondHalfTime[3]
        );

    }

}
