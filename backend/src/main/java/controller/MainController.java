package controller;

import aggregator.*;
import dao.MatchDAO;
import helper.Constants;
import model.Match;
import viewmodel.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

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

        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<AggregatedMatchesMetaView> matchesMetaViews = new ArrayList<>();

        List<Match> lastFiveMatches = this.matchDAO.getMatchesByTeamNames(homeTeamName, awayTeamName, 5);
        List<Match> lastTenMatches = this.matchDAO.getMatchesByTeamNames(homeTeamName, awayTeamName, 10);

        Future<AggregatedMatchesMetaView> fiveMatchesView = executor.submit(() -> this.getAggregatedMatchesMetaView(lastFiveMatches));
        Future<AggregatedMatchesMetaView> tenMatchesView = executor.submit(() -> this.getAggregatedMatchesMetaView(lastTenMatches));

        try {
            matchesMetaViews.add(fiveMatchesView.get(4, TimeUnit.SECONDS));
            matchesMetaViews.add(tenMatchesView.get(4, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        try {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("Executor shutdown finished");
        }

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));

        return matchesMetaViews;
    }

    private AggregatedMatchesMetaView getAggregatedMatchesMetaView(List<Match> matches) {

        NumberOfGoalsModel fullTime = this.numberOfGoalsAggregator.getAggregatedCount(matches, Constants.FULLTIME);
        NumberOfGoalsModel firstHalf = this.numberOfGoalsAggregator.getAggregatedCount(matches, Constants.FIRST_HALF);
        NumberOfGoalsModel secondHalf = this.numberOfGoalsAggregator.getAggregatedCount(matches, Constants.SECOND_HALF);

        TeamGoalsModel teamGoalsView = this.teamGoalsAggregator.getAggregatedCount(matches);
        MatchOutcomeModel matchOutcomeView = this.matchOutcomeAggregator.getAggregatedCount(matches);
        MatchDetailOutcomeView matchDetailOutcomeView = this.matchDetailOutcomeAggregator.getAggregatedCount(matches);
        NumberOfGoalsAndWinsModel numberOfGoalsAndWinsView = this.numberOfGoalsAndWinsAggregator.getAggregatedCount(matches);
        HalfTimeWithMoreGoalsView halfTimeWithMoreGoalsView = this.halfTimeWithMoreGoalsAggregator.getAggregatedCount(matches);
        Map<String, Long> exactResultsView = this.exactResultAggregator.aggregate(matches);

        return new AggregatedMatchesMetaView(fullTime,
                firstHalf,
                secondHalf,
                teamGoalsView,
                matchOutcomeView,
                matchDetailOutcomeView,
                numberOfGoalsAndWinsView,
                halfTimeWithMoreGoalsView,
                exactResultsView);
    }
}
