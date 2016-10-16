package controller;

import collecter.TeamGoalsCollecter;
import collecter.model.NumberOfGoalsModel;
import collecter.model.TeamGoalsModel;
import model.Match;
import viewmodel.TeamGoalsView;

import java.util.ArrayList;
import java.util.List;

public class TeamGoalsController {
    
    private List<Match> matches;
    private List<TeamGoalsModel> matchesWithTeamGoals;

    public TeamGoalsController(List<Match> matches) {
        this.matches = matches;
    }
    
    
    public TeamGoalsView getTeamGoalsAggregated() {

        aggregateMatches();
        long[] countOfTeamGoalsFullTime = getCountFullTime();
        long[] countOfTeamGoalsHalfTime = getCountHalfTime();

        return mapArraysToModel(countOfTeamGoalsFullTime, countOfTeamGoalsHalfTime);
    }

    private TeamGoalsView mapArraysToModel(long[] countOfTeamGoalsFullTime, long[] countOfTeamGoalsHalfTime) {
        return new TeamGoalsView(
                countOfTeamGoalsFullTime[0],
                countOfTeamGoalsFullTime[1],
                countOfTeamGoalsFullTime[2],
                countOfTeamGoalsHalfTime[0],
                countOfTeamGoalsHalfTime[1],
                countOfTeamGoalsHalfTime[2]
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
                .filter(
                        TeamGoalsModel::isHomeTeamScored
                ).count();
        long countMatchesWhereAwayTeamScored = matchesWithTeamGoals.stream()
                .filter(
                        TeamGoalsModel::isAwayTeamScored
                ).count();
        long countMatchesWhereBothTeamsHaveScored = matchesWithTeamGoals.stream()
                .filter(
                        TeamGoalsModel::isBothTeamsScored
                ).count();

        return new long[]{
                countMatchesWhereHomeTeamScored,
                countMatchesWhereAwayTeamScored,
                countMatchesWhereBothTeamsHaveScored
        };
    }

    private long[] getCountHalfTime() {

        long countMatchesWhereHomeTeamScoredOnHalfTime = matchesWithTeamGoals.stream()
                .filter(
                        TeamGoalsModel::isHomeTeamScoredOnHalfTime
                ).count();
        long countMatchesWhereAwayTeamScoredOnHalfTime = matchesWithTeamGoals.stream()
                .filter(
                        TeamGoalsModel::isAwayTeamScoredOnHalfTime
                ).count();
        long countMatchesWhereBothTeamsHaveScoredOnHalfTime = matchesWithTeamGoals.stream()
                .filter(
                        TeamGoalsModel::isBothTeamsScoredOnHalfTime
                ).count();

        return new long[]{
                countMatchesWhereHomeTeamScoredOnHalfTime,
                countMatchesWhereAwayTeamScoredOnHalfTime,
                countMatchesWhereBothTeamsHaveScoredOnHalfTime
        };
    }
}