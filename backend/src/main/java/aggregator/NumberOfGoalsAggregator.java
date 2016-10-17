package aggregator;

import collecter.NumberOfGoalsCollecter;
import collecter.model.NumberOfGoalsModel;
import model.Match;
import viewmodel.NumberOfGoalsView;

import java.util.ArrayList;
import java.util.List;

public class NumberOfGoalsAggregator extends Aggregator{

    private List<Match> matches;
    private List<NumberOfGoalsModel> matchesWithNumberOfGoals;

    public NumberOfGoalsAggregator(List<Match> matches) {
        this.matches = matches;
    }

    public NumberOfGoalsView getAggregatedCount() {

        aggregateMatches();
        long [] countOfFullTimeGoals = getCountFullTime();
        long [] countOfHalfTimeGoals = getCountHalfTime();

        return mapArraysToViewModel(countOfFullTimeGoals, countOfHalfTimeGoals);


    }

    private NumberOfGoalsView mapArraysToViewModel(long[] countOfFullTimeGoals, long[] countOfHalfTimeGoals) {
        return new NumberOfGoalsView(
                countOfFullTimeGoals[0],
                countOfFullTimeGoals[1],
                countOfFullTimeGoals[2],
                countOfFullTimeGoals[3],
                countOfFullTimeGoals[4],
                countOfHalfTimeGoals[0],
                countOfHalfTimeGoals[1],
                countOfHalfTimeGoals[2],
                countOfHalfTimeGoals[3],
                countOfHalfTimeGoals[4]
        );
    }

    private void aggregateMatches() {
        matchesWithNumberOfGoals = new ArrayList<>();
        matches.forEach(
                match -> matchesWithNumberOfGoals.add(
                        NumberOfGoalsCollecter.getNumberOfGoals(match)
                )
        );
    }

    private long[] getCountFullTime() {

        long countOfLastMatchesWithNoGoals = matchesWithNumberOfGoals.stream()
                .filter(match -> !match.isOneGoalsScored())
                .count();
        long countOfLastMatchesWithOneGoal = matchesWithNumberOfGoals.stream()
                .filter(
                        NumberOfGoalsModel::isOneGoalsScored
                ).count();
        long countOfLastMatchesWithTwoGoals = matchesWithNumberOfGoals.stream()
                .filter(
                        NumberOfGoalsModel::isTwoGoalsScored
                ).count();
        long countOfLastMatchesWithThreeGoals = matchesWithNumberOfGoals.stream()
                .filter(
                        NumberOfGoalsModel::isThreeGoalsScored
                ).count();
        long countOfLastMatchesWithFourOrMoreGoals = matchesWithNumberOfGoals.stream()
                .filter(
                        NumberOfGoalsModel::isFourOrMoreGoalsScored
                ).count();

        return new long[]{
                countOfLastMatchesWithNoGoals,
                countOfLastMatchesWithOneGoal,
                countOfLastMatchesWithTwoGoals,
                countOfLastMatchesWithThreeGoals,
                countOfLastMatchesWithFourOrMoreGoals};

    }

    private long[] getCountHalfTime() {

        long countOfLastMatchesWithNoGoalsOnHalfTime = matchesWithNumberOfGoals.stream()
                .filter(match -> !match.isOneGoalsScoredOnHalfTime())
                .count();
        long countOfLastMatchesWithOneGoalOnHalfTime = matchesWithNumberOfGoals.stream()
                .filter(
                        NumberOfGoalsModel::isOneGoalsScoredOnHalfTime
                ).count();
        long countOfLastMatchesWithTwoGoalsOnHalfTime = matchesWithNumberOfGoals.stream()
                .filter(
                        NumberOfGoalsModel::isTwoGoalsScoredOnHalfTime
                ).count();
        long countOfLastMatchesWithThreeGoalsOnHalfTime = matchesWithNumberOfGoals.stream()
                .filter(
                        NumberOfGoalsModel::isThreeGoalsScoredOnHalfTime
                ).count();
        long countOfLastMatchesWithFourOrMoreGoalsOnHalfTime = matchesWithNumberOfGoals.stream()
                .filter(
                        NumberOfGoalsModel::isFourOrMoreGoalsScoredOnHalfTime
                ).count();

        return new long[]{
                countOfLastMatchesWithNoGoalsOnHalfTime,
                countOfLastMatchesWithOneGoalOnHalfTime,
                countOfLastMatchesWithTwoGoalsOnHalfTime,
                countOfLastMatchesWithThreeGoalsOnHalfTime,
                countOfLastMatchesWithFourOrMoreGoalsOnHalfTime};

    }

}
