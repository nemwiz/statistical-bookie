import model.Match;

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
}
