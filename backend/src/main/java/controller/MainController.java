package controller;

import aggregator.MatchOutcomeAggregator;
import aggregator.NumberOfGoalsAggregator;
import aggregator.TeamGoalsAggregator;
import dao.MatchDAO;
import model.Match;
import viewmodel.MatchOutcomeView;
import viewmodel.NumberOfGoalsView;
import viewmodel.TeamGoalsView;

import java.util.List;

public class MainController {

    private MatchDAO matchDAO;
    private NumberOfGoalsAggregator numberOfGoalsAggregator;
    private TeamGoalsAggregator teamGoalsAggregator;
    private MatchOutcomeAggregator matchOutcomeAggregator;

    public MainController(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> getMatches(String homeTeamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamName(homeTeamName);

        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);
        teamGoalsAggregator = new TeamGoalsAggregator(matches);
        matchOutcomeAggregator = new MatchOutcomeAggregator(matches);

        NumberOfGoalsView numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();
        TeamGoalsView teamGoalsView = teamGoalsAggregator.getAggregatedCount();
        MatchOutcomeView matchOutcomeView = matchOutcomeAggregator.getAggregatedCount();

        System.out.println(numberOfGoalsView);
        System.out.println(teamGoalsView);
        System.out.println(matchOutcomeView);

        return this.matchDAO.getMatchesByTeamName(homeTeamName);
    }

}
