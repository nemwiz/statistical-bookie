package aggregator;

import collecter.NumberOfGoalsCollecter;
import helper.Constants;
import model.Match;
import viewmodel.MatchOutcomeView;

import java.util.List;

public class MatchOutcomeAggregator extends Aggregator {

    private List<Match> matches;

    public MatchOutcomeAggregator(List<Match> matches) {
        this.matches = matches;
    }

    public MatchOutcomeView getAggregatedCount() {

        return new MatchOutcomeView(
                getCountOnFullTime(Constants.HOME_TEAM_WIN),
                getCountOnFullTime(Constants.DRAW),
                getCountOnFullTime(Constants.AWAY_TEAM_WIN),
                getCountOnHalfTime(Constants.HOME_TEAM_WIN),
                getCountOnHalfTime(Constants.DRAW),
                getCountOnHalfTime(Constants.AWAY_TEAM_WIN),
                getCountHomeTeamWinsInSecondHalfTime(),
                getCountAwayTeamWinsInSecondHalfTime(),
                getCountDrawsInSecondHalfTime()

        );
    }

    public long getCountOnFullTime(String matchOutcome) {

        return matches.stream()
                .filter(
                        match -> match.getFinalOutcome().equals(matchOutcome)
                ).count();
    }

    public long getCountOnHalfTime(String halfTimeOutcome) {

        return matches.stream()
                .filter(
                        match -> match.getHalfTimeOutcome().equals(halfTimeOutcome)
                ).count();
    }

    private long getCountHomeTeamWinsInSecondHalfTime() {
        return matches.stream()
                .filter(this::isHomeTeamWonInSecondHalfTime)
                .count();
    }

    private long getCountAwayTeamWinsInSecondHalfTime() {
        return matches.stream()
                .filter(this::isAwayTeamWonInSecondHalfTime)
                .count();
    }

    private long getCountDrawsInSecondHalfTime() {
        return matches.stream()
                .filter(this::isDrawInSecondHalfTime)
                .count();
    }

    private boolean isHomeTeamWonInSecondHalfTime(Match match) {

        int homeTeamGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match);
        int awayTeamGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match);

        return homeTeamGoalsScoredInSecondHalfTime > awayTeamGoalsScoredInSecondHalfTime;
    }

    private boolean isAwayTeamWonInSecondHalfTime(Match match) {

        int homeTeamGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match);
        int awayTeamGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match);

        return homeTeamGoalsScoredInSecondHalfTime < awayTeamGoalsScoredInSecondHalfTime;
    }

    private boolean isDrawInSecondHalfTime(Match match) {

        int homeTeamGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getHomeTeamGoalsScoredInSecondHalfTime(match);
        int awayTeamGoalsScoredInSecondHalfTime = NumberOfGoalsCollecter.getAwayTeamGoalsScoredInSecondHalfTime(match);

        return homeTeamGoalsScoredInSecondHalfTime == awayTeamGoalsScoredInSecondHalfTime;
    }

}
