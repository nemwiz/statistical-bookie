package aggregator;

import model.Match;
import viewmodel.MatchDetailOutcomeView;

import java.util.List;

import static helper.Constants.*;

public class MatchDetailOutcomeAggregator extends Aggregator {

    private List<Match> matches;

    public MatchDetailOutcomeAggregator(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public MatchDetailOutcomeView getAggregatedCount() {
        return new MatchDetailOutcomeView(
                countWhenHomeTeamWonOnHalfTimeAndWonInTheEnd(),
                countWhenHomeTeamWonOnHalfTimeAndDrewInTheEnd(),
                countWhenHomeTeamWonOnHalfTimeAndLostInTheEnd(),
                countWhenHalfTimeWasDrawAndHomeTeamWonInTheEnd(),
                countWhenHalfTimeWasDrawAndWasDrawInTheEnd(),
                countWhenHalfTimeWasDrawAndAwayTeamWonInTheEnd(),
                countWhenAwayTeamWonOnHalfTimeAndWonInTheEnd(),
                countWhenAwayTeamWonOnHalfTimeAndDrewInTheEnd(),
                countWhenAwayTeamWonOnHalfTimeAndHomeTeamWonInTheEnd()
        );
    }

    private long countWhenHomeTeamWonOnHalfTimeAndWonInTheEnd() {

        return matches.stream()
                .filter(match -> getMatchPredicate(match, HOME_TEAM_WIN, HOME_TEAM_WIN))
                .count();
    }

    private long countWhenHomeTeamWonOnHalfTimeAndDrewInTheEnd() {

        return matches.stream()
                .filter(match -> getMatchPredicate(match, HOME_TEAM_WIN, DRAW))
                .count();
    }

    private long countWhenHomeTeamWonOnHalfTimeAndLostInTheEnd() {

        return matches.stream()
                .filter(match -> getMatchPredicate(match, HOME_TEAM_WIN, AWAY_TEAM_WIN))
                .count();
    }

    private long countWhenHalfTimeWasDrawAndHomeTeamWonInTheEnd() {

        return matches.stream()
                .filter(match -> getMatchPredicate(match, DRAW, HOME_TEAM_WIN))
                .count();
    }

    private long countWhenHalfTimeWasDrawAndWasDrawInTheEnd() {

        return matches.stream()
                .filter(match -> getMatchPredicate(match, DRAW, DRAW))
                .count();
    }

    private long countWhenHalfTimeWasDrawAndAwayTeamWonInTheEnd() {

        return matches.stream()
                .filter(match -> getMatchPredicate(match, DRAW, AWAY_TEAM_WIN))
                .count();
    }

    private long countWhenAwayTeamWonOnHalfTimeAndWonInTheEnd() {

        return matches.stream()
                .filter(match -> getMatchPredicate(match, AWAY_TEAM_WIN, AWAY_TEAM_WIN))
                .count();
    }

    private long countWhenAwayTeamWonOnHalfTimeAndDrewInTheEnd() {

        return matches.stream()
                .filter(match -> getMatchPredicate(match, AWAY_TEAM_WIN, DRAW))
                .count();
    }

    private long countWhenAwayTeamWonOnHalfTimeAndHomeTeamWonInTheEnd() {

        return matches.stream()
                .filter(match -> getMatchPredicate(match, AWAY_TEAM_WIN, HOME_TEAM_WIN))
                .count();
    }

    private boolean getMatchPredicate(Match match, String halfTimeOutcome, String finalOutcome) {
        return match.getHalfTimeOutcome().equals(halfTimeOutcome) && match.getFinalOutcome().equals(finalOutcome);
    }
}
