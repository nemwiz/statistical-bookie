package aggregator;

import model.Match;

import java.util.ArrayList;
import java.util.List;

public class GoalsAggregator {

    private static final int MAX_AGGREGATED_GOALS_PER_MATCH = 4;

    public static int sumGoals(Match match) {
        return match.getHomeTeamGoals() + match.getAwayTeamGoals();
    }

    public static List<Integer> aggregateGoals(Match match) {

        int totalGoals = sumGoals(match);
        List<Integer> aggregatedGoalsForOneMatch = new ArrayList<>();

        for (int i = 1; i <= MAX_AGGREGATED_GOALS_PER_MATCH; i++) {

            if (totalGoals >= i) {
                aggregatedGoalsForOneMatch.add(1);
            } else {
                aggregatedGoalsForOneMatch.add(0);
            }

        }

        return aggregatedGoalsForOneMatch;

    }
}
