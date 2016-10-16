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
