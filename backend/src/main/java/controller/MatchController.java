package controller;

import collecter.NumberOfGoalsCollecter;
import collecter.TeamGoalsCollecter;
import collecter.model.NumberOfGoalsModel;
import collecter.model.TeamGoalsModel;
import dao.MatchDAO;
import model.Match;
import viewmodel.NumberOfGoalsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MatchController {

    private MatchDAO matchDAO;
    private NumberOfGoalsController numberOfGoalsController;
    private TeamGoalsController teamGoalsController;

    public MatchController(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> getMatches(String homeTeamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamName(homeTeamName);
        numberOfGoalsController = new NumberOfGoalsController(matches);
        NumberOfGoalsViewModel numberOfGoalsViewModel = numberOfGoalsController.getNumberOfGoalsAggregated();

        System.out.println(numberOfGoalsViewModel);

        getTeamGoalsFromLastMatches(homeTeamName, null);
        return this.matchDAO.getMatchesByTeamName(homeTeamName);
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

}
