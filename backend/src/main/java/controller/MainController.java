package controller;

import aggregator.*;
import dao.MatchDAO;
import model.Match;
import viewmodel.*;

import java.util.List;
import java.util.Map;

public class MainController {

    private MatchDAO matchDAO;
    private NumberOfGoalsAggregator numberOfGoalsAggregator;
    private TeamGoalsAggregator teamGoalsAggregator;
    private MatchOutcomeAggregator matchOutcomeAggregator;
    private MatchDetailOutcomeAggregator matchDetailOutcomeAggregator;
    private NumberOfGoalsAndWinsAggregator numberOfGoalsAndWinsAggregator;
    private HalfTimeWithMoreGoalsAggregator halfTimeWithMoreGoalsAggregator;
    private ExactResultAggregator exactResultAggregator;
    private LeagueTableAggregator leagueTableAggregator;

    public MainController(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> getMatches(String homeTeamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamName(homeTeamName);
        List<Match> currentSeasonMatches = this.matchDAO.getAllMatchesForCurrentSeason();

        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);
        teamGoalsAggregator = new TeamGoalsAggregator(matches);
        matchOutcomeAggregator = new MatchOutcomeAggregator(matches);
        matchDetailOutcomeAggregator = new MatchDetailOutcomeAggregator(matches);
        numberOfGoalsAndWinsAggregator = new NumberOfGoalsAndWinsAggregator(matches);
        halfTimeWithMoreGoalsAggregator = new HalfTimeWithMoreGoalsAggregator(matches);
        exactResultAggregator = new ExactResultAggregator(matches);
        leagueTableAggregator = new LeagueTableAggregator(currentSeasonMatches);

        NumberOfGoalsModel numberOfGoalsMetaView = numberOfGoalsAggregator.getAggregatedCount();
        TeamGoalsModel teamGoalsView = teamGoalsAggregator.getAggregatedCount();
        MatchOutcomeModel matchOutcomeView = matchOutcomeAggregator.getAggregatedCount();
        MatchDetailOutcomeView matchDetailOutcomeView = matchDetailOutcomeAggregator.getAggregatedCount();
        NumberOfGoalsAndWinsView numberOfGoalsAndWinsView = numberOfGoalsAndWinsAggregator.getAggregatedCount();
        HalfTimeWithMoreGoalsView halfTimeWithMoreGoalsView = halfTimeWithMoreGoalsAggregator.getAggregatedCount();
        Map<String, Long> exactResultsView = exactResultAggregator.aggregate();
        leagueTableAggregator.aggregate();

        System.out.println(numberOfGoalsMetaView);
        System.out.println(teamGoalsView);
        System.out.println(matchOutcomeView);
        System.out.println(matchDetailOutcomeView);
        System.out.println(numberOfGoalsAndWinsView);
        System.out.println(halfTimeWithMoreGoalsView);
        System.out.println("ExactResultsView " + exactResultsView.toString());

        return this.matchDAO.getMatchesByTeamName(homeTeamName);
    }

    public AggregatedMatchesMetaView getMatchesByTeamNames(String homeTeamName, String awayTeamName) {

        List<Match> matches = this.matchDAO.getMatchesByTeamNames(homeTeamName, awayTeamName);

        numberOfGoalsAggregator = new NumberOfGoalsAggregator(matches);
        teamGoalsAggregator = new TeamGoalsAggregator(matches);
        matchOutcomeAggregator = new MatchOutcomeAggregator(matches);
        matchDetailOutcomeAggregator = new MatchDetailOutcomeAggregator(matches);
        numberOfGoalsAndWinsAggregator = new NumberOfGoalsAndWinsAggregator(matches);
        halfTimeWithMoreGoalsAggregator = new HalfTimeWithMoreGoalsAggregator(matches);
        exactResultAggregator = new ExactResultAggregator(matches);

        NumberOfGoalsModel numberOfGoalsMetaView = numberOfGoalsAggregator.getAggregatedCount();
        TeamGoalsModel teamGoalsView = teamGoalsAggregator.getAggregatedCount();
        MatchOutcomeModel matchOutcomeView = matchOutcomeAggregator.getAggregatedCount();
        MatchDetailOutcomeView matchDetailOutcomeView = matchDetailOutcomeAggregator.getAggregatedCount();
        NumberOfGoalsAndWinsView numberOfGoalsAndWinsView = numberOfGoalsAndWinsAggregator.getAggregatedCount();
        HalfTimeWithMoreGoalsView halfTimeWithMoreGoalsView = halfTimeWithMoreGoalsAggregator.getAggregatedCount();
        Map<String, Long> exactResultsView = exactResultAggregator.aggregate();

        return new AggregatedMatchesMetaView(numberOfGoalsMetaView,
                teamGoalsView,
                matchOutcomeView,
                matchDetailOutcomeView,
                numberOfGoalsAndWinsView,
                halfTimeWithMoreGoalsView,
                exactResultsView);
    }
}
