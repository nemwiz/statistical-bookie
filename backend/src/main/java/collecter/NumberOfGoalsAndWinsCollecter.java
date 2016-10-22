package collecter;

import collecter.model.NumberOfGoalsAndWinsModel;
import collecter.model.NumberOfGoalsModel;
import helper.Constants;
import model.Match;

public class NumberOfGoalsAndWinsCollecter {

    public static NumberOfGoalsAndWinsModel getNumberOfGoalsAndWins(Match match) {

        NumberOfGoalsModel numberOfGoalsModel = NumberOfGoalsCollecter.getNumberOfGoals(match);

        return new NumberOfGoalsAndWinsModel(
                matchWithOutcomeAndOneGoal(match, Constants.HOME_TEAM_WIN, numberOfGoalsModel),
                matchWithOutcomeAndTwoGoals(match, Constants.HOME_TEAM_WIN, numberOfGoalsModel),
                matchWithOutcomeAndThreeGoals(match, Constants.HOME_TEAM_WIN, numberOfGoalsModel),
                matchWithOutcomeAndFourOrMoreGoals(match, Constants.HOME_TEAM_WIN, numberOfGoalsModel),
                matchWithOutcomeAndOneGoal(match, Constants.DRAW, numberOfGoalsModel),
                matchWithOutcomeAndTwoGoals(match, Constants.DRAW, numberOfGoalsModel),
                matchWithOutcomeAndThreeGoals(match, Constants.DRAW, numberOfGoalsModel),
                matchWithOutcomeAndFourOrMoreGoals(match, Constants.DRAW, numberOfGoalsModel),
                matchWithOutcomeAndOneGoal(match, Constants.AWAY_TEAM_WIN, numberOfGoalsModel),
                matchWithOutcomeAndTwoGoals(match, Constants.AWAY_TEAM_WIN, numberOfGoalsModel),
                matchWithOutcomeAndThreeGoals(match, Constants.AWAY_TEAM_WIN, numberOfGoalsModel),
                matchWithOutcomeAndFourOrMoreGoals(match, Constants.AWAY_TEAM_WIN, numberOfGoalsModel)
        );

    }

    public static boolean matchWithOutcomeAndOneGoal(Match match, String finalOutcome, NumberOfGoalsModel numberOfGoalsModel) {
        return match.getFinalOutcome().equals(finalOutcome) && numberOfGoalsModel.isOneGoalsScored();
    }

    public static boolean matchWithOutcomeAndTwoGoals(Match match, String finalOutcome, NumberOfGoalsModel numberOfGoalsModel) {
        return match.getFinalOutcome().equals(finalOutcome) && numberOfGoalsModel.isTwoGoalsScored();
    }

    public static boolean matchWithOutcomeAndThreeGoals(Match match, String finalOutcome, NumberOfGoalsModel numberOfGoalsModel) {
        return match.getFinalOutcome().equals(finalOutcome) && numberOfGoalsModel.isThreeGoalsScored();
    }

    public static boolean matchWithOutcomeAndFourOrMoreGoals(Match match, String finalOutcome, NumberOfGoalsModel numberOfGoalsModel) {
        return match.getFinalOutcome().equals(finalOutcome) && numberOfGoalsModel.isFourOrMoreGoalsScored();
    }


}
