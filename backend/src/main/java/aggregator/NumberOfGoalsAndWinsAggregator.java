package aggregator;

import collecter.NumberOfGoalsCollecter;
import helper.Constants;
import model.Match;
import viewmodel.NumberOfGoalsAndWinsView;

import java.util.List;

public class NumberOfGoalsAndWinsAggregator extends Aggregator {

    private List<Match> matches;

    public NumberOfGoalsAndWinsAggregator(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public NumberOfGoalsAndWinsView getAggregatedCount() {

        return new NumberOfGoalsAndWinsView(
                countMatchesWithHomeTeamWinAndOneGoal(),
                countMatchesWithHomeTeamWinAndTwoGoals(),
                countMatchesWithHomeTeamWinAndThreeGoals(),
                countMatchesWithHomeTeamWinAndFourOrMoreGoals(),
                countMatchesWithDrawAndOneGoal(),
                countMatchesWithDrawAndTwoGoals(),
                countMatchesWithDrawAndThreeGoals(),
                countMatchesWithDrawAndFourOrMoreGoals(),
                countMatchesWithAwayTeamWinAndOneGoal(),
                countMatchesWithAwayTeamWinAndTwoGoals(),
                countMatchesWithAwayTeamWinAndThreeGoals(),
                countMatchesWithAwayTeamWinAndFourOrMoreGoals()
        );
    }

    private long countMatchesWithHomeTeamWinAndOneGoal() {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, Constants.HOME_TEAM_WIN))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, Constants.ONE_GOAL))
                .count();
    }

    private long countMatchesWithHomeTeamWinAndTwoGoals() {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, Constants.HOME_TEAM_WIN))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, Constants.TWO_GOALS))
                .count();

    }

    private long countMatchesWithHomeTeamWinAndThreeGoals() {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, Constants.HOME_TEAM_WIN))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, Constants.THREE_GOALS))
                .count();

    }

    private long countMatchesWithHomeTeamWinAndFourOrMoreGoals() {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, Constants.HOME_TEAM_WIN))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, Constants.FOUR_GOALS))
                .count();

    }

    private long countMatchesWithDrawAndOneGoal() {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, Constants.DRAW))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, Constants.ONE_GOAL))
                .count();

    }

    private long countMatchesWithDrawAndTwoGoals() {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, Constants.DRAW))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, Constants.TWO_GOALS))
                .count();

    }

    private long countMatchesWithDrawAndThreeGoals() {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, Constants.DRAW))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, Constants.THREE_GOALS))
                .count();

    }

    private long countMatchesWithDrawAndFourOrMoreGoals() {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, Constants.DRAW))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, Constants.FOUR_GOALS))
                .count();
    }

    private long countMatchesWithAwayTeamWinAndOneGoal() {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, Constants.AWAY_TEAM_WIN))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, Constants.ONE_GOAL))
                .count();
    }

    private long countMatchesWithAwayTeamWinAndTwoGoals() {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, Constants.AWAY_TEAM_WIN))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, Constants.TWO_GOALS))
                .count();

    }

    private long countMatchesWithAwayTeamWinAndThreeGoals() {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, Constants.AWAY_TEAM_WIN))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, Constants.THREE_GOALS))
                .count();
    }

    private long countMatchesWithAwayTeamWinAndFourOrMoreGoals() {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, Constants.AWAY_TEAM_WIN))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, Constants.FOUR_GOALS))
                .count();

    }

    private boolean isNumberOfGoalsAboveGoalLimit(Match match, int goalsLimit) {
        return NumberOfGoalsCollecter.sumGoalsFullTime(match) >= goalsLimit;
    }

    private boolean isMatchOutcome(Match match, String outcome) {
        return match.getFinalOutcome().equals(outcome);
    }

}
