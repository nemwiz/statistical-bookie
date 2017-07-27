package aggregator;

import model.Match;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExactResultAggregator {

    public ExactResultAggregator() {
    }

    public Map<String, Long> aggregate(List<Match> matches) {

        return matches.stream()
                .map(this::getMatchExactResultAsString)
                .collect(Collectors.groupingBy(exactResult -> exactResult, Collectors.counting()));
    }

    private String getMatchExactResultAsString(Match match) {
        return String.valueOf(match.getHomeTeamGoals()) + ":" + String.valueOf(match.getAwayTeamGoals());
    }

}
