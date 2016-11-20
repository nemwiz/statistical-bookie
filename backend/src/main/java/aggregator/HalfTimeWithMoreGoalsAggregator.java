package aggregator;

import collecter.NumberOfGoalsCollecter;
import model.Match;
import viewmodel.HalfTimeWithMoreGoalsView;

import java.util.List;

public class HalfTimeWithMoreGoalsAggregator extends Aggregator {

    private List<Match> matches;

    public HalfTimeWithMoreGoalsAggregator(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public HalfTimeWithMoreGoalsView getAggregatedCount() {
        return new HalfTimeWithMoreGoalsView(
                countWhenMoreGoalsAreScoredInFirstHalfTime(),
                countWhenMoreGoalsAreScoredInSecondHalfTime(),
                countEvenNumberOfGoalsWasScoredInBothHalfTimes()
        );
    }

    private long countWhenMoreGoalsAreScoredInFirstHalfTime() {
        return matches.stream()
                .filter(this::isMoreGoalsScoredInFirstHalfTime)
                .count();
    }

    private long countWhenMoreGoalsAreScoredInSecondHalfTime() {
        return matches.stream()
                .filter(this::isMoreGoalsScoredInSecondHalfTime)
                .count();
    }

    private long countEvenNumberOfGoalsWasScoredInBothHalfTimes() {
        return matches.stream()
                .filter(this::isEvenNumberOfGoalsScoredInBothHalfTimes)
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
