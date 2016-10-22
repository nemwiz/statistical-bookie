package aggregator;

import model.Match;
import viewmodel.MatchOutcomeView;

import java.util.List;

public class MatchOutcomeAggregator extends Aggregator {

    public static final String HOME_TEAM_WIN = "H";
    public static final String DRAW = "D";
    public static final String AWAY_TEAM_WIN = "A";
    private List<Match> matches;

    public MatchOutcomeAggregator(List<Match> matches) {
        this.matches = matches;
    }

    public MatchOutcomeView getAggregatedCount() {

        return new MatchOutcomeView(
                getCountOnFullTime(HOME_TEAM_WIN),
                getCountOnFullTime(DRAW),
                getCountOnFullTime(AWAY_TEAM_WIN),
                getCountOnHalfTime(HOME_TEAM_WIN),
                getCountOnHalfTime(DRAW),
                getCountOnHalfTime(AWAY_TEAM_WIN)
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
