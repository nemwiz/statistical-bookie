package aggregator;

import model.Match;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExactResultAggregator {

    private List<Match> matches;

    public ExactResultAggregator(List<Match> matches) {
        this.matches = matches;
    }

    public Map<String, Long> aggregate() {

        return this.matches.stream()
                .map(match -> getMatchExactResultAsString(match))
                .collect(Collectors.groupingBy(exactResult -> exactResult, Collectors.counting()));
    }

    private String getMatchExactResultAsString(Match match) {

        return String.valueOf(match.getHomeTeamGoals()) + ":" + String.valueOf(match.getAwayTeamGoals());
    }

}
