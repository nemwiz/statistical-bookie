package aggregator;

import collecter.NumberOfGoalsCollecter;
import model.Match;
import viewmodel.HalfTimeWithMoreGoals;

import java.util.List;
import java.util.function.Function;

public class HalfTimeWithMoreGoalsAggregator {

    public HalfTimeWithMoreGoalsAggregator() {
    }

    public HalfTimeWithMoreGoals getAggregatedCount(List<Match> matches) {
        return new HalfTimeWithMoreGoals(
                countMatches(matches, this::isMoreGoalsScoredInFirstHalfTime),
                countMatches(matches, this::isMoreGoalsScoredInSecondHalfTime),
                countMatches(matches, this::isEvenNumberOfGoalsScoredInBothHalfTimes)
        );
    }

    private long countMatches(List<Match> matches, Function<Match, Boolean> filteringFunction) {
        return matches.stream()
                .filter(filteringFunction::apply)
                .count();
    }

    private boolean isMoreGoalsScoredInFirstHalfTime(Match match) {

        int sumOfGoalsFirstHalfTime = NumberOfGoalsCollecter.sumGoalsHalfTime(match);
        int sumOfGoalsSecondHalfTime = NumberOfGoalsCollecter.sumGoalsFullTime(match) - sumOfGoalsFirstHalfTime;

        return sumOfGoalsFirstHalfTime > sumOfGoalsSecondHalfTime;
    }

    private boolean isMoreGoalsScoredInSecondHalfTime(Match match) {

        int sumOfGoalsFirstHalfTime = NumberOfGoalsCollecter.sumGoalsHalfTime(match);
        int sumOfGoalsSecondHalfTime = NumberOfGoalsCollecter.sumGoalsFullTime(match) - sumOfGoalsFirstHalfTime;

        return sumOfGoalsFirstHalfTime < sumOfGoalsSecondHalfTime;
    }

    private boolean isEvenNumberOfGoalsScoredInBothHalfTimes(Match match) {

        int sumOfGoalsFirstHalfTime = NumberOfGoalsCollecter.sumGoalsHalfTime(match);
        int sumOfGoalsSecondHalfTime = NumberOfGoalsCollecter.sumGoalsFullTime(match) - sumOfGoalsFirstHalfTime;

        return sumOfGoalsFirstHalfTime == sumOfGoalsSecondHalfTime;
    }

}
