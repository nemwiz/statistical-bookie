package aggregator;

import model.Match;
import model.TeamScore;
import viewmodel.LeagueTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static helper.Constants.*;

public class LeagueTableAggregator {

    private static int NUMBER_OF_POINTS_FOR_WIN = 3;
    private static int NUMBER_OF_POINTS_FOR_DRAW = 1;
    private static int NUMBER_OF_POINTS_FOR_LOSS = 0;

    public LeagueTableAggregator() {
    }

    public List<LeagueTable> aggregate(List<Match> matches) {

        List<TeamScore> teamScores = matches.stream()
                .flatMap(match -> Stream.of(
                        new TeamScore(match.getHomeTeam(), this.getPointsWonBasedOnMatchOutcome(HOME_TEAM_WIN, match.getFinalOutcome())),
                        new TeamScore(match.getAwayTeam(), this.getPointsWonBasedOnMatchOutcome(AWAY_TEAM_WIN, match.getFinalOutcome()))))
                .collect(Collectors.toList());

        Map<String, Integer> pointsWon = teamScores
                .stream()
                .collect(Collectors.groupingBy(TeamScore::getTeamName, Collectors.summingInt(TeamScore::getPointsWon)));

        Map<String, Long> wins = teamScores
                .stream()
                .filter(getTeamScorePredicate(NUMBER_OF_POINTS_FOR_WIN))
                .collect(Collectors.groupingBy(TeamScore::getTeamName, Collectors.counting()));

        Map<String, Long> losses = teamScores
                .stream()
                .filter(getTeamScorePredicate(NUMBER_OF_POINTS_FOR_LOSS))
                .collect(Collectors.groupingBy(TeamScore::getTeamName, Collectors.counting()));

        Map<String, Long> draws = teamScores
                .stream()
                .filter(getTeamScorePredicate(NUMBER_OF_POINTS_FOR_DRAW))
                .collect(Collectors.groupingBy(TeamScore::getTeamName, Collectors.counting()));

        List<LeagueTable> leagueTable = new ArrayList<>();

        pointsWon.forEach((key, value) -> leagueTable.add(new LeagueTable(key, wins.getOrDefault(key, 0L), draws.getOrDefault(key, 0L), losses.getOrDefault(key, 0L), value)));

        return leagueTable;
    }

    private int getPointsWonBasedOnMatchOutcome(String homeOrAwayFixture, String finalOutcome) {

        if (homeOrAwayFixture.equals(finalOutcome)) {
            return NUMBER_OF_POINTS_FOR_WIN;
        } else if (finalOutcome.equals(DRAW)) {
            return NUMBER_OF_POINTS_FOR_DRAW;
        } else {
            return NUMBER_OF_POINTS_FOR_LOSS;
        }

    }

    private Predicate<TeamScore> getTeamScorePredicate(int numberOfPoints) {
        return teamScore -> teamScore.getPointsWon() == numberOfPoints;
    }

}
