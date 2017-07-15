package controller;

import aggregator.*;
import dao.MatchDAO;
import model.Match;
import viewmodel.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
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

    public MainController(MatchDAO matchDAO, NumberOfGoalsAggregator numberOfGoalsAggregator, TeamGoalsAggregator teamGoalsAggregator, MatchOutcomeAggregator matchOutcomeAggregator, MatchDetailOutcomeAggregator matchDetailOutcomeAggregator, NumberOfGoalsAndWinsAggregator numberOfGoalsAndWinsAggregator, HalfTimeWithMoreGoalsAggregator halfTimeWithMoreGoalsAggregator, ExactResultAggregator exactResultAggregator) {
        this.matchDAO = matchDAO;
        this.numberOfGoalsAggregator = numberOfGoalsAggregator;
        this.teamGoalsAggregator = teamGoalsAggregator;
        this.matchOutcomeAggregator = matchOutcomeAggregator;
        this.matchDetailOutcomeAggregator = matchDetailOutcomeAggregator;
        this.numberOfGoalsAndWinsAggregator = numberOfGoalsAndWinsAggregator;
        this.halfTimeWithMoreGoalsAggregator = halfTimeWithMoreGoalsAggregator;
        this.exactResultAggregator = exactResultAggregator;
    }

    public List<AggregatedMatchesMetaView> getMatchesByTeamNames(String homeTeamName, String awayTeamName) {

        Instant start = Instant.now();

        List<AggregatedMatchesMetaView> matchesMetaViews = new ArrayList<>();

        List<Match> lastFiveMatches = this.matchDAO.getMatchesByTeamNames(homeTeamName, awayTeamName, 5);
        List<Match> lastTenMatches = this.matchDAO.getMatchesByTeamNames(homeTeamName, awayTeamName, 10);

        AggregatedMatchesMetaView fiveMatchesView = getAggregatedMatchesMetaView(lastFiveMatches);
        AggregatedMatchesMetaView tenMatchesView = getAggregatedMatchesMetaView(lastTenMatches);

        matchesMetaViews.add(fiveMatchesView);
        matchesMetaViews.add(tenMatchesView);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));

        return matchesMetaViews;
    }

    private AggregatedMatchesMetaView getAggregatedMatchesMetaView(List<Match> matches) {

        NumberOfGoalsModel numberOfGoalsMetaView = this.numberOfGoalsAggregator.getAggregatedCount(matches);
        TeamGoalsModel teamGoalsView = this.teamGoalsAggregator.getAggregatedCount(matches);
        MatchOutcomeModel matchOutcomeView = this.matchOutcomeAggregator.getAggregatedCount(matches);
        MatchDetailOutcomeView matchDetailOutcomeView = this.matchDetailOutcomeAggregator.getAggregatedCount(matches);
        NumberOfGoalsAndWinsModel numberOfGoalsAndWinsView = this.numberOfGoalsAndWinsAggregator.getAggregatedCount(matches);
        HalfTimeWithMoreGoalsView halfTimeWithMoreGoalsView = this.halfTimeWithMoreGoalsAggregator.getAggregatedCount(matches);
        Map<String, Long> exactResultsView = this.exactResultAggregator.aggregate(matches);

        return new AggregatedMatchesMetaView(numberOfGoalsMetaView,
                teamGoalsView,
                matchOutcomeView,
                matchDetailOutcomeView,
                numberOfGoalsAndWinsView,
                halfTimeWithMoreGoalsView,
                exactResultsView);
    }
}
