package controller;

import aggregator.*;
import dao.MatchDAO;
import model.Match;
import viewmodel.*;

import java.util.List;

public class MainController {

    private MatchDAO matchDAO;
    private NumberOfGoalsAggregator numberOfGoalsAggregator;
    private TeamGoalsAggregator teamGoalsAggregator;
    private MatchOutcomeAggregator matchOutcomeAggregator;
    private MatchDetailOutcomeAggregator matchDetailOutcomeAggregator;
    private NumberOfGoalsAndWinsAggregator numberOfGoalsAndWinsAggregator;
    private HalfTimeWithMoreGoalsAggregator halfTimeWithMoreGoalsAggregator;

    public MainController(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> getMatches(String homeTeamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamName(homeTeamName);

        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);
        teamGoalsAggregator = new TeamGoalsAggregator(matches);
        matchOutcomeAggregator = new MatchOutcomeAggregator(matches);
        matchDetailOutcomeAggregator = new MatchDetailOutcomeAggregator(matches);
        numberOfGoalsAndWinsAggregator = new NumberOfGoalsAndWinsAggregator(matches);
        halfTimeWithMoreGoalsAggregator = new HalfTimeWithMoreGoalsAggregator(matches);

        NumberOfGoalsView numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();
        TeamGoalsView teamGoalsView = teamGoalsAggregator.getAggregatedCount();
        MatchOutcomeView matchOutcomeView = matchOutcomeAggregator.getAggregatedCount();
        MatchDetailOutcomeView matchDetailOutcomeView = matchDetailOutcomeAggregator.getAggregatedCount();
        NumberOfGoalsAndWinsView numberOfGoalsAndWinsView = numberOfGoalsAndWinsAggregator.getAggregatedCount();
        HalfTimeWithMoreGoalsView halfTimeWithMoreGoalsView = halfTimeWithMoreGoalsAggregator.getAggregatedCount();

        System.out.println(numberOfGoalsView);
        System.out.println(teamGoalsView);
        System.out.println(matchOutcomeView);
        System.out.println(matchDetailOutcomeView);
        System.out.println(numberOfGoalsAndWinsView);
        System.out.println(halfTimeWithMoreGoalsView);

        return this.matchDAO.getMatchesByTeamName(homeTeamName);
    }

    public AggregatedMatchesMetaView getAggregatedMatches(String homeTeamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamName(homeTeamName);

        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);
        teamGoalsAggregator = new TeamGoalsAggregator(matches);
        matchOutcomeAggregator = new MatchOutcomeAggregator(matches);
        matchDetailOutcomeAggregator = new MatchDetailOutcomeAggregator(matches);
        numberOfGoalsAndWinsAggregator = new NumberOfGoalsAndWinsAggregator(matches);
        halfTimeWithMoreGoalsAggregator = new HalfTimeWithMoreGoalsAggregator(matches);

        NumberOfGoalsView numberOfGoalsView = numberOfGoalsAggregator.getAggregatedCount();
        TeamGoalsView teamGoalsView = teamGoalsAggregator.getAggregatedCount();
        MatchOutcomeView matchOutcomeView = matchOutcomeAggregator.getAggregatedCount();
        MatchDetailOutcomeView matchDetailOutcomeView = matchDetailOutcomeAggregator.getAggregatedCount();
        NumberOfGoalsAndWinsView numberOfGoalsAndWinsView = numberOfGoalsAndWinsAggregator.getAggregatedCount();
        HalfTimeWithMoreGoalsView halfTimeWithMoreGoalsView = halfTimeWithMoreGoalsAggregator.getAggregatedCount();

       return new AggregatedMatchesMetaView(numberOfGoalsView,
               teamGoalsView,
               matchOutcomeView,
               matchDetailOutcomeView,
               numberOfGoalsAndWinsView,
               halfTimeWithMoreGoalsView);
    }

}
