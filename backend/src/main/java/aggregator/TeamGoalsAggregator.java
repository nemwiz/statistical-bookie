package aggregator;

import collecter.TeamGoalsCollecter;
import collecter.model.TeamGoalsModel;
import model.Match;
import viewmodel.TeamGoalsView;

import java.util.ArrayList;
import java.util.List;

public class TeamGoalsAggregator extends Aggregator {
    
    private List<Match> matches;
    private List<TeamGoalsModel> matchesWithTeamGoals;

    public TeamGoalsAggregator(List<Match> matches) {
        this.matches = matches;
    }
    
    
    public TeamGoalsView getAggregatedCount() {

        aggregateMatches();
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

    private void aggregateMatches() {
        matchesWithTeamGoals = new ArrayList<>();

        matches.forEach(
                match -> matchesWithTeamGoals.add(
                        TeamGoalsCollecter.getTeamGoalsScored(match)
                )
        );
    }

    private long[] getCountFullTime() {

        long countMatchesWhereHomeTeamScored = matchesWithTeamGoals.stream()
                .filter(TeamGoalsModel::isHomeTeamScored)
                .count();

        long countMatchesWhereAwayTeamScored = matchesWithTeamGoals.stream()
                .filter(TeamGoalsModel::isAwayTeamScored)
                .count();

        long countMatchesWhereBothTeamsHaveScored = matchesWithTeamGoals.stream()
                .filter(TeamGoalsModel::isBothTeamsScored)
                .count();

        return new long[]{
                countMatchesWhereHomeTeamScored,
                countMatchesWhereAwayTeamScored,
                countMatchesWhereBothTeamsHaveScored
        };
    }

    private long[] getCountHalfTime() {

        long countMatchesWhereHomeTeamScoredOnHalfTime = matchesWithTeamGoals.stream()
                .filter(TeamGoalsModel::isHomeTeamScoredOnHalfTime)
                .count();

        long countMatchesWhereAwayTeamScoredOnHalfTime = matchesWithTeamGoals.stream()
                .filter(TeamGoalsModel::isAwayTeamScoredOnHalfTime)
                .count();

        long countMatchesWhereBothTeamsHaveScoredOnHalfTime = matchesWithTeamGoals.stream()
                .filter(TeamGoalsModel::isBothTeamsScoredOnHalfTime)
                .count();

        return new long[]{
                countMatchesWhereHomeTeamScoredOnHalfTime,
                countMatchesWhereAwayTeamScoredOnHalfTime,
                countMatchesWhereBothTeamsHaveScoredOnHalfTime
        };
    }
    
    private long[] getCountSecondHalfTime() {

        long countMatchesWhereHomeTeamScoredInSecondHalfTime = matchesWithTeamGoals.stream()
                .filter(TeamGoalsModel::isHomeTeamScoredInSecondHalfTime)
                .count();

        long countMatchesWhereAwayTeamScoredInSecondHalfTime = matchesWithTeamGoals.stream()
                .filter(TeamGoalsModel::isAwayTeamScoredInSecondHalfTime)
                .count();

        long countMatchesWhereBothTeamsHaveScoredInSecondHalfTime = matchesWithTeamGoals.stream()
                .filter(TeamGoalsModel::isBothTeamsScoredInSecondHalfTime)
                .count();

        return new long[]{
                countMatchesWhereHomeTeamScoredInSecondHalfTime,
                countMatchesWhereAwayTeamScoredInSecondHalfTime,
                countMatchesWhereBothTeamsHaveScoredInSecondHalfTime
        };
    }
}
