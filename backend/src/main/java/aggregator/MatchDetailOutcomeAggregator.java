package aggregator;

import model.Match;
import viewmodel.MatchDetailOutcomeView;

import java.util.List;

public class MatchDetailOutcomeAggregator extends Aggregator {

    private List<Match> matches;

    public MatchDetailOutcomeAggregator(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public MatchDetailOutcomeView getAggregatedCount() {
        return null;
    }
}
