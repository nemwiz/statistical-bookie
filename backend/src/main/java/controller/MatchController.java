package controller;

import aggregator.GoalsAggregator;
import aggregator.model.GoalsAggregationModel;
import dao.MatchDAO;
import mapper.MatchGoalsToAggregatedGoalsMapper;
import model.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchController {

    private MatchDAO matchDAO;

    public MatchController(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> getAllMatchesByTeamName(String teamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamName(teamName);
        List<GoalsAggregationModel> gamList = new ArrayList<>();

        matches.forEach(
                match -> gamList.add(
                        MatchGoalsToAggregatedGoalsMapper
                                .mapMatchAggregatedGoalsToGoalsAggregationmodel(
                                        GoalsAggregator.aggregateGoals(match))
                )
        );

        GoalsAggregationModel finalGam = getAggregatedGoalsForLastnMatches(gamList);

        System.out.println(finalGam.toString());

        return matches;
    }

    private static GoalsAggregationModel getAggregatedGoalsForLastnMatches(List<GoalsAggregationModel> gamList) {

        int aggregatedOneGoalForLastnMatches = (int) gamList.stream()
                .filter(
                        aggregatedMatch -> aggregatedMatch.getOneGoal()==1
                ).count();
        int aggregatedTwoGoalsForLastnMatches = (int) gamList.stream()
                .filter(
                        aggregatedMatch -> aggregatedMatch.getTwoGoals()==1
                ).count();
        int aggregatedThreeGoalsForLastnMatches = (int) gamList.stream()
                .filter(
                        aggregatedMatch -> aggregatedMatch.getThreeGoals()==1
                ).count();
        int aggregatedFourOrMoreGoalsForLastnMatches = (int) gamList.stream()
                .filter(
                        aggregatedMatch -> aggregatedMatch.getFourOrMoreGoals()==1
                ).count();

        return new GoalsAggregationModel(
                aggregatedOneGoalForLastnMatches,
                aggregatedTwoGoalsForLastnMatches,
                aggregatedThreeGoalsForLastnMatches,
                aggregatedFourOrMoreGoalsForLastnMatches
        );

    }

}
