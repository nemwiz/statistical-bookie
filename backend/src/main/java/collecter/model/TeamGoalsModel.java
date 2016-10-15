package collecter.model;

public class TeamGoalsModel {
    private boolean homeTeamScored;
    private boolean awayTeamScored;
    private boolean bothTeamsScored;
    private boolean homeTeamScoredOnHalfTime;
    private boolean awayTeamScoredOnHalfTime;
    private boolean bothTeamsScoredOnHalfTime;

    public TeamGoalsModel(boolean homeTeamScored, boolean awayTeamScored, boolean bothTeamsScored, boolean homeTeamScoredOnHalfTime, boolean awayTeamScoredOnHalfTime, boolean bothTeamsScoredOnHalfTime) {
        this.homeTeamScored = homeTeamScored;
        this.awayTeamScored = awayTeamScored;
        this.bothTeamsScored = bothTeamsScored;
        this.homeTeamScoredOnHalfTime = homeTeamScoredOnHalfTime;
        this.awayTeamScoredOnHalfTime = awayTeamScoredOnHalfTime;
        this.bothTeamsScoredOnHalfTime = bothTeamsScoredOnHalfTime;
    }

    public boolean isHomeTeamScored() {
        return homeTeamScored;
    }

    public boolean isAwayTeamScored() {
        return awayTeamScored;
    }

    public boolean isBothTeamsScored() {
        return bothTeamsScored;
    }

    public boolean isHomeTeamScoredOnHalfTime() {
        return homeTeamScoredOnHalfTime;
    }

    public boolean isAwayTeamScoredOnHalfTime() {
        return awayTeamScoredOnHalfTime;
    }

    public boolean isBothTeamsScoredOnHalfTime() {
        return bothTeamsScoredOnHalfTime;
    }
}
