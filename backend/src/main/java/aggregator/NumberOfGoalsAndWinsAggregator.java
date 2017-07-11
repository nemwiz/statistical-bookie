package aggregator;

import collecter.NumberOfGoalsCollecter;
import helper.Constants;
import model.Match;
import viewmodel.NumberOfGoalsAndWinsModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NumberOfGoalsAndWinsAggregator {

    private List<Match> matches;

    public NumberOfGoalsAndWinsAggregator(List<Match> matches) {
        this.matches = matches;
    }

    public NumberOfGoalsAndWinsModel getAggregatedCount() {

        Map<String, Long> homeTeamResults = this.convertValuesToMap(this.getCount(Constants.HOME_TEAM_WIN));
        Map<String, Long> awayTeamResults = this.convertValuesToMap(this.getCount(Constants.AWAY_TEAM_WIN));
        Map<String, Long> drawResults = this.convertValuesToMap(this.getCount(Constants.DRAW));

        return new NumberOfGoalsAndWinsModel(
                homeTeamResults,
                awayTeamResults,
                drawResults
        );
    }

    private long[] getCount(String whichTeamWon) {

        long winAndOneGoalScoredCount = this.countMatches(whichTeamWon, Constants.ONE_GOAL);
        long winAndTwoGoalsScoredCount = this.countMatches(whichTeamWon, Constants.TWO_GOALS);
        long winAndThreeGoalsScoredCount = this.countMatches(whichTeamWon, Constants.THREE_GOALS);
        long winAndFourOrMoreGoalsScoredCount = this.countMatches(whichTeamWon, Constants.FOUR_GOALS);

        return new long[]{
                winAndOneGoalScoredCount,
                winAndTwoGoalsScoredCount,
                winAndThreeGoalsScoredCount,
                winAndFourOrMoreGoalsScoredCount
        };
    }

    private long countMatches(String whichTeamWon, int goalsLimit) {

        return this.matches.stream()
                .filter(match -> isMatchOutcome(match, whichTeamWon))
                .filter(match -> isNumberOfGoalsAboveGoalLimit(match, goalsLimit))
                .count();
    }

    private boolean isNumberOfGoalsAboveGoalLimit(Match match, int goalsLimit) {
        return NumberOfGoalsCollecter.sumGoalsFullTime(match) >= goalsLimit;
    }

    private boolean isMatchOutcome(Match match, String outcome) {
        return match.getFinalOutcome().equals(outcome);
    }


    private Map<String, Long> convertValuesToMap(long[] results) {

        Map<String, Long> resultsMap = new LinkedHashMap<>();

        resultsMap.put("winAndOneGoalScored", results[0]);
        resultsMap.put("winAndTwoGoalsScored", results[1]);
        resultsMap.put("winAndThreeGoalsScored", results[2]);
        resultsMap.put("winAndFourOrMoreGoalsScored", results[3]);

        return resultsMap;
    }
}
