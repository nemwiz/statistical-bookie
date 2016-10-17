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

    public NumberOfGoalsView(long noGoals, long oneGoal, long twoGoals, long threeGoals, long fourOrMoreGoals, long noGoalsHalfTime, long oneGoalHalfTime, long twoGoalsHalfTime, long threeGoalsHalfTime, long fourOrMoreGoalsHalfTime) {
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

    @Override
    public String toString() {
        return "NumberOfGoalsView{" +
                "oneGoal=" + oneGoal +
                ", twoGoals=" + twoGoals +
                ", threeGoals=" + threeGoals +
                ", fourOrMoreGoals=" + fourOrMoreGoals +
                ", oneGoalHalfTime=" + oneGoalHalfTime +
                ", twoGoalsHalfTime=" + twoGoalsHalfTime +
                ", threeGoalsHalfTime=" + threeGoalsHalfTime +
                ", fourOrMoreGoalsHalfTime=" + fourOrMoreGoalsHalfTime +
                '}';
    }
}
