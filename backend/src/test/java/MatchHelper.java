import model.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MatchHelper {

    public static String HOME_TEAM_WIN = "H";
    public static String DRAW = "D";
    public static String AWAY_TEAM_WIN = "A";

    private Match match;

    public MatchHelper(Match match) {
        this.match = match;
    }

    public void setUpMatchGoals(int homeTeamGoals, int awayTeamGoals) {
        this.match.setHomeTeamGoals(homeTeamGoals);
        this.match.setAwayTeamGoals(awayTeamGoals);
    }

    public void setUpMatchHalfTimeGoals(int homeTeamHalfTimeGoals, int awayTeamHalfTimeGoals) {
        this.match.setHomeTeamHalftimeGoals(homeTeamHalfTimeGoals);
        this.match.setAwayTeamHalftimeGoals(awayTeamHalfTimeGoals);
    }

    public static int homeTeamGoals(int homeTeamGoals) {
        return homeTeamGoals;
    }

    public static int awayTeamGoals(int awayTeamGoals) {
        return awayTeamGoals;
    }

    public static int homeTeamHalfTimeGoals(int homeTeamHalfTimeGoals) {
        return homeTeamHalfTimeGoals;
    }

    public static int awayTeamHalfTimeGoals(int awayTeamHalfTimeGoals) {
        return awayTeamHalfTimeGoals;
    }

    private void addMatchesWithFinalOutcome(String finalOutcome, long numberOfMatchesToAdd, List<Match> matches) {

        for (long i = 0; i < numberOfMatchesToAdd; i++) {
            Match match = new Match();
            match.setFinalOutcome(finalOutcome);
            matches.add(match);
        }

    }

    private void addMatchesWithHalfTimeOutcome(String halfTimeOutcome, long numberOfMatchesToAdd, List<Match> matches) {

        for (long i = 0; i < numberOfMatchesToAdd; i++) {
            Match match = new Match();
            match.setHalfTimeOutcome(halfTimeOutcome);
            matches.add(match);
        }

    }

    public List<Match> setUpFullTimeMatches(long numberOfMatches) {

        List<Match> matches = new ArrayList<>();

        addMatchesWithFinalOutcome(HOME_TEAM_WIN, numberOfMatches, matches);
        addMatchesWithFinalOutcome(DRAW, numberOfMatches, matches);
        addMatchesWithFinalOutcome(AWAY_TEAM_WIN, numberOfMatches, matches);

        return matches;
    }

    public List<Match> setUpHalfTimeMatches(long numberOfMatches) {

        List<Match> matches = new ArrayList<>();

        addMatchesWithHalfTimeOutcome(HOME_TEAM_WIN, numberOfMatches, matches);
        addMatchesWithHalfTimeOutcome(DRAW, numberOfMatches, matches);
        addMatchesWithHalfTimeOutcome(AWAY_TEAM_WIN, numberOfMatches, matches);

        return matches;
    }

    public static Match createMatchWithFinalAndHalfTimeOutcome(String finalOutcome, String halfTimeOutcome) {

        Match match = new Match();
        match.setFinalOutcome(finalOutcome);
        match.setHalfTimeOutcome(halfTimeOutcome);

        return match;

    }

}
