package controller;

import collecter.NumberOfGoalsCollecter;
import collecter.TeamGoalsCollecter;
import collecter.model.NumberOfGoalsModel;
import collecter.model.TeamGoalsModel;
import dao.MatchDAO;
import model.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchController {

    private MatchDAO matchDAO;

    public MatchController(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> getMatches(String homeTeamName) {
        getNumberOfGoalsFromLastMatches(homeTeamName);
        getTeamGoalsFromLastMatches(homeTeamName, null);
        return this.matchDAO.getMatchesByTeamName(homeTeamName);
    }

    public void getNumberOfGoalsFromLastMatches(String homeTeamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamName(homeTeamName);
        List<NumberOfGoalsModel> matchesWithNumberOfGoals = new ArrayList<>();

        matches.forEach(
                match -> matchesWithNumberOfGoals.add(
                                        NumberOfGoalsCollecter.getNumberOfGoals(match)
                )
        );

        countNumberOfGoalsForEachFromLastNMatches(matchesWithNumberOfGoals);


//        return countOfLastMatchesWithNumberOfGoals;
    }

    private static void countNumberOfGoalsForEachFromLastNMatches(List<NumberOfGoalsModel> matchesWithNumberOfGoals) {

        long countOfLastMatchesWithOneGoal = matchesWithNumberOfGoals.stream()
                .filter(
                        NumberOfGoalsModel::isOneGoalsScored
                ).count();
        long countOfLastMatchesWithTwoGoals = matchesWithNumberOfGoals.stream()
                .filter(
                        NumberOfGoalsModel::isTwoGoalsScored
                ).count();
        long countOfLastMatchesWithThreeGoals = matchesWithNumberOfGoals.stream()
                .filter(
                        NumberOfGoalsModel::isThreeGoalsScored
                ).count();
        long countOfLastMatchesWithFourOrMoreGoals = matchesWithNumberOfGoals.stream()
                .filter(
                        NumberOfGoalsModel::isFourOrMoreGoalsScored
                ).count();

        System.out.println(countOfLastMatchesWithOneGoal + " " +
                countOfLastMatchesWithTwoGoals + " " +
        countOfLastMatchesWithThreeGoals + " " +
        countOfLastMatchesWithFourOrMoreGoals);


    }

    public void getTeamGoalsFromLastMatches(String homeTeamName, String awayTeamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamName(homeTeamName);
        List<TeamGoalsModel> matchesWithTeamGoals = new ArrayList<>();

        matches.forEach(
                match -> matchesWithTeamGoals.add(
                        TeamGoalsCollecter.getTeamGoalsScored(match)
                )
        );

        long lastMatchesWhereHomeTeamScored = matchesWithTeamGoals
                .stream()
                .filter(TeamGoalsModel::isHomeTeamScored)
                .count();

        System.out.println(lastMatchesWhereHomeTeamScored);
    }

    public long testFunc(String parameterName, List<?> matches) {

        matches.stream()
                .filter(null)
                .count();
        return 1;

    }

}
