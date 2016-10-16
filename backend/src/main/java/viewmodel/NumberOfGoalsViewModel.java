package viewmodel;

public class NumberOfGoalsViewModel {

    private long oneGoalIsScored;
    private long twoGoalsAreScored;
    private long threeGoalsAreScored;
    private long fourOrMoreGoalsAreScored;
    private long isOneGoalsScoredOnHalfTime;
    private long isTwoGoalsScoredOnHalfTime;
    private long isThreeGoalsScoredOnHalfTime;
    private long isFourOrMoreGoalsScoredOnHalfTime;

    public NumberOfGoalsViewModel(long oneGoalIsScored, long twoGoalsAreScored, long threeGoalsAreScored, long fourOrMoreGoalsAreScored, long isOneGoalsScoredOnHalfTime, long isTwoGoalsScoredOnHalfTime, long isThreeGoalsScoredOnHalfTime, long isFourOrMoreGoalsScoredOnHalfTime) {
        this.oneGoalIsScored = oneGoalIsScored;
        this.twoGoalsAreScored = twoGoalsAreScored;
        this.threeGoalsAreScored = threeGoalsAreScored;
        this.fourOrMoreGoalsAreScored = fourOrMoreGoalsAreScored;
        this.isOneGoalsScoredOnHalfTime = isOneGoalsScoredOnHalfTime;
        this.isTwoGoalsScoredOnHalfTime = isTwoGoalsScoredOnHalfTime;
        this.isThreeGoalsScoredOnHalfTime = isThreeGoalsScoredOnHalfTime;
        this.isFourOrMoreGoalsScoredOnHalfTime = isFourOrMoreGoalsScoredOnHalfTime;
    }

    @Override
    public String toString() {
        return "NumberOfGoalsViewModel{" +
                "oneGoalIsScored=" + oneGoalIsScored +
                ", twoGoalsAreScored=" + twoGoalsAreScored +
                ", threeGoalsAreScored=" + threeGoalsAreScored +
                ", fourOrMoreGoalsAreScored=" + fourOrMoreGoalsAreScored +
                ", isOneGoalsScoredOnHalfTime=" + isOneGoalsScoredOnHalfTime +
                ", isTwoGoalsScoredOnHalfTime=" + isTwoGoalsScoredOnHalfTime +
                ", isThreeGoalsScoredOnHalfTime=" + isThreeGoalsScoredOnHalfTime +
                ", isFourOrMoreGoalsScoredOnHalfTime=" + isFourOrMoreGoalsScoredOnHalfTime +
                '}';
    }
}
