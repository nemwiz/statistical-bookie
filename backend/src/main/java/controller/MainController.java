package controller;

import dao.MatchDAO;
import model.Match;
import viewmodel.MatchOutcomeView;
import viewmodel.NumberOfGoalsView;
import viewmodel.TeamGoalsView;

import java.util.List;

public class MainController {

    private MatchDAO matchDAO;
    private NumberOfGoalsController numberOfGoalsController;
    private TeamGoalsController teamGoalsController;
    private MatchOutcomeController matchOutcomeController;

    public MainController(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> getMatches(String homeTeamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamName(homeTeamName);

        numberOfGoalsController = new NumberOfGoalsController(matches);
        teamGoalsController = new TeamGoalsController(matches);
        matchOutcomeController = new MatchOutcomeController(matches);

        NumberOfGoalsView numberOfGoalsView = numberOfGoalsController.getNumberOfGoalsAggregated();
        TeamGoalsView teamGoalsView = teamGoalsController.getTeamGoalsAggregated();
        MatchOutcomeView matchOutcomeView = matchOutcomeController.getMatchOutcomeAggregated();

        System.out.println(numberOfGoalsView);
        System.out.println(teamGoalsView);
        System.out.println(matchOutcomeView);

        return this.matchDAO.getMatchesByTeamName(homeTeamName);
    }

}
