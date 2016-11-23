package viewmodel;

public class NumberOfGoalsView implements ViewModel{

    private long noGoals;
    private long oneGoal;
    private long twoGoals;
    private long threeGoals;
    private long fourOrMoreGoals;
    private long noGoalsHalfTime;
    private long oneGoalHalfTime;
    private long twoGoalsHalfTime;
    private long threeGoalsHalfTime;
    private long fourOrMoreGoalsHalfTime;
    private long noGoalsInSecondHalfTime;
    private long oneGoalInSecondHalfTime;
    private long twoGoalsInSecondHalfTime;
    private long threeGoalsInSecondHalfTime;
    private long fourOrMoreGoalsInSecondHalfTime;

    public NumberOfGoalsView(long noGoals, long oneGoal, long twoGoals, long threeGoals, long fourOrMoreGoals, long noGoalsHalfTime, long oneGoalHalfTime, long twoGoalsHalfTime, long threeGoalsHalfTime, long fourOrMoreGoalsHalfTime, long noGoalsInSecondHalfTime, long oneGoalInSecondHalfTime, long twoGoalsInSecondHalfTime, long threeGoalsInSecondHalfTime, long fourOrMoreGoalsInSecondHalfTime) {
        this.noGoals = noGoals;
        this.oneGoal = oneGoal;
        this.twoGoals = twoGoals;
        this.threeGoals = threeGoals;
        this.fourOrMoreGoals = fourOrMoreGoals;
        this.noGoalsHalfTime = noGoalsHalfTime;
        this.oneGoalHalfTime = oneGoalHalfTime;
        this.twoGoalsHalfTime = twoGoalsHalfTime;
        this.threeGoalsHalfTime = threeGoalsHalfTime;
        this.fourOrMoreGoalsHalfTime = fourOrMoreGoalsHalfTime;
        this.noGoalsInSecondHalfTime = noGoalsInSecondHalfTime;
        this.oneGoalInSecondHalfTime = oneGoalInSecondHalfTime;
        this.twoGoalsInSecondHalfTime = twoGoalsInSecondHalfTime;
        this.threeGoalsInSecondHalfTime = threeGoalsInSecondHalfTime;
        this.fourOrMoreGoalsInSecondHalfTime = fourOrMoreGoalsInSecondHalfTime;
    }

    public long getNoGoals() {
        return noGoals;
    }

    public long getOneGoal() {
        return oneGoal;
    }

    public long getTwoGoals() {
        return twoGoals;
    }

    public long getThreeGoals() {
        return threeGoals;
    }

    public long getFourOrMoreGoals() {
        return fourOrMoreGoals;
    }

    public long getNoGoalsHalfTime() {
        return noGoalsHalfTime;
    }

    public long getOneGoalHalfTime() {
        return oneGoalHalfTime;
    }

    public long getTwoGoalsHalfTime() {
        return twoGoalsHalfTime;
    }

    public long getThreeGoalsHalfTime() {
        return threeGoalsHalfTime;
    }

    public long getFourOrMoreGoalsHalfTime() {
        return fourOrMoreGoalsHalfTime;
    }

    public long getNoGoalsInSecondHalfTime() {
        return noGoalsInSecondHalfTime;
    }

    public long getOneGoalInSecondHalfTime() {
        return oneGoalInSecondHalfTime;
    }

    public long getTwoGoalsInSecondHalfTime() {
        return twoGoalsInSecondHalfTime;
    }

    public long getThreeGoalsInSecondHalfTime() {
        return threeGoalsInSecondHalfTime;
    }

    public long getFourOrMoreGoalsInSecondHalfTime() {
        return fourOrMoreGoalsInSecondHalfTime;
    }

    @Override
    public String toString() {
        return "NumberOfGoalsView{" +
                "noGoals=" + noGoals +
                ", oneGoal=" + oneGoal +
                ", twoGoals=" + twoGoals +
                ", threeGoals=" + threeGoals +
                ", fourOrMoreGoals=" + fourOrMoreGoals +
                ", noGoalsHalfTime=" + noGoalsHalfTime +
                ", oneGoalHalfTime=" + oneGoalHalfTime +
                ", twoGoalsHalfTime=" + twoGoalsHalfTime +
                ", threeGoalsHalfTime=" + threeGoalsHalfTime +
                ", fourOrMoreGoalsHalfTime=" + fourOrMoreGoalsHalfTime +
                ", noGoalsInSecondHalfTime=" + noGoalsInSecondHalfTime +
                ", oneGoalInSecondHalfTime=" + oneGoalInSecondHalfTime +
                ", twoGoalsInSecondHalfTime=" + twoGoalsInSecondHalfTime +
                ", threeGoalsInSecondHalfTime=" + threeGoalsInSecondHalfTime +
                ", fourOrMoreGoalsInSecondHalfTime=" + fourOrMoreGoalsInSecondHalfTime +
                '}';
    }
}
