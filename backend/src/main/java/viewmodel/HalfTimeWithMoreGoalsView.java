package viewmodel;

public class HalfTimeWithMoreGoalsView implements ViewModel {
    private long moreGoalsWillBeScoredInFirstHalfTime;
    private long moreGoalsWillBeScoredInSecondtHalfTime;
    private long evenNumberOfGoalsWillBeScoredInBothHalfTimes;

    public HalfTimeWithMoreGoalsView(long moreGoalsWillBeScoredInFirstHalfTime, long moreGoalsWillBeScoredInSecondtHalfTime, long evenNumberOfGoalsWillBeScoredInBothHalfTimes) {
        this.moreGoalsWillBeScoredInFirstHalfTime = moreGoalsWillBeScoredInFirstHalfTime;
        this.moreGoalsWillBeScoredInSecondtHalfTime = moreGoalsWillBeScoredInSecondtHalfTime;
        this.evenNumberOfGoalsWillBeScoredInBothHalfTimes = evenNumberOfGoalsWillBeScoredInBothHalfTimes;
    }

    public long getMoreGoalsWillBeScoredInFirstHalfTime() {
        return moreGoalsWillBeScoredInFirstHalfTime;
    }

    public long getMoreGoalsWillBeScoredInSecondtHalfTime() {
        return moreGoalsWillBeScoredInSecondtHalfTime;
    }

    public long getEvenNumberOfGoalsWillBeScoredInBothHalfTimes() {
        return evenNumberOfGoalsWillBeScoredInBothHalfTimes;
    }

    @Override
    public String toString() {
        return "HalfTimeWithMoreGoalsView{" +
                "moreGoalsWillBeScoredInFirstHalfTime=" + moreGoalsWillBeScoredInFirstHalfTime +
                ", moreGoalsWillBeScoredInSecondtHalfTime=" + moreGoalsWillBeScoredInSecondtHalfTime +
                ", evenNumberOfGoalsWillBeScoredInBothHalfTimes=" + evenNumberOfGoalsWillBeScoredInBothHalfTimes +
                '}';
    }
}
