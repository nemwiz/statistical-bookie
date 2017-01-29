package aggregator;

import dao.MatchDAO;
import helper.Constants;
import model.Match;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static helper.Constants.*;

public class LeagueTableAggregator {

    private class TeamScore {

        private String teamName;
        private int numberOfGoals;
        private int pointsWon;

        public TeamScore(String teamName, int numberOfGoals, int pointsWon) {
            this.teamName = teamName;
            this.numberOfGoals = numberOfGoals;
            this.pointsWon = pointsWon;
        }

        public String getTeamName() {
            return teamName;
        }

        public int getNumberOfGoals() {
            return numberOfGoals;
        }

        public int getPointsWon() {
            return pointsWon;
        }

        @Override
        public String toString() {
            return "TeamScore{" +
                    "teamName='" + teamName + '\'' +
                    ", numberOfGoals=" + numberOfGoals +
                    ", pointsWon=" + pointsWon +
                    '}';
        }
    }

    private List<Match> matches;

    public LeagueTableAggregator(List<Match> matches) {
        this.matches = matches;
    }

    public void aggregate() {

        List<TeamScore> teamScores = new ArrayList<>();

        teamScores = this.matches.stream()
                .filter(match -> match.getLeagueCode().equals("E0"))
                .flatMap(match -> Stream.of(
                        new TeamScore(match.getHomeTeam(), match.getHomeTeamGoals(), this.getPointsWonBasedOnMatchOutcome(HOME_TEAM_WIN, match.getFinalOutcome())),
                        new TeamScore(match.getAwayTeam(), match.getAwayTeamGoals(), this.getPointsWonBasedOnMatchOutcome(AWAY_TEAM_WIN, match.getFinalOutcome()))))
                .collect(Collectors.toList());


        Map<String, Integer> leagueTable = teamScores
                .stream()
                .collect(Collectors.groupingBy(TeamScore::getTeamName, Collectors.summingInt(TeamScore::getPointsWon)));

        TreeMap<String, Integer> sortedLeagueTable = new TreeMap<>(leagueTable);

        //TODO finish endpoint, extract TeamScore to separate location, map also by goals (home and away), write table to database etc.
        // TODO Write unit tests


    }

    private int getPointsWonBasedOnMatchOutcome(String homeOrAwayFixture, String finalOutcome) {

        if (homeOrAwayFixture.equals(finalOutcome)) {
            return 3;
        } else if (finalOutcome.equals(DRAW)) {
            return 1;
        } else {
            return 0;
        }

    }

}
