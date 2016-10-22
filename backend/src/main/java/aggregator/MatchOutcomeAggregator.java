package aggregator;

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
                getCountOnFullTime("H"),
                getCountOnFullTime("D"),
                getCountOnFullTime("A"),
                getCountOnHalfTime("H"),
                getCountOnHalfTime("D"),
                getCountOnHalfTime("A")
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
