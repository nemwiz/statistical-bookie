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

    public static NumberOfGoalsModel getNumberOfGoals(Match match) {

        int totalGoals = sumGoalsFullTime(match);
        int totalGoalsHalfTime = sumGoalsHalfTime(match);
        boolean[] isGoalsScoredFullTime = new boolean[GOALS_PER_MATCH_LIMIT];
        boolean[] isGoalsScoredHalfTime = new boolean[GOALS_PER_MATCH_LIMIT];

        checkIfCertainNumberOfGoalsIsScored(totalGoals, isGoalsScoredFullTime);
        checkIfCertainNumberOfGoalsIsScored(totalGoalsHalfTime, isGoalsScoredHalfTime);

        return mapArraysToModel(isGoalsScoredFullTime, isGoalsScoredHalfTime);

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
            boolean[] isGoalsScoredHalfTime
    ) {

        return new NumberOfGoalsModel(
                isGoalsScoredFullTime[0],
                isGoalsScoredFullTime[1],
                isGoalsScoredFullTime[2],
                isGoalsScoredFullTime[3],
                isGoalsScoredHalfTime[0],
                isGoalsScoredHalfTime[1],
                isGoalsScoredHalfTime[2],
                isGoalsScoredHalfTime[3]
        );

    }

}
