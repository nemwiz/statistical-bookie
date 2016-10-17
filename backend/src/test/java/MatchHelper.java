import model.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchHelper {

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

}
