package aggregator;

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
                getCountOnHalfTime(Constants.AWAY_TEAM_WIN)
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

}
