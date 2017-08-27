package aggregator;

import model.Match;
import viewmodel.HalfTimeFullTime;

import java.util.List;

import static helper.Constants.*;

public class MatchDetailOutcomeAggregator {

    private List<Match> matches;

    public MatchDetailOutcomeAggregator() {
    }

    public HalfTimeFullTime getAggregatedCount(List<Match> matches) {

        this.matches = matches;

        return new HalfTimeFullTime(
                countMatches(HOME_TEAM_WIN, HOME_TEAM_WIN),
                countMatches(HOME_TEAM_WIN, DRAW),
                countMatches(HOME_TEAM_WIN, AWAY_TEAM_WIN),
                countMatches(DRAW, HOME_TEAM_WIN),
                countMatches(DRAW, DRAW),
                countMatches(DRAW, AWAY_TEAM_WIN),
                countMatches(AWAY_TEAM_WIN, AWAY_TEAM_WIN),
                countMatches(AWAY_TEAM_WIN, DRAW),
                countMatches(AWAY_TEAM_WIN, HOME_TEAM_WIN)
        );
    }

    private long countMatches(String conditionOne, String conditionTwo) {
        return this.matches.stream()
                .filter(match -> getMatchPredicate(match, conditionOne, conditionTwo))
                .count();
    }

    private boolean getMatchPredicate(Match match, String halfTimeOutcome, String finalOutcome) {
        return match.getHalfTimeOutcome().equals(halfTimeOutcome) && match.getFinalOutcome().equals(finalOutcome);
    }
}
