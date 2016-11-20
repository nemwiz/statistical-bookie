package viewmodel;

public class MatchOutcomeView implements ViewModel {

    private long homeTeamWinFullTime;
    private long drawFullTime;
    private long awayTeamWinFullTime;
    private long homeTeamWinHalfTime;
    private long drawHalfTime;
    private long awayTeamWinHalfTime;
    private long homeTeamWinSecondHalfTime;
    private long awayTeamWinSecondHalfTime;
    private long drawSecondHalfTime;

    public MatchOutcomeView(long homeTeamWinFullTime, long drawFullTime, long awayTeamWinFullTime, long homeTeamWinHalfTime, long drawHalfTime, long awayTeamWinHalfTime, long homeTeamWinSecondHalfTime, long awayTeamWinSecondHalfTime, long drawSecondHalfTime) {
        this.homeTeamWinFullTime = homeTeamWinFullTime;
        this.drawFullTime = drawFullTime;
        this.awayTeamWinFullTime = awayTeamWinFullTime;
        this.homeTeamWinHalfTime = homeTeamWinHalfTime;
        this.drawHalfTime = drawHalfTime;
        this.awayTeamWinHalfTime = awayTeamWinHalfTime;
        this.homeTeamWinSecondHalfTime = homeTeamWinSecondHalfTime;
        this.awayTeamWinSecondHalfTime = awayTeamWinSecondHalfTime;
        this.drawSecondHalfTime = drawSecondHalfTime;
    }

    public long getHomeTeamWinFullTime() {
        return homeTeamWinFullTime;
    }

    public long getDrawFullTime() {
        return drawFullTime;
    }

    public long getAwayTeamWinFullTime() {
        return awayTeamWinFullTime;
    }

    public long getHomeTeamWinHalfTime() {
        return homeTeamWinHalfTime;
    }

    public long getDrawHalfTime() {
        return drawHalfTime;
    }

    public long getAwayTeamWinHalfTime() {
        return awayTeamWinHalfTime;
    }

    public long getHomeTeamWinSecondHalfTime() {
        return homeTeamWinSecondHalfTime;
    }

    public long getAwayTeamWinSecondHalfTime() {
        return awayTeamWinSecondHalfTime;
    }

    public long getDrawSecondHalfTime() {
        return drawSecondHalfTime;
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
                ", homeTeamWinSecondHalfTime=" + homeTeamWinSecondHalfTime +
                ", awayTeamWinSecondHalfTime=" + awayTeamWinSecondHalfTime +
                ", drawSecondHalfTime=" + drawSecondHalfTime +
                '}';
    }
}
