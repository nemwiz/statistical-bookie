package controller;

import aggregator.GoalsAggregator;
import aggregator.model.NumberOfGoalsPerMatchModel;
import dao.MatchDAO;
import mapper.MatchToNumberOfGoalsMapper;
import model.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchController {

    private MatchDAO matchDAO;

    public MatchController(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> getNumberOfGoalsFromLastMatches(String teamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamName(teamName);
        List<NumberOfGoalsPerMatchModel> matchesWithNumberOfGoals = new ArrayList<>();

        matches.forEach(
                match -> matchesWithNumberOfGoals.add(
                        MatchToNumberOfGoalsMapper
                                .mapSingleMatchToNumberOfGoalsModel(
                                        GoalsAggregator.getNumberOfGoals(match))
                )
        );

        NumberOfGoalsPerMatchModel countOfLastMatchesWithNumberOfGoals = countNumberOfGoalsForEachFromLastNMatches(matchesWithNumberOfGoals);

        System.out.println(countOfLastMatchesWithNumberOfGoals.toString());

        return matches;
    }

    private static NumberOfGoalsPerMatchModel countNumberOfGoalsForEachFromLastNMatches(List<NumberOfGoalsPerMatchModel> matchesWithNumberOfGoals) {

        int countOfLastMatchesWithOneGoal = (int) matchesWithNumberOfGoals.stream()
                .filter(
                        matchWithNumberOfGoals -> matchWithNumberOfGoals.getOneGoal()==1
                ).count();
        int countOfLastMatchesWithTwoGoals = (int) matchesWithNumberOfGoals.stream()
                .filter(
                        matchWithNumberOfGoals -> matchWithNumberOfGoals.getTwoGoals()==1
                ).count();
        int countOfLastMatchesWithThreeGoals = (int) matchesWithNumberOfGoals.stream()
                .filter(
                        matchWithNumberOfGoals -> matchWithNumberOfGoals.getThreeGoals()==1
                ).count();
        int countOfLastMatchesWithFourOrMoreGoals = (int) matchesWithNumberOfGoals.stream()
                .filter(
                        matchWithNumberOfGoals -> matchWithNumberOfGoals.getFourOrMoreGoals()==1
                ).count();

        return new NumberOfGoalsPerMatchModel(
                countOfLastMatchesWithOneGoal,
                countOfLastMatchesWithTwoGoals,
                countOfLastMatchesWithThreeGoals,
                countOfLastMatchesWithFourOrMoreGoals
        );

    }

}
