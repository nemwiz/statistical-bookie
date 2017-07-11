package aggregator;

import collecter.NumberOfGoalsCollecter;
import helper.Constants;
import model.Match;
import viewmodel.TeamGoalsModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TeamGoalsAggregator {

    private List<Match> matches;

    public TeamGoalsAggregator(List<Match> matches) {
        this.matches = matches;
    }


    public TeamGoalsModel getAggregatedCount() {

        Map<String, Long> homeTeamResults = this.convertValuesToMap(this.getHomeTeamCount());
        Map<String, Long> awayTeamResults = this.convertValuesToMap(this.getAwayTeamCount());
        Map<String, Long> bothTeamsResults = this.convertValuesToMap(this.getBothTeamsCount());

        return new TeamGoalsModel(homeTeamResults, awayTeamResults, bothTeamsResults);
    }

    private long[] getHomeTeamCount() {

        long scoredInTheWholeGame = this.countMatches(match -> match.getHomeTeamGoals() > Constants.ZERO_GOALS);
        long scoredInFirstHalftime = this.countMatches(match -> match.getHomeTeamHalftimeGoals() > Constants.ZERO_GOALS);
        long scoredInSecondHalftime = this.countMatches(match -> NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match) > Constants.ZERO_GOALS);

        return new long[]{
                scoredInTheWholeGame,
                scoredInFirstHalftime,
                scoredInSecondHalftime
        };
    }

    private long[] getAwayTeamCount() {

        long scoredInTheWholeGame = this.countMatches(match -> match.getAwayTeamGoals() > Constants.ZERO_GOALS);
        long scoredInFirstHalftime = this.countMatches(match -> match.getAwayTeamHalftimeGoals() > Constants.ZERO_GOALS);
        long scoredInSecondHalftime = this.countMatches(match -> NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match) > Constants.ZERO_GOALS);

        return new long[]{
                scoredInTheWholeGame,
                scoredInFirstHalftime,
                scoredInSecondHalftime
        };
    }

    private long[] getBothTeamsCount() {

        long scoredInTheWholeGame = this.countMatches(this::isBothTeamsScored);
        long scoredInFirstHalftime = this.countMatches(this::isBothTeamsScoredOnHalfTime);
        long scoredInSecondHalftime = this.countMatches(this::isBothTeamsScoredInSecondHalfTime);

        return new long[]{
                scoredInTheWholeGame,
                scoredInFirstHalftime,
                scoredInSecondHalftime
        };
    }

    private long countMatches(Function<Match, Boolean> filteringFunction) {

        return this.matches.stream()
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
