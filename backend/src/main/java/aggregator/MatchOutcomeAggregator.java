package aggregator;

import aggregator.Aggregator;
import model.Match;
import viewmodel.MatchOutcomeView;

import java.util.List;

public class MatchOutcomeAggregator extends Aggregator {

    private List<Match> matches;

    public MatchOutcomeAggregator(List<Match> matches) {
        this.matches = matches;
    }

    public MatchOutcomeView getAggregatedCount() {

        long[] countOfMatchOutcomeFullTime = getCountFullTime();
        long[] countOfTeamGoalsHalfTime = getCountHalfTime();

        return mapArrayToViewModel(countOfMatchOutcomeFullTime, countOfTeamGoalsHalfTime);

    }

    private MatchOutcomeView mapArrayToViewModel(long[] countOfMatchOutcomeFullTime, long[] countOfTeamGoalsHalfTime) {
        return new MatchOutcomeView(
                countOfMatchOutcomeFullTime[0],
                countOfMatchOutcomeFullTime[1],
                countOfMatchOutcomeFullTime[2],
                countOfTeamGoalsHalfTime[0],
                countOfTeamGoalsHalfTime[1],
                countOfTeamGoalsHalfTime[2]
        );
    }

    private long[] getCountFullTime() {

        long countMatchesWhereHomeTeamWon = matches.stream()
                .filter(match -> match.getFinalOutcome().equals("H"))
                .count();

        long countMatchesWhereOutcomeWasDraw = matches.stream()
                .filter(match -> match.getFinalOutcome().equals("D"))
                .count();

        long countMatchesWhereAwayTeamWon = matches.stream()
                .filter(match -> match.getFinalOutcome().equals("A"))
                .count();

        return new long[] {
                countMatchesWhereHomeTeamWon,
                countMatchesWhereOutcomeWasDraw,
                countMatchesWhereAwayTeamWon
        };
    }

    private long[] getCountHalfTime() {

        long countMatchesWhereHomeTeamWonOnHalfTime = matches.stream()
                .filter(match -> match.getHalfTimeOutcome().equals("H"))
                .count();

        long countMatchesWhereHalfTimeOutcomeWasDraw = matches.stream()
                .filter(match -> match.getHalfTimeOutcome().equals("D"))
                .count();

        long countMatchesWhereAwayTeamWonOnHalfTime = matches.stream()
                .filter(match -> match.getHalfTimeOutcome().equals("A"))
                .count();

        return new long[] {
                countMatchesWhereHomeTeamWonOnHalfTime,
                countMatchesWhereHalfTimeOutcomeWasDraw,
                countMatchesWhereAwayTeamWonOnHalfTime
        };

    }

}
