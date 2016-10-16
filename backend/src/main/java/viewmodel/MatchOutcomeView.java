package viewmodel;

public class MatchOutcomeView implements ViewModel {

    private long homeTeamWinFullTime;
    private long drawFullTime;
    private long awayTeamWinFullTime;
    private long homeTeamWinHalfTime;
    private long drawHalfTime;
    private long awayTeamWinHalfTime;

    public MatchOutcomeView(long homeTeamWinFullTime, long drawFullTime, long awayTeamWinFullTime, long homeTeamWinHalfTime, long drawHalfTime, long awayTeamWinHalfTime) {
        this.homeTeamWinFullTime = homeTeamWinFullTime;
        this.drawFullTime = drawFullTime;
        this.awayTeamWinFullTime = awayTeamWinFullTime;
        this.homeTeamWinHalfTime = homeTeamWinHalfTime;
        this.drawHalfTime = drawHalfTime;
        this.awayTeamWinHalfTime = awayTeamWinHalfTime;
    }

    @Override
    public String toString() {
        return "MatchOutcomeView{" +
                "homeTeamWinFullTime=" + homeTeamWinFullTime +
                ", drawFullTime=" + drawFullTime +
                ", awayTeamWinFullTime=" + awayTeamWinFullTime +
                ", homeTeamWinHalfTime=" + homeTeamWinHalfTime +
                ", drawHalfTime=" + drawHalfTime +
                ", awayTeamWinHalfTime=" + awayTeamWinHalfTime +
                '}';
    }
}
