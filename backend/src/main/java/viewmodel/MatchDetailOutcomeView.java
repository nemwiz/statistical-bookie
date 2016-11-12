package viewmodel;

public class MatchDetailOutcomeView implements ViewModel {
    
    private long homeTeamWonOnHalfTimeAndWonInTheEnd;
    private long homeTeamWonOnHalfTimeAndDrewInTheEnd;
    private long homeTeamWonOnHalfTimeAndLostInTheEnd;
    private long halfTimeWasDrawAndHomeTeamWonInTheEnd;
    private long halfTimeWasDrawAndWasDrawInTheEnd;
    private long halfTimeWasDrawAndAwayTeamWonInTheEnd;
    private long awayTeamWonOnHalfTimeAndWonInTheEnd;
    private long awayTeamWonOnHalfTimeAndDrewInTheEnd;
    private long awayTeamWonOnHalfTimeAndLostInTheEnd;

    public MatchDetailOutcomeView(long homeTeamWonOnHalfTimeAndWonInTheEnd, long homeTeamWonOnHalfTimeAndDrewInTheEnd, long homeTeamWonOnHalfTimeAndLostInTheEnd, long halfTimeWasDrawAndHomeTeamWonInTheEnd, long halfTimeWasDrawAndWasDrawInTheEnd, long halfTimeWasDrawAndAwayTeamWonInTheEnd, long awayTeamWonOnHalfTimeAndWonInTheEnd, long awayTeamWonOnHalfTimeAndDrewInTheEnd, long awayTeamWonOnHalfTimeAndLostInTheEnd) {
        this.homeTeamWonOnHalfTimeAndWonInTheEnd = homeTeamWonOnHalfTimeAndWonInTheEnd;
        this.homeTeamWonOnHalfTimeAndDrewInTheEnd = homeTeamWonOnHalfTimeAndDrewInTheEnd;
        this.homeTeamWonOnHalfTimeAndLostInTheEnd = homeTeamWonOnHalfTimeAndLostInTheEnd;
        this.halfTimeWasDrawAndHomeTeamWonInTheEnd = halfTimeWasDrawAndHomeTeamWonInTheEnd;
        this.halfTimeWasDrawAndWasDrawInTheEnd = halfTimeWasDrawAndWasDrawInTheEnd;
        this.halfTimeWasDrawAndAwayTeamWonInTheEnd = halfTimeWasDrawAndAwayTeamWonInTheEnd;
        this.awayTeamWonOnHalfTimeAndWonInTheEnd = awayTeamWonOnHalfTimeAndWonInTheEnd;
        this.awayTeamWonOnHalfTimeAndDrewInTheEnd = awayTeamWonOnHalfTimeAndDrewInTheEnd;
        this.awayTeamWonOnHalfTimeAndLostInTheEnd = awayTeamWonOnHalfTimeAndLostInTheEnd;
    }

    public long getHomeTeamWonOnHalfTimeAndWonInTheEnd() {
        return homeTeamWonOnHalfTimeAndWonInTheEnd;
    }

    public long getHomeTeamWonOnHalfTimeAndDrewInTheEnd() {
        return homeTeamWonOnHalfTimeAndDrewInTheEnd;
    }

    public long getHomeTeamWonOnHalfTimeAndLostInTheEnd() {
        return homeTeamWonOnHalfTimeAndLostInTheEnd;
    }

    public long getHalfTimeWasDrawAndHomeTeamWonInTheEnd() {
        return halfTimeWasDrawAndHomeTeamWonInTheEnd;
    }

    public long getHalfTimeWasDrawAndWasDrawInTheEnd() {
        return halfTimeWasDrawAndWasDrawInTheEnd;
    }

    public long getHalfTimeWasDrawAndAwayTeamWonInTheEnd() {
        return halfTimeWasDrawAndAwayTeamWonInTheEnd;
    }

    public long getAwayTeamWonOnHalfTimeAndWonInTheEnd() {
        return awayTeamWonOnHalfTimeAndWonInTheEnd;
    }

    public long getAwayTeamWonOnHalfTimeAndDrewInTheEnd() {
        return awayTeamWonOnHalfTimeAndDrewInTheEnd;
    }

    public long getAwayTeamWonOnHalfTimeAndLostInTheEnd() {
        return awayTeamWonOnHalfTimeAndLostInTheEnd;
    }

    @Override
    public String toString() {
        return "MatchDetailOutcomeView{" +
                "homeTeamWonOnHalfTimeAndWonInTheEnd=" + homeTeamWonOnHalfTimeAndWonInTheEnd +
                ", homeTeamWonOnHalfTimeAndDrewInTheEnd=" + homeTeamWonOnHalfTimeAndDrewInTheEnd +
                ", homeTeamWonOnHalfTimeAndLostInTheEnd=" + homeTeamWonOnHalfTimeAndLostInTheEnd +
                ", halfTimeWasDrawAndHomeTeamWonInTheEnd=" + halfTimeWasDrawAndHomeTeamWonInTheEnd +
                ", halfTimeWasDrawAndWasDrawInTheEnd=" + halfTimeWasDrawAndWasDrawInTheEnd +
                ", halfTimeWasDrawAndAwayTeamWonInTheEnd=" + halfTimeWasDrawAndAwayTeamWonInTheEnd +
                ", awayTeamWonOnHalfTimeAndWonInTheEnd=" + awayTeamWonOnHalfTimeAndWonInTheEnd +
                ", awayTeamWonOnHalfTimeAndDrewInTheEnd=" + awayTeamWonOnHalfTimeAndDrewInTheEnd +
                ", awayTeamWonOnHalfTimeAndLostInTheEnd=" + awayTeamWonOnHalfTimeAndLostInTheEnd +
                '}';
    }
}
