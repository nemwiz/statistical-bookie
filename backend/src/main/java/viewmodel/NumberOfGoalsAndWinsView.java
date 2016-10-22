package viewmodel;

public class NumberOfGoalsAndWinsView implements ViewModel {

    private long homeTeamWinAndOneGoalScored;
    private long homeTeamWinAndTwoGoalsScored;
    private long homeTeamWinAndThreeGoalsScored;
    private long homeTeamWinAndFourOrMoreGoalsScored;
    private long drawAndOneGoalScored;
    private long drawAndTwoGoalsScored;
    private long drawAndThreeGoalsScored;
    private long drawAndFourOrMoreGoalsScored;
    private long awayTeamWinAndOneGoalScored;
    private long awayTeamWinAndTwoGoalsScored;
    private long awayTeamWinAndThreeGoalsScored;
    private long awayTeamWinAndFourOrMoreGoalsScored;

    public NumberOfGoalsAndWinsView(long homeTeamWinAndOneGoalScored, long homeTeamWinAndTwoGoalsScored, long homeTeamWinAndThreeGoalsScored, long homeTeamWinAndFourOrMoreGoalsScored, long drawAndOneGoalScored, long drawAndTwoGoalsScored, long drawAndThreeGoalsScored, long drawAndFourOrMoreGoalsScored, long awayTeamWinAndOneGoalScored, long awayTeamWinAndTwoGoalsScored, long awayTeamWinAndThreeGoalsScored, long awayTeamWinAndFourOrMoreGoalsScored) {
        this.homeTeamWinAndOneGoalScored = homeTeamWinAndOneGoalScored;
        this.homeTeamWinAndTwoGoalsScored = homeTeamWinAndTwoGoalsScored;
        this.homeTeamWinAndThreeGoalsScored = homeTeamWinAndThreeGoalsScored;
        this.homeTeamWinAndFourOrMoreGoalsScored = homeTeamWinAndFourOrMoreGoalsScored;
        this.drawAndOneGoalScored = drawAndOneGoalScored;
        this.drawAndTwoGoalsScored = drawAndTwoGoalsScored;
        this.drawAndThreeGoalsScored = drawAndThreeGoalsScored;
        this.drawAndFourOrMoreGoalsScored = drawAndFourOrMoreGoalsScored;
        this.awayTeamWinAndOneGoalScored = awayTeamWinAndOneGoalScored;
        this.awayTeamWinAndTwoGoalsScored = awayTeamWinAndTwoGoalsScored;
        this.awayTeamWinAndThreeGoalsScored = awayTeamWinAndThreeGoalsScored;
        this.awayTeamWinAndFourOrMoreGoalsScored = awayTeamWinAndFourOrMoreGoalsScored;
    }

    public long getHomeTeamWinAndOneGoalScored() {
        return homeTeamWinAndOneGoalScored;
    }

    public long getHomeTeamWinAndTwoGoalsScored() {
        return homeTeamWinAndTwoGoalsScored;
    }

    public long getHomeTeamWinAndThreeGoalsScored() {
        return homeTeamWinAndThreeGoalsScored;
    }

    public long getHomeTeamWinAndFourOrMoreGoalsScored() {
        return homeTeamWinAndFourOrMoreGoalsScored;
    }

    public long getDrawAndOneGoalScored() {
        return drawAndOneGoalScored;
    }

    public long getDrawAndTwoGoalsScored() {
        return drawAndTwoGoalsScored;
    }

    public long getDrawAndThreeGoalsScored() {
        return drawAndThreeGoalsScored;
    }

    public long getDrawAndFourOrMoreGoalsScored() {
        return drawAndFourOrMoreGoalsScored;
    }

    public long getAwayTeamWinAndOneGoalScored() {
        return awayTeamWinAndOneGoalScored;
    }

    public long getAwayTeamWinAndTwoGoalsScored() {
        return awayTeamWinAndTwoGoalsScored;
    }

    public long getAwayTeamWinAndThreeGoalsScored() {
        return awayTeamWinAndThreeGoalsScored;
    }

    public long getAwayTeamWinAndFourOrMoreGoalsScored() {
        return awayTeamWinAndFourOrMoreGoalsScored;
    }

    @Override
    public String toString() {
        return "NumberOfGoalsAndWinsView{" +
                "homeTeamWinAndOneGoalScored=" + homeTeamWinAndOneGoalScored +
                ", homeTeamWinAndTwoGoalsScored=" + homeTeamWinAndTwoGoalsScored +
                ", homeTeamWinAndThreeGoalsScored=" + homeTeamWinAndThreeGoalsScored +
                ", homeTeamWinAndFourOrMoreGoalsScored=" + homeTeamWinAndFourOrMoreGoalsScored +
                ", drawAndOneGoalScored=" + drawAndOneGoalScored +
                ", drawAndTwoGoalsScored=" + drawAndTwoGoalsScored +
                ", drawAndThreeGoalsScored=" + drawAndThreeGoalsScored +
                ", drawAndFourOrMoreGoalsScored=" + drawAndFourOrMoreGoalsScored +
                ", awayTeamWinAndOneGoalScored=" + awayTeamWinAndOneGoalScored +
                ", awayTeamWinAndTwoGoalsScored=" + awayTeamWinAndTwoGoalsScored +
                ", awayTeamWinAndThreeGoalsScored=" + awayTeamWinAndThreeGoalsScored +
                ", awayTeamWinAndFourOrMoreGoalsScored=" + awayTeamWinAndFourOrMoreGoalsScored +
                '}';
    }
}
