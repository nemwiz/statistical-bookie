import aggregator.MatchOutcomeAggregator;
import model.Match;
import org.junit.Before;
import org.junit.Test;
import viewmodel.MatchOutcomeView;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class MatchOutcomeAggregatorTest {

    private static String HOME_TEAM_WIN = "H";
    private static String DRAW = "D";
    private static String AWAY_TEAM_WIN = "A";

    private MatchOutcomeAggregator matchOutcomeAggregator;
    private MatchOutcomeView matchOutcomeView;
    private List<Match> matches;

    @Before
    public void setUp() {
        matches = new ArrayList<>();
    }

    @Test
    public void checkThatCountOfHomeTeamWinAtFullAndHalfTimeIsCorrect() {

        long countOfMatches = 3;
        createMatches(countOfMatches, HOME_TEAM_WIN);
        matchOutcomeAggregator = new MatchOutcomeAggregator(matches);

        matchOutcomeView = matchOutcomeAggregator.getAggregatedCount();

        assertEquals(countOfMatches, matchOutcomeView.getHomeTeamWinFullTime());
        assertEquals(countOfMatches, matchOutcomeView.getHomeTeamWinHalfTime());
    }

    @Test
    public void checkThatCountOfDrawMatchesAtFullAndHalfTimeIsCorrect() {

        long countOfDrawMatches = 2;
        createMatches(countOfDrawMatches, DRAW);
        matchOutcomeAggregator = new MatchOutcomeAggregator(matches);

        matchOutcomeView = matchOutcomeAggregator.getAggregatedCount();

        assertEquals(countOfDrawMatches, matchOutcomeView.getDrawFullTime());
        assertEquals(countOfDrawMatches, matchOutcomeView.getDrawHalfTime());
    }

    @Test
    public void checkThatCountOfAwayTeamWinAtFullAndHalfTimeIsCorrect() {

        long countOfAwayTeamWinMatches = 5;
        createMatches(countOfAwayTeamWinMatches, AWAY_TEAM_WIN);
        matchOutcomeAggregator = new MatchOutcomeAggregator(matches);

        matchOutcomeView = matchOutcomeAggregator.getAggregatedCount();

        assertEquals(countOfAwayTeamWinMatches, matchOutcomeView.getAwayTeamWinFullTime());
        assertEquals(countOfAwayTeamWinMatches, matchOutcomeView.getAwayTeamWinHalfTime());
    }

    @Test
    public void checkThatCountOfMatchWinAtFullAndHalfTimeIsZeroWhenNoMatches() {

        long expectedCount = 0;
        matchOutcomeAggregator = new MatchOutcomeAggregator(matches);

        matchOutcomeView = matchOutcomeAggregator.getAggregatedCount();

        assertEquals(expectedCount, matchOutcomeView.getAwayTeamWinFullTime());
        assertEquals(expectedCount, matchOutcomeView.getAwayTeamWinHalfTime());
        assertEquals(expectedCount, matchOutcomeView.getHomeTeamWinFullTime());
        assertEquals(expectedCount, matchOutcomeView.getHomeTeamWinHalfTime());
        assertEquals(expectedCount, matchOutcomeView.getDrawFullTime());
        assertEquals(expectedCount, matchOutcomeView.getDrawHalfTime());
    }

    private void createMatches(long numberOfMatchesToAdd, String halfTimeAndFinalOutcome) {

        for(int i=0; i < numberOfMatchesToAdd; i++) {

            Match match = new Match();

            switch (halfTimeAndFinalOutcome) {

                case "H":
                    match.setFinalOutcome(HOME_TEAM_WIN);
                    match.setHalfTimeOutcome(HOME_TEAM_WIN);
                    break;
                case "D":
                    match.setFinalOutcome(DRAW);
                    match.setHalfTimeOutcome(DRAW);
                    break;
                case "A":
                    match.setFinalOutcome(AWAY_TEAM_WIN);
                    match.setHalfTimeOutcome(AWAY_TEAM_WIN);
                    break;
            }

            matches.add(match);

        }

    }


}
