package viewmodel;

public class TeamGoalsView implements ViewModel{

    private long homeTeamScored;
    private long awayTeamScored;
    private long bothTeamsScored;
    private long homeTeamScoredHalfTime;
    private long awayTeamScoredHalfTime;
    private long bothTeamsScoredHalfTime;

    public TeamGoalsView(long homeTeamScored, long awayTeamScored, long bothTeamsScored, long homeTeamScoredHalfTime, long awayTeamScoredHalfTime, long bothTeamsScoredHalfTime) {
        this.homeTeamScored = homeTeamScored;
        this.awayTeamScored = awayTeamScored;
        this.bothTeamsScored = bothTeamsScored;
        this.homeTeamScoredHalfTime = homeTeamScoredHalfTime;
        this.awayTeamScoredHalfTime = awayTeamScoredHalfTime;
        this.bothTeamsScoredHalfTime = bothTeamsScoredHalfTime;
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

    @Override
    public String toString() {
        return "TeamGoalsView{" +
                "homeTeamScored=" + homeTeamScored +
                ", awayTeamScored=" + awayTeamScored +
                ", bothTeamsScored=" + bothTeamsScored +
                ", homeTeamScoredHalfTime=" + homeTeamScoredHalfTime +
                ", awayTeamScoredHalfTime=" + awayTeamScoredHalfTime +
                ", bothTeamsScoredHalfTime=" + bothTeamsScoredHalfTime +
                '}';
    }
}
