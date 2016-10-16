package viewmodel;

public class NumberOfGoalsView implements ViewModel{

    private long oneGoal;
    private long twoGoals;
    private long threeGoals;
    private long fourOrMoreGoals;
    private long oneGoalHalfTime;
    private long twoGoalsHalfTime;
    private long threeGoalsHalfTime;
    private long fourOrMoreGoalsHalfTime;

    public NumberOfGoalsView(long oneGoal, long twoGoals, long threeGoals, long fourOrMoreGoals, long oneGoalHalfTime, long twoGoalsHalfTime, long threeGoalsHalfTime, long fourOrMoreGoalsHalfTime) {
        this.oneGoal = oneGoal;
        this.twoGoals = twoGoals;
        this.threeGoals = threeGoals;
        this.fourOrMoreGoals = fourOrMoreGoals;
        this.oneGoalHalfTime = oneGoalHalfTime;
        this.twoGoalsHalfTime = twoGoalsHalfTime;
        this.threeGoalsHalfTime = threeGoalsHalfTime;
        this.fourOrMoreGoalsHalfTime = fourOrMoreGoalsHalfTime;
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
