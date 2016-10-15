package aggregator.model;

public class NumberOfGoalsPerMatchModel {

    private int oneGoal;
    private int twoGoals;
    private int threeGoals;
    private int fourOrMoreGoals;

    public NumberOfGoalsPerMatchModel(int oneGoal, int twoGoals, int threeGoals, int fourOrMoreGoals) {
        this.oneGoal = oneGoal;
        this.twoGoals = twoGoals;
        this.threeGoals = threeGoals;
        this.fourOrMoreGoals = fourOrMoreGoals;
    }

    public int getOneGoal() {
        return oneGoal;
    }

    public int getTwoGoals() {
        return twoGoals;
    }

    public int getThreeGoals() {
        return threeGoals;
    }

    public int getFourOrMoreGoals() {
        return fourOrMoreGoals;
    }

    @Override
    public String toString() {
        return "NumberOfGoalsPerMatchModel{" +
                "oneGoal=" + oneGoal +
                ", twoGoals=" + twoGoals +
                ", threeGoals=" + threeGoals +
                ", fourOrMoreGoals=" + fourOrMoreGoals +
                '}';
    }
}
