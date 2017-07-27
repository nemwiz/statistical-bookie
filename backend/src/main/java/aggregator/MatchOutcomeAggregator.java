package aggregator;

import collecter.NumberOfGoalsCollecter;
import helper.Constants;
import model.Match;
import viewmodel.MatchOutcomeModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MatchOutcomeAggregator {

    public MatchOutcomeAggregator() {
    }

    public MatchOutcomeModel getAggregatedCount(List<Match> matches) {

        Map<String, Long> homeTeamResults = this.convertValuesToMap(this.getHomeTeamCount(matches));
        Map<String, Long> awayTeamResults = this.convertValuesToMap(this.getAwayTeamCount(matches));
        Map<String, Long> drawResults = this.convertValuesToMap(this.getDrawCount(matches));

        return new MatchOutcomeModel(homeTeamResults, awayTeamResults, drawResults);
    }

    private long[] getHomeTeamCount(List<Match> matches) {

        long countFulltime = this.countMatches(matches, match -> match.getFinalOutcome().equals(Constants.HOME_TEAM_WIN));
        long countFirstHalftime = this.countMatches(matches, match -> match.getHalfTimeOutcome().equals(Constants.HOME_TEAM_WIN));
        long countSecondHalftime = this.countMatches(matches, this::isHomeTeamWonInSecondHalfTime);

        return new long[]{
                countFulltime,
                countFirstHalftime,
                countSecondHalftime
        };

    }

    private long[] getAwayTeamCount(List<Match> matches) {

        long countFulltime = this.countMatches(matches, match -> match.getFinalOutcome().equals(Constants.AWAY_TEAM_WIN));
        long countFirstHalftime = this.countMatches(matches, match -> match.getHalfTimeOutcome().equals(Constants.AWAY_TEAM_WIN));
        long countSecondHalftime = this.countMatches(matches, this::isAwayTeamWonInSecondHalfTime);

        return new long[]{
                countFulltime,
                countFirstHalftime,
                countSecondHalftime
        };

    }

    private long[] getDrawCount(List<Match> matches) {

        long countFulltime = this.countMatches(matches, match -> match.getFinalOutcome().equals(Constants.DRAW));
        long countFirstHalftime = this.countMatches(matches, match -> match.getHalfTimeOutcome().equals(Constants.DRAW));
        long countSecondHalftime = this.countMatches(matches, this::isDrawInSecondHalfTime);

        return new long[]{
                countFulltime,
                countFirstHalftime,
                countSecondHalftime
        };

    }

    private long countMatches(List<Match> matches, Function<Match, Boolean> filteringFunction) {
        return matches.stream()
                .filter(filteringFunction::apply)
                .count();
    }

    private boolean isHomeTeamWonInSecondHalfTime(Match match) {

        int homeTeamGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match);
        int awayTeamGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match);

        return homeTeamGoalsScoredInSecondHalfTime > awayTeamGoalsScoredInSecondHalfTime;
    }

    private boolean isAwayTeamWonInSecondHalfTime(Match match) {

        int homeTeamGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match);
        int awayTeamGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match);

        return homeTeamGoalsScoredInSecondHalfTime < awayTeamGoalsScoredInSecondHalfTime;
    }

    private boolean isDrawInSecondHalfTime(Match match) {

        int homeTeamGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match);
        int awayTeamGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match);

        return homeTeamGoalsScoredInSecondHalfTime == awayTeamGoalsScoredInSecondHalfTime;
    }

    private Map<String, Long> convertValuesToMap(long[] results) {

        Map<String, Long> resultsMap = new LinkedHashMap<>();

        resultsMap.put("winFulltime", results[0]);
        resultsMap.put("winFirstHalftime", results[1]);
        resultsMap.put("winSecondHalftime", results[2]);

        return resultsMap;
    }

}
