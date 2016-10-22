package collecter;

import collecter.model.NumberOfGoalsAndWinsModel;
import collecter.model.NumberOfGoalsModel;
import helper.Constants;
import model.Match;

public class NumberOfGoalsAndWinsCollecter {

    public static NumberOfGoalsAndWinsModel getNumberOfGoalsAndWins(Match match) {

        NumberOfGoalsModel numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        return new NumberOfGoalsAndWinsModel(
                matchesWithOneGoal(match, Constants.HOME_TEAM_WIN, numberOfGoalsModel),
                matchesWithTwoGoals(match, Constants.HOME_TEAM_WIN, numberOfGoalsModel),
                matchesWithThreeGoals(match, Constants.HOME_TEAM_WIN, numberOfGoalsModel),
                matchesWithFourOrMoreGoals(match, Constants.HOME_TEAM_WIN, numberOfGoalsModel),
                matchesWithOneGoal(match, Constants.DRAW, numberOfGoalsModel),
                matchesWithTwoGoals(match, Constants.DRAW, numberOfGoalsModel),
                matchesWithThreeGoals(match, Constants.DRAW, numberOfGoalsModel),
                matchesWithFourOrMoreGoals(match, Constants.DRAW, numberOfGoalsModel),
                matchesWithOneGoal(match, Constants.AWAY_TEAM_WIN, numberOfGoalsModel),
                matchesWithTwoGoals(match, Constants.AWAY_TEAM_WIN, numberOfGoalsModel),
                matchesWithThreeGoals(match, Constants.AWAY_TEAM_WIN, numberOfGoalsModel),
                matchesWithFourOrMoreGoals(match, Constants.AWAY_TEAM_WIN, numberOfGoalsModel)
        );

    }

    public static boolean matchesWithOneGoal(Match match, String finalOutcome, NumberOfGoalsModel numberOfGoalsModel) {
        return match.getFinalOutcome().equals(finalOutcome) && numberOfGoalsModel.isOneGoalsScored();
    }

    public static boolean matchesWithTwoGoals(Match match, String finalOutcome, NumberOfGoalsModel numberOfGoalsModel) {
        return match.getFinalOutcome().equals(finalOutcome) && numberOfGoalsModel.isTwoGoalsScored();
    }

    public static boolean matchesWithThreeGoals(Match match, String finalOutcome, NumberOfGoalsModel numberOfGoalsModel) {
        return match.getFinalOutcome().equals(finalOutcome) && numberOfGoalsModel.isThreeGoalsScored();
    }

    public static boolean matchesWithFourOrMoreGoals(Match match, String finalOutcome, NumberOfGoalsModel numberOfGoalsModel) {
        return match.getFinalOutcome().equals(finalOutcome) && numberOfGoalsModel.isFourOrMoreGoalsScored();
    }


}
