package collecter;

import collecter.model.NumberOfGoalsModel;
import model.Match;

public class NumberOfGoalsCollecter {

    private static final int GOALS_PER_MATCH_LIMIT = 4;

    public static int sumGoals(Match match) {
        return match.getHomeTeamGoals() + match.getAwayTeamGoals();
    }

    public static int sumGoalsOnHalfTime(Match match) {
        return match.getHomeTeamHalftimeGoals() + match.getAwayTeamHalftimeGoals();
    }

    public static NumberOfGoalsModel getNumberOfGoals(Match match) {

        int totalGoals = sumGoals(match);
        boolean[] isGoalsScoredArray = new boolean[GOALS_PER_MATCH_LIMIT];

        for (int i = 1; i <= GOALS_PER_MATCH_LIMIT; i++) {

            if (totalGoals >= i) {
                isGoalsScoredArray[i - 1] = true;
            }

        }

        return mapArrayToModel(isGoalsScoredArray);

    }

    private static NumberOfGoalsModel mapArrayToModel(boolean[] isGoalsScoredArray) {

        return new NumberOfGoalsModel(
                isGoalsScoredArray[0],
                isGoalsScoredArray[1],
                isGoalsScoredArray[2],
                isGoalsScoredArray[3]
        );

    }

}
