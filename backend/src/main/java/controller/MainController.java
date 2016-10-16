package controller;

import collecter.TeamGoalsCollecter;
import collecter.model.TeamGoalsModel;
import dao.MatchDAO;
import model.Match;
import viewmodel.NumberOfGoalsView;
import viewmodel.TeamGoalsView;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private MatchDAO matchDAO;
    private NumberOfGoalsController numberOfGoalsController;
    private TeamGoalsController teamGoalsController;

    public MainController(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> getMatches(String homeTeamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamName(homeTeamName);

        numberOfGoalsController = new NumberOfGoalsController(matches);
        teamGoalsController = new TeamGoalsController(matches);

        NumberOfGoalsView numberOfGoalsView = numberOfGoalsController.getNumberOfGoalsAggregated();
        TeamGoalsView teamGoalsView = teamGoalsController.getTeamGoalsAggregated();

        System.out.println(numberOfGoalsView);
        System.out.println(teamGoalsView);

        return this.matchDAO.getMatchesByTeamName(homeTeamName);
    }

}
