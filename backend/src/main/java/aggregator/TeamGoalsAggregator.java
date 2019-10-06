package aggregator;

import collecter.NumberOfGoalsCollecter;
import helper.Constants;
import model.Match;
import viewmodel.TeamScoredModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TeamGoalsAggregator {

    public TeamGoalsAggregator() {
    }

    public TeamScoredModel getAggregatedCount(List<Match> matches) {

        Map<String, Long> homeTeamResults = this.convertValuesToMap(this.getHomeTeamCount(matches));
        Map<String, Long> awayTeamResults = this.convertValuesToMap(this.getAwayTeamCount(matches));
        Map<String, Long> bothTeamsResults = this.convertValuesToMap(this.getBothTeamsCount(matches));

        return new TeamScoredModel(homeTeamResults, awayTeamResults, bothTeamsResults);
    }

    private long[] getHomeTeamCount(List<Match> matches) {

        long scoredInTheWholeGame = this.countMatches(matches, match -> match.getHomeTeamGoals() > Constants.ZERO_GOALS);
        long scoredInFirstHalftime = this.countMatches(matches, match -> match.getHomeTeamHalftimeGoals() > Constants.ZERO_GOALS);
        long scoredInSecondHalftime = this.countMatches(matches, match -> NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match) > Constants.ZERO_GOALS);

        return new long[]{
                scoredInTheWholeGame,
                scoredInFirstHalftime,
                scoredInSecondHalftime
        };
    }

    private long[] getAwayTeamCount(List<Match> matches) {

        long scoredInTheWholeGame = this.countMatches(matches, match -> match.getAwayTeamGoals() > Constants.ZERO_GOALS);
        long scoredInFirstHalftime = this.countMatches(matches, match -> match.getAwayTeamHalftimeGoals() > Constants.ZERO_GOALS);
        long scoredInSecondHalftime = this.countMatches(matches, match -> NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match) > Constants.ZERO_GOALS);

        return new long[]{
                scoredInTheWholeGame,
                scoredInFirstHalftime,
                scoredInSecondHalftime
        };
    }

    private long[] getBothTeamsCount(List<Match> matches) {

        long scoredInTheWholeGame = this.countMatches(matches, this::isBothTeamsScored);
        long scoredInFirstHalftime = this.countMatches(matches, this::isBothTeamsScoredOnHalfTime);
        long scoredInSecondHalftime = this.countMatches(matches, this::isBothTeamsScoredInSecondHalfTime);

        return new long[]{
                scoredInTheWholeGame,
                scoredInFirstHalftime,
                scoredInSecondHalftime
        };
    }

    private long countMatches(List<Match> matches, Function<Match, Boolean> filteringFunction) {

        return matches.stream()
                .filter(filteringFunction::apply)
                .count();
    }

    private boolean isBothTeamsScored(Match match) {
        return match.getHomeTeamGoals() > Constants.ZERO_GOALS && match.getAwayTeamGoals() > Constants.ZERO_GOALS;
    }

    private boolean isBothTeamsScoredOnHalfTime(Match match) {
        return match.getHomeTeamHalftimeGoals() > Constants.ZERO_GOALS && match.getAwayTeamHalftimeGoals() > Constants.ZERO_GOALS;
    }

    private boolean isBothTeamsScoredInSecondHalfTime(Match match) {
        return NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match) > Constants.ZERO_GOALS && NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match) > Constants.ZERO_GOALS;
    }

    private Map<String, Long> convertValuesToMap(long[] results) {

        Map<String, Long> resultsMap = new LinkedHashMap<>();

        resultsMap.put("scoredInTheWholeGame", results[0]);
        resultsMap.put("scoredInFirstHalftime", results[1]);
        resultsMap.put("scoredInSecondHalftime", results[2]);

        return resultsMap;
    }
}
