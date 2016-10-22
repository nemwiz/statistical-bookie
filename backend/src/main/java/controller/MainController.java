package controller;

import aggregator.MatchOutcomeAggregator;
import aggregator.NumberOfGoalsAggregator;
import aggregator.NumberOfGoalsAndWinsAggregator;
import aggregator.TeamGoalsAggregator;
import dao.MatchDAO;
import model.Match;
import viewmodel.MatchOutcomeView;
import viewmodel.NumberOfGoalsAndWinsView;
import viewmodel.NumberOfGoalsView;
import viewmodel.TeamGoalsView;

import java.util.List;

public class MainController {

    private MatchDAO matchDAO;
    private NumberOfGoalsAggregator numberOfGoalsAggregator;
    private TeamGoalsAggregator teamGoalsAggregator;
    private MatchOutcomeAggregator matchOutcomeAggregator;
    private NumberOfGoalsAndWinsAggregator numberOfGoalsAndWinsAggregator;

    public MainController(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> getMatches(String homeTeamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamName(homeTeamName);

        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);
        teamGoalsAggregator = new TeamGoalsAggregator(matches);
        matchOutcomeAggregator = new MatchOutcomeAggregator(matches);
        numberOfGoalsAndWinsAggregator = new NumberOfGoalsAndWinsAggregator(matches);

        NumberOfGoalsView numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();
        TeamGoalsView teamGoalsView = teamGoalsAggregator.getAggregatedCount();
        MatchOutcomeView matchOutcomeView = matchOutcomeAggregator.getAggregatedCount();
        NumberOfGoalsAndWinsView numberOfGoalsAndWinsView = numberOfGoalsAndWinsAggregator.getAggregatedCount();

        System.out.println(numberOfGoalsView);
        System.out.println(teamGoalsView);
        System.out.println(matchOutcomeView);
        System.out.println(numberOfGoalsAndWinsView);

        return this.matchDAO.getMatchesByTeamName(homeTeamName);
    }

}
