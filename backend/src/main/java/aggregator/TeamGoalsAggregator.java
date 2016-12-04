package aggregator;

import collecter.NumberOfGoalsCollecter;
import helper.Constants;
import model.Match;
import viewmodel.TeamGoalsView;

import java.util.List;

public class TeamGoalsAggregator extends Aggregator {
    
    private List<Match> matches;

    public TeamGoalsAggregator(List<Match> matches) {
        this.matches = matches;
    }
    
    
    public TeamGoalsView getAggregatedCount() {

        long[] countOfTeamGoalsFullTime = getCountFullTime();
        long[] countOfTeamGoalsHalfTime = getCountHalfTime();
        long[] countOfTeamGoalsInSecondHalfTime = getCountSecondHalfTime();

        return mapArraysToViewModel(
                countOfTeamGoalsFullTime,
                countOfTeamGoalsHalfTime,
                countOfTeamGoalsInSecondHalfTime);
    }

    private TeamGoalsView mapArraysToViewModel(long[] countOfTeamGoalsFullTime,
                                               long[] countOfTeamGoalsHalfTime,
                                               long[] countOfTeamsGoalsInSecondHalfTime) {
        return new TeamGoalsView(
                countOfTeamGoalsFullTime[0],
                countOfTeamGoalsFullTime[1],
                countOfTeamGoalsFullTime[2],
                countOfTeamGoalsHalfTime[0],
                countOfTeamGoalsHalfTime[1],
                countOfTeamGoalsHalfTime[2],
                countOfTeamsGoalsInSecondHalfTime[0],
                countOfTeamsGoalsInSecondHalfTime[1],
                countOfTeamsGoalsInSecondHalfTime[2]
        );
    }

    private long[] getCountFullTime() {

        long countMatchesWhereHomeTeamScored = this.matches.stream()
                .filter(match -> match.getHomeTeamGoals() > Constants.ZERO_GOALS)
                .count();

        long countMatchesWhereAwayTeamScored = this.matches.stream()
                .filter(match -> match.getAwayTeamGoals() > Constants.ZERO_GOALS)
                .count();

        long countMatchesWhereBothTeamsHaveScored = this.matches.stream()
                .filter(this::isBothTeamsScored)
                .count();

        return new long[]{
                countMatchesWhereHomeTeamScored,
                countMatchesWhereAwayTeamScored,
                countMatchesWhereBothTeamsHaveScored
        };
    }

    private long[] getCountHalfTime() {

        long countMatchesWhereHomeTeamScoredOnHalfTime = this.matches.stream()
                .filter(match -> match.getHomeTeamHalftimeGoals() > Constants.ZERO_GOALS)
                .count();

        long countMatchesWhereAwayTeamScoredOnHalfTime = this.matches.stream()
                .filter(match -> match.getAwayTeamHalftimeGoals() > Constants.ZERO_GOALS)
                .count();

        long countMatchesWhereBothTeamsHaveScoredOnHalfTime = this.matches.stream()
                .filter(this::isBothTeamsScoredOnHalfTime)
                .count();

        return new long[]{
                countMatchesWhereHomeTeamScoredOnHalfTime,
                countMatchesWhereAwayTeamScoredOnHalfTime,
                countMatchesWhereBothTeamsHaveScoredOnHalfTime
        };
    }

    private long[] getCountSecondHalfTime() {

        long countMatchesWhereHomeTeamScoredInSecondHalfTime = this.matches.stream()
                .filter(match -> NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match) > Constants.ZERO_GOALS)
                .count();

        long countMatchesWhereAwayTeamScoredInSecondHalfTime = this.matches.stream()
                .filter(match -> NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match) > Constants.ZERO_GOALS)
                .count();

        long countMatchesWhereBothTeamsHaveScoredInSecondHalfTime = this.matches.stream()
                .filter(this::isBothTeamsScoredInSecondHalfTime)
                .count();

        return new long[]{
                countMatchesWhereHomeTeamScoredInSecondHalfTime,
                countMatchesWhereAwayTeamScoredInSecondHalfTime,
                countMatchesWhereBothTeamsHaveScoredInSecondHalfTime
        };
    }

    private boolean isBothTeamsScored(Match match) {
        return match.getHomeTeamGoals() > Constants.ZERO_GOALS  && match.getAwayTeamGoals() > Constants.ZERO_GOALS;
    }

    private boolean isBothTeamsScoredOnHalfTime(Match match) {
        return match.getHomeTeamHalftimeGoals() > Constants.ZERO_GOALS && match.getAwayTeamHalftimeGoals() > Constants.ZERO_GOALS;
    }

    private boolean isBothTeamsScoredInSecondHalfTime(Match match) {
        return NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match) > Constants.ZERO_GOALS && NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match) > Constants.ZERO_GOALS;
    }
}
