package mapper;

import aggregator.model.NumberOfGoalsPerMatchModel;

import java.util.List;

public class MatchToNumberOfGoalsMapper {

    public static NumberOfGoalsPerMatchModel mapSingleMatchToNumberOfGoalsModel(List<Integer> matchWithNumberOfGoals) {

        return new NumberOfGoalsPerMatchModel(
                matchWithNumberOfGoals.get(0),
                matchWithNumberOfGoals.get(1),
                matchWithNumberOfGoals.get(2),
                matchWithNumberOfGoals.get(3)
        );

    }

}
