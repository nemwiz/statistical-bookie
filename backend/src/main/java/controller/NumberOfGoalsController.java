package controller;

import collecter.NumberOfGoalsCollecter;
import collecter.model.NumberOfGoalsModel;
import model.Match;
import viewmodel.NumberOfGoalsView;

import java.util.ArrayList;
import java.util.List;

public class NumberOfGoalsController {

    private List<Match> matches;
    private List<NumberOfGoalsModel> matchesWithNumberOfGoals;

    public NumberOfGoalsController(List<Match> matches) {
        this.matches = matches;
    }

    public NumberOfGoalsView getNumberOfGoalsAggregated() {

        aggregateMatches();
        long [] countOfFullTimeGoals = getCountFullTime();
        long [] countOfHalfTimeGoals = getCountHalfTime();

        return mapArraysToModel(countOfFullTimeGoals, countOfHalfTimeGoals);


    }

    private NumberOfGoalsView mapArraysToModel(long[] countOfFullTimeGoals, long[] countOfHalfTimeGoals) {
        return new NumberOfGoalsView(
                countOfFullTimeGoals[0],
                countOfFullTimeGoals[1],
                countOfFullTimeGoals[2],
                countOfFullTimeGoals[3],
                countOfHalfTimeGoals[0],
                countOfHalfTimeGoals[1],
                countOfHalfTimeGoals[2],
                countOfHalfTimeGoals[3]
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
                countOfLastMatchesWithOneGoal,
        countOfLastMatchesWithTwoGoals,
        countOfLastMatchesWithThreeGoals,
        countOfLastMatchesWithFourOrMoreGoals};

    }

    private long[] getCountHalfTime() {

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
                countOfLastMatchesWithOneGoalOnHalfTime,
                countOfLastMatchesWithTwoGoalsOnHalfTime,
                countOfLastMatchesWithThreeGoalsOnHalfTime,
                countOfLastMatchesWithFourOrMoreGoalsOnHalfTime};

    }

}