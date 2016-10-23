package aggregator;

import collecter.NumberOfGoalsCollecter;
import collecter.model.NumberOfGoalsModel;
import model.Match;
import viewmodel.NumberOfGoalsView;

import java.util.ArrayList;
import java.util.List;

import static helper.Counter.count;

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

        long countOfLastMatchesWithNoGoals = 0;
        long countOfLastMatchesWithOneGoal = 0;
        long countOfLastMatchesWithTwoGoals = 0;
        long countOfLastMatchesWithThreeGoals = 0;
        long countOfLastMatchesWithFourOrMoreGoals = 0;

        for(NumberOfGoalsModel numberOfGoalsModel : matchesWithNumberOfGoals) {

            countOfLastMatchesWithNoGoals =
                    count(matchesWithNumberOfGoals, !numberOfGoalsModel.isOneGoalsScored());

            countOfLastMatchesWithOneGoal =
                    count(matchesWithNumberOfGoals, numberOfGoalsModel.isOneGoalsScored());

            countOfLastMatchesWithTwoGoals =
                    count(matchesWithNumberOfGoals, numberOfGoalsModel.isTwoGoalsScored());

            countOfLastMatchesWithThreeGoals =
                    count(matchesWithNumberOfGoals, numberOfGoalsModel.isThreeGoalsScored());

            countOfLastMatchesWithFourOrMoreGoals =
                    count(matchesWithNumberOfGoals, numberOfGoalsModel.isFourOrMoreGoalsScored());

        }

        return new long[]{
                countOfLastMatchesWithNoGoals,
                countOfLastMatchesWithOneGoal,
                countOfLastMatchesWithTwoGoals,
                countOfLastMatchesWithThreeGoals,
                countOfLastMatchesWithFourOrMoreGoals};

    }

    private long[] getCountHalfTime() {

        long countOfLastMatchesWithNoGoalsOnHalfTime = 0;
        long countOfLastMatchesWithOneGoalOnHalfTime = 0;
        long countOfLastMatchesWithTwoGoalsOnHalfTime = 0;
        long countOfLastMatchesWithThreeGoalsOnHalfTime = 0;
        long countOfLastMatchesWithFourOrMoreGoalsOnHalfTime = 0;

        for(NumberOfGoalsModel numberOfGoalsModel : matchesWithNumberOfGoals) {

            countOfLastMatchesWithNoGoalsOnHalfTime =
                    count(matchesWithNumberOfGoals, !numberOfGoalsModel.isOneGoalsScoredOnHalfTime());

            countOfLastMatchesWithOneGoalOnHalfTime =
                    count(matchesWithNumberOfGoals, numberOfGoalsModel.isOneGoalsScoredOnHalfTime());

            countOfLastMatchesWithTwoGoalsOnHalfTime =
                    count(matchesWithNumberOfGoals, numberOfGoalsModel.isTwoGoalsScoredOnHalfTime());

            countOfLastMatchesWithThreeGoalsOnHalfTime =
                    count(matchesWithNumberOfGoals, numberOfGoalsModel.isThreeGoalsScoredOnHalfTime());

            countOfLastMatchesWithFourOrMoreGoalsOnHalfTime =
                    count(matchesWithNumberOfGoals, numberOfGoalsModel.isFourOrMoreGoalsScoredOnHalfTime());
        }

        return new long[]{
                countOfLastMatchesWithNoGoalsOnHalfTime,
                countOfLastMatchesWithOneGoalOnHalfTime,
                countOfLastMatchesWithTwoGoalsOnHalfTime,
                countOfLastMatchesWithThreeGoalsOnHalfTime,
                countOfLastMatchesWithFourOrMoreGoalsOnHalfTime};

    }

}
