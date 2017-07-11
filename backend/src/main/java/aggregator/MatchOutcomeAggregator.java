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

    private List<Match> matches;

    public MatchOutcomeAggregator(List<Match> matches) {
        this.matches = matches;
    }

    public MatchOutcomeModel getAggregatedCount() {

        Map<String, Long> homeTeamResults = this.convertValuesToMap(this.getHomeTeamCount());
        Map<String, Long> awayTeamResults = this.convertValuesToMap(this.getAwayTeamCount());
        Map<String, Long> drawResults = this.convertValuesToMap(this.getDrawCount());

        return new MatchOutcomeModel(homeTeamResults, awayTeamResults, drawResults);
    }

    private long[] getHomeTeamCount() {

        long countFulltime = this.countMatches(match -> match.getFinalOutcome().equals(Constants.HOME_TEAM_WIN));
        long countFirstHalftime = this.countMatches(match -> match.getHalfTimeOutcome().equals(Constants.HOME_TEAM_WIN));
        long countSecondHalftime = this.countMatches(this::isHomeTeamWonInSecondHalfTime);

        return new long[]{
                countFulltime,
                countFirstHalftime,
                countSecondHalftime
        };

    }

    private long[] getAwayTeamCount() {

        long countFulltime = this.countMatches(match -> match.getFinalOutcome().equals(Constants.AWAY_TEAM_WIN));
        long countFirstHalftime = this.countMatches(match -> match.getHalfTimeOutcome().equals(Constants.AWAY_TEAM_WIN));
        long countSecondHalftime = this.countMatches(this::isAwayTeamWonInSecondHalfTime);

        return new long[]{
                countFulltime,
                countFirstHalftime,
                countSecondHalftime
        };

    }

    private long[] getDrawCount() {

        long countFulltime = this.countMatches(match -> match.getFinalOutcome().equals(Constants.DRAW));
        long countFirstHalftime = this.countMatches(match -> match.getHalfTimeOutcome().equals(Constants.DRAW));
        long countSecondHalftime = this.countMatches(this::isDrawInSecondHalfTime);

        return new long[]{
                countFulltime,
                countFirstHalftime,
                countSecondHalftime
        };

    }

    private long countMatches(Function<Match, Boolean> filteringFunction) {
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
