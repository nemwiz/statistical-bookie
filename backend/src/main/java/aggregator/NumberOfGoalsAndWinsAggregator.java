package aggregator;

import collecter.NumberOfGoalsCollecter;
import helper.Constants;
import model.Match;
import viewmodel.NumberOfGoalsAndWinsModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NumberOfGoalsAndWinsAggregator {

    public NumberOfGoalsAndWinsAggregator() {
    }

    public NumberOfGoalsAndWinsModel getAggregatedCount(List<Match> matches) {

        Map<String, Long> homeTeamResults = this.convertValuesToMap(this.getCount(matches, Constants.HOME_TEAM_WIN));
        Map<String, Long> awayTeamResults = this.convertValuesToMap(this.getCount(matches, Constants.AWAY_TEAM_WIN));
        Map<String, Long> drawResults = this.convertDrawValuesToMap(this.getCount(matches, Constants.DRAW));

        return new NumberOfGoalsAndWinsModel(
                homeTeamResults,
                awayTeamResults,
                drawResults
        );
    }

    private long[] getCount(List<Match> matches, String whichTeamWon) {

        long winAndOneGoalScoredCount = this.countMatches(matches, whichTeamWon, Constants.ONE_GOAL);
        long winAndTwoGoalsScoredCount = this.countMatches(matches, whichTeamWon, Constants.TWO_GOALS);
        long winAndThreeGoalsScoredCount = this.countMatches(matches, whichTeamWon, Constants.THREE_GOALS);
        long winAndFourOrMoreGoalsScoredCount = this.countMatches(matches, whichTeamWon, Constants.FOUR_GOALS);

        return new long[]{
                winAndOneGoalScoredCount,
                winAndTwoGoalsScoredCount,
                winAndThreeGoalsScoredCount,
                winAndFourOrMoreGoalsScoredCount
        };
    }

    private long countMatches(List<Match> matches, String whichTeamWon, int goalsLimit) {

        return matches.stream()
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

    private Map<String, Long> convertDrawValuesToMap(long[] results) {

        Map<String, Long> resultsMap = new LinkedHashMap<>();

        resultsMap.put("drawAndOneGoalScored", results[0]);
        resultsMap.put("drawAndTwoGoalsScored", results[1]);
        resultsMap.put("drawAndThreeGoalsScored", results[2]);
        resultsMap.put("drawAndFourOrMoreGoalsScored", results[3]);

        return resultsMap;
    }
}
