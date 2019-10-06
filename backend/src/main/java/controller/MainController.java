package controller;

import aggregator.*;
import dao.MatchDAO;
import helper.Constants;
import model.Match;
import viewmodel.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
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

    public List<List<Map<String, ?>>> getMatchesByTeamNames(String homeTeamName, String awayTeamName) {

        Instant start = Instant.now();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<List<Map<String, ?>>> matchesMetaViews = new ArrayList<>();

        List<Match> lastFiveMatches = this.matchDAO.getMatchesByTeamNames(homeTeamName, awayTeamName, 5);
        List<Match> lastTenMatches = this.matchDAO.getMatchesByTeamNames(homeTeamName, awayTeamName, 10);

        Future<List<Map<String, ?>>> fiveMatchesView = executor.submit(() -> this.getAggregatedMatchesMetaView(homeTeamName, awayTeamName, lastFiveMatches));
        Future<List<Map<String, ?>>> tenMatchesView = executor.submit(() -> this.getAggregatedMatchesMetaView(homeTeamName, awayTeamName, lastTenMatches));

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

    private List<Map<String, ?>> getAggregatedMatchesMetaView(String homeTeamName, String awayTeamName, List<Match> matches) {

        NumberOfGoalsModel fullTime = this.numberOfGoalsAggregator.getAggregatedCount(matches, Constants.FULLTIME);
        NumberOfGoalsModel firstHalf = this.numberOfGoalsAggregator.getAggregatedCount(matches, Constants.FIRST_HALF);
        NumberOfGoalsModel secondHalf = this.numberOfGoalsAggregator.getAggregatedCount(matches, Constants.SECOND_HALF);

        TeamScoredModel teamScoredModel = this.teamGoalsAggregator.getAggregatedCount(matches);
        HalftimesOutcomeAndMatchOutcomeModel halftimesOutcomeAndMatchOutcomeModel = this.matchOutcomeAggregator.getAggregatedCount(matches);
        NumberOfGoalsAndWinsModel numberOfGoalsAndWinsView = this.numberOfGoalsAndWinsAggregator.getAggregatedCount(matches);

        HalfTimeFullTime halfTimeFullTime = this.matchDetailOutcomeAggregator.getAggregatedCount(matches);
        HalfTimeWithMoreGoals halfTimeWithMoreGoals = this.halfTimeWithMoreGoalsAggregator.getAggregatedCount(matches);
        Map<String, Long> exactResultsView = this.exactResultAggregator.aggregate(matches);

        TeamAggregatedData homeTeamAggregation = new TeamAggregatedData(
                fullTime.getHomeTeam(),
                firstHalf.getHomeTeam(),
                secondHalf.getHomeTeam(),
                teamScoredModel.getHomeTeam(),
                halftimesOutcomeAndMatchOutcomeModel.getHomeTeam(),
                numberOfGoalsAndWinsView.getHomeTeam()
        );

        TeamAggregatedData awayTeamAggregation = new TeamAggregatedData(
                fullTime.getAwayTeam(),
                firstHalf.getAwayTeam(),
                secondHalf.getAwayTeam(),
                teamScoredModel.getAwayTeam(),
                halftimesOutcomeAndMatchOutcomeModel.getAwayTeam(),
                numberOfGoalsAndWinsView.getAwayTeam()
        );

        BothTeamsAggregatedData bothTeamsAggregatedData = new BothTeamsAggregatedData(
                fullTime.getBothTeams(),
                firstHalf.getBothTeams(),
                secondHalf.getBothTeams(),
                teamScoredModel.getBothTeams()
        );


        MatchCommonAggregation matchCommonAggregation = new MatchCommonAggregation(
                halftimesOutcomeAndMatchOutcomeModel.getDraw(),
                numberOfGoalsAndWinsView.getDraw(),
                halfTimeFullTime,
                halfTimeWithMoreGoals,
                exactResultsView
        );

        Map<String, TeamAggregatedData> homeTeam = new HashMap<>();
        homeTeam.put(homeTeamName, homeTeamAggregation);

        Map<String, TeamAggregatedData> awayTeam = new HashMap<>();
        awayTeam.put(awayTeamName, awayTeamAggregation);

        Map<String, BothTeamsAggregatedData> bothTeams = new HashMap<>();
        bothTeams.put("BothTeams", bothTeamsAggregatedData);

        Map<String, MatchCommonAggregation> commonAggregationMap = new HashMap<>();
        commonAggregationMap.put("Common", matchCommonAggregation);

        List<Map<String, ?>> list = new ArrayList<>();
        list.add(homeTeam);
        list.add(awayTeam);
        list.add(bothTeams);
        list.add(commonAggregationMap);

        return list;
    }
}
