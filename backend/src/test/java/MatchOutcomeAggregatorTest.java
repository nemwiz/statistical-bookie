import aggregator.MatchOutcomeAggregator;
import model.Match;
import org.junit.Before;
import org.junit.Test;
import viewmodel.MatchOutcomeView;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class MatchOutcomeAggregatorTest {

    public static String HOME_TEAM_WIN = "H";
    public static String DRAW = "D";
    public static String AWAY_TEAM_WIN = "A";

    private MatchOutcomeAggregator matchOutcomeAggregator;
    private MatchOutcomeView matchOutcomeView;
    private Match match;
    private MatchHelper matchHelper;
    private List<Match> matches;

    @Before
    public void setUp() {
        match = new Match();
        matchHelper = new MatchHelper(match);
        matches = new ArrayList<>();
    }

    @Test
    public void checkThatCountOfHomeTeamWinAtFullAndHalfTimeIsCorrect() {

        long countOfHomeTeamWinMatches = 3;
        matches.add(MatchHelper.createMatchWithFinalAndHalfTimeOutcome(HOME_TEAM_WIN, HOME_TEAM_WIN));
        matches.add(MatchHelper.createMatchWithFinalAndHalfTimeOutcome(HOME_TEAM_WIN, HOME_TEAM_WIN));
        matches.add(MatchHelper.createMatchWithFinalAndHalfTimeOutcome(HOME_TEAM_WIN, HOME_TEAM_WIN));
        matchOutcomeAggregator = new MatchOutcomeAggregator(matches);

        matchOutcomeView = matchOutcomeAggregator.getAggregatedCount();

        assertEquals(countOfHomeTeamWinMatches, matchOutcomeView.getHomeTeamWinFullTime());
        assertEquals(countOfHomeTeamWinMatches, matchOutcomeView.getHomeTeamWinHalfTime());
    }

    @Test
    public void checkThatCountOfDrawMatchesAtFullAndHalfTimeIsCorrect() {

        long countOfDrawMatches = 2;
        matches.add(MatchHelper.createMatchWithFinalAndHalfTimeOutcome(DRAW, DRAW));
        matches.add(MatchHelper.createMatchWithFinalAndHalfTimeOutcome(DRAW, DRAW));
        matchOutcomeAggregator = new MatchOutcomeAggregator(matches);

        matchOutcomeView = matchOutcomeAggregator.getAggregatedCount();

        assertEquals(countOfDrawMatches, matchOutcomeView.getDrawFullTime());
        assertEquals(countOfDrawMatches, matchOutcomeView.getDrawHalfTime());
    }

    @Test
    public void checkThatCountOfAwayTeamWinAtFullAndHalfTimeIsCorrect() {

        long countOfAwayTeamWinMatches = 2;
        matches.add(MatchHelper.createMatchWithFinalAndHalfTimeOutcome(AWAY_TEAM_WIN, AWAY_TEAM_WIN));
        matches.add(MatchHelper.createMatchWithFinalAndHalfTimeOutcome(AWAY_TEAM_WIN, AWAY_TEAM_WIN));
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



}
