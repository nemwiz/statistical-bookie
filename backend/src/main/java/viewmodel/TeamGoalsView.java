package viewmodel;

public class TeamGoalsView implements ViewModel{

    private long homeTeamScored;
    private long awayTeamScored;
    private long bothTeamsScored;
    private long homeTeamScoredHalfTime;
    private long awayTeamScoredHalfTime;
    private long bothTeamsScoredHalfTime;
    private long homeTeamScoredInSecondHalfTime;
    private long awayTeamScoredInSecondHalfTime;
    private long bothTeamsScoredInSecondHalfTime;

    public TeamGoalsView(long homeTeamScored, long awayTeamScored, long bothTeamsScored, long homeTeamScoredHalfTime, long awayTeamScoredHalfTime, long bothTeamsScoredHalfTime, long homeTeamScoredInSecondHalfTime, long awayTeamScoredInSecondHalfTime, long bothTeamsScoredInSecondHalfTime) {
        this.homeTeamScored = homeTeamScored;
        this.awayTeamScored = awayTeamScored;
        this.bothTeamsScored = bothTeamsScored;
        this.homeTeamScoredHalfTime = homeTeamScoredHalfTime;
        this.awayTeamScoredHalfTime = awayTeamScoredHalfTime;
        this.bothTeamsScoredHalfTime = bothTeamsScoredHalfTime;
        this.homeTeamScoredInSecondHalfTime = homeTeamScoredInSecondHalfTime;
        this.awayTeamScoredInSecondHalfTime = awayTeamScoredInSecondHalfTime;
        this.bothTeamsScoredInSecondHalfTime = bothTeamsScoredInSecondHalfTime;
    }

    public long getHomeTeamScored() {
        return homeTeamScored;
    }

    public long getAwayTeamScored() {
        return awayTeamScored;
    }

    public long getBothTeamsScored() {
        return bothTeamsScored;
    }

    public long getHomeTeamScoredHalfTime() {
        return homeTeamScoredHalfTime;
    }

    public long getAwayTeamScoredHalfTime() {
        return awayTeamScoredHalfTime;
    }

    public long getBothTeamsScoredHalfTime() {
        return bothTeamsScoredHalfTime;
    }

    public long getHomeTeamScoredInSecondHalfTime() {
        return homeTeamScoredInSecondHalfTime;
    }

    public long getAwayTeamScoredInSecondHalfTime() {
        return awayTeamScoredInSecondHalfTime;
    }

    public long getBothTeamsScoredInSecondHalfTime() {
        return bothTeamsScoredInSecondHalfTime;
    }

    @Override
    public String toString() {
        return "TeamGoalsView{" +
                "homeTeamScored=" + homeTeamScored +
                ", awayTeamScored=" + awayTeamScored +
                ", bothTeamsScored=" + bothTeamsScored +
                ", homeTeamScoredHalfTime=" + homeTeamScoredHalfTime +
                ", awayTeamScoredHalfTime=" + awayTeamScoredHalfTime +
                ", bothTeamsScoredHalfTime=" + bothTeamsScoredHalfTime +
                ", homeTeamScoredInSecondHalfTime=" + homeTeamScoredInSecondHalfTime +
                ", awayTeamScoredInSecondHalfTime=" + awayTeamScoredInSecondHalfTime +
                ", bothTeamsScoredInSecondHalfTime=" + bothTeamsScoredInSecondHalfTime +
                '}';
    }
}
