package collecter.model;

public class TeamGoalsModel {
    private boolean homeTeamScored;
    private boolean awayTeamScored;
    private boolean bothTeamsScored;
    private boolean homeTeamScoredOnHalfTime;
    private boolean awayTeamScoredOnHalfTime;
    private boolean bothTeamsScoredOnHalfTime;
    private boolean homeTeamScoredInSecondHalfTime;
    private boolean awayTeamScoredInSecondHalfTime;
    private boolean bothTeamsScoredInSecondHalfTime;

    public TeamGoalsModel(boolean homeTeamScored, boolean awayTeamScored, boolean bothTeamsScored, boolean homeTeamScoredOnHalfTime, boolean awayTeamScoredOnHalfTime, boolean bothTeamsScoredOnHalfTime, boolean homeTeamScoredInSecondHalfTime, boolean awayTeamScoredInSecondHalfTime, boolean bothTeamsScoredInSecondHalfTime) {
        this.homeTeamScored = homeTeamScored;
        this.awayTeamScored = awayTeamScored;
        this.bothTeamsScored = bothTeamsScored;
        this.homeTeamScoredOnHalfTime = homeTeamScoredOnHalfTime;
        this.awayTeamScoredOnHalfTime = awayTeamScoredOnHalfTime;
        this.bothTeamsScoredOnHalfTime = bothTeamsScoredOnHalfTime;
        this.homeTeamScoredInSecondHalfTime = homeTeamScoredInSecondHalfTime;
        this.awayTeamScoredInSecondHalfTime = awayTeamScoredInSecondHalfTime;
        this.bothTeamsScoredInSecondHalfTime = bothTeamsScoredInSecondHalfTime;
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

    public boolean isHomeTeamScoredInSecondHalfTime() {
        return homeTeamScoredInSecondHalfTime;
    }

    public boolean isAwayTeamScoredInSecondHalfTime() {
        return awayTeamScoredInSecondHalfTime;
    }

    public boolean isBothTeamsScoredInSecondHalfTime() {
        return bothTeamsScoredInSecondHalfTime;
    }
}
