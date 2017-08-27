import aggregator.MatchDetailOutcomeAggregator;
import model.Match;
import org.junit.Before;
import org.junit.Test;
import viewmodel.HalfTimeFullTime;

import java.util.ArrayList;
import java.util.List;

import static helper.Constants.*;
import static junit.framework.TestCase.assertEquals;

public class MatchDetailOutcomeAggregatorTest {

    private MatchDetailOutcomeAggregator matchDetailOutcomeAggregator;
    private HalfTimeFullTime halfTimeFullTime;
    private List<Match> matches;
    private long countOfMatches;

    @Before
    public void setUp() {
        matches = new ArrayList<>();
    }

    @Test
    public void countIsCorrectWhenHomeTeamWonOnHalfTimeAndWonInTheEnd() {

        countOfMatches = 5;
        setUpWithParameters(countOfMatches, HOME_TEAM_WIN, HOME_TEAM_WIN);

        assertEquals(countOfMatches, halfTimeFullTime.getHomeTeamWonOnHalfTimeAndWonInTheEnd());
    }

    @Test
    public void countIsCorrectWhenHomeTeamWonOnHalfTimeAndDrewInTheEnd() {

        countOfMatches = 3;
        setUpWithParameters(countOfMatches, HOME_TEAM_WIN, DRAW);

        assertEquals(countOfMatches, halfTimeFullTime.getHomeTeamWonOnHalfTimeAndDrewInTheEnd());
    }

    @Test
    public void countIsCorrectWhenHomeTeamWonOnHalfTimeAndLostInTheEnd() {

        countOfMatches = 4;
        setUpWithParameters(countOfMatches, HOME_TEAM_WIN, AWAY_TEAM_WIN);

        assertEquals(countOfMatches, halfTimeFullTime.getHomeTeamWonOnHalfTimeAndLostInTheEnd());
    }

    @Test
    public void countIsCorrectWhenHalfTimeWasDrawAndHomeTeamWonInTheEnd() {

        countOfMatches = 14;
        setUpWithParameters(countOfMatches, DRAW, HOME_TEAM_WIN);

        assertEquals(countOfMatches, halfTimeFullTime.getHalfTimeWasDrawAndHomeTeamWonInTheEnd());
    }

    @Test
    public void countIsCorrectWhenHalfTimeWasDrawAndWasDrawInTheEnd() {

        countOfMatches = 2;
        setUpWithParameters(countOfMatches, DRAW, DRAW);

        assertEquals(countOfMatches, halfTimeFullTime.getHalfTimeWasDrawAndWasDrawInTheEnd());
    }

    @Test
    public void countIsCorrectWhenHalfTimeWasDrawAndAwayTeamWonInTheEnd() {

        countOfMatches = 8;
        setUpWithParameters(countOfMatches, DRAW, AWAY_TEAM_WIN);

        assertEquals(countOfMatches, halfTimeFullTime.getHalfTimeWasDrawAndAwayTeamWonInTheEnd());
    }

    @Test
    public void countIsCorrectWhenAwayTeamWonOnHalfTimeAndWonInTheEnd() {

        countOfMatches = 5;
        setUpWithParameters(countOfMatches, AWAY_TEAM_WIN, AWAY_TEAM_WIN);

        assertEquals(countOfMatches, halfTimeFullTime.getAwayTeamWonOnHalfTimeAndWonInTheEnd());
    }

    @Test
    public void countIsCorrectWhenAwayTeamWonOnHalfTimeAndDrewInTheEnd() {

        countOfMatches = 3;
        setUpWithParameters(countOfMatches, AWAY_TEAM_WIN, DRAW);

        assertEquals(countOfMatches, halfTimeFullTime.getAwayTeamWonOnHalfTimeAndDrewInTheEnd());
    }

    @Test
    public void countIsCorrectWhenAwayTeamWonOnHalfTimeAndLostInTheEnd() {

        countOfMatches = 9;
        setUpWithParameters(countOfMatches, AWAY_TEAM_WIN, HOME_TEAM_WIN);

        assertEquals(countOfMatches, halfTimeFullTime.getAwayTeamWonOnHalfTimeAndLostInTheEnd());
    }

    private long setUpWithParameters(long countOfMatches, String halfTimeOutcome, String finalOutcome) {
        createMatches(countOfMatches, halfTimeOutcome, finalOutcome);
        matchDetailOutcomeAggregator = new MatchDetailOutcomeAggregator();
        halfTimeFullTime = matchDetailOutcomeAggregator.getAggregatedCount(this.matches);
        return countOfMatches;
    }

    private void createMatches(long numberOfMatchesToCreate, String halfTimeOutcome, String finalOutcome) {

        for(int i=0; i < numberOfMatchesToCreate; i++) {

            Match match = new Match();

            switch (halfTimeOutcome) {

                case "H":
                    match.setHalfTimeOutcome(HOME_TEAM_WIN);
                    break;
                case "D":
                    match.setHalfTimeOutcome(DRAW);
                    break;
                case "A":
                    match.setHalfTimeOutcome(AWAY_TEAM_WIN);
                    break;
            }

            switch (finalOutcome) {

                case "H":
                    match.setFinalOutcome(HOME_TEAM_WIN);
                    break;
                case "D":
                    match.setFinalOutcome(DRAW);
                    break;
                case "A":
                    match.setFinalOutcome(AWAY_TEAM_WIN);
                    break;
            }

            matches.add(match);

        }

    }

}
