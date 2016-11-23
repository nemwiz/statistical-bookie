package collecter;

import collecter.model.NumberOfGoalsModel;
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
