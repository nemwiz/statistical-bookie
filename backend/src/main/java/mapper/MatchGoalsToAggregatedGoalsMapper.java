package mapper;

import aggregator.model.GoalsAggregationModel;

import java.util.List;

public class MatchGoalsToAggregatedGoalsMapper {

    public static GoalsAggregationModel mapMatchAggregatedGoalsToGoalsAggregationmodel(List<Integer> aggregatedGoals) {

        return new GoalsAggregationModel(
                aggregatedGoals.get(0),
                aggregatedGoals.get(1),
                aggregatedGoals.get(2),
                aggregatedGoals.get(3)
        );

    }

}
