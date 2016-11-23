package collecter.model;

public class NumberOfGoalsModel {

    private boolean isOneGoalsScored;
    private boolean isTwoGoalsScored;
    private boolean isThreeGoalsScored;
    private boolean isFourOrMoreGoalsScored;
    private boolean isOneGoalsScoredOnHalfTime;
    private boolean isTwoGoalsScoredOnHalfTime;
    private boolean isThreeGoalsScoredOnHalfTime;
    private boolean isFourOrMoreGoalsScoredOnHalfTime;
    private boolean isOneGoalsScoredInSecondHalfTime;
    private boolean isTwoGoalsScoredInSecondHalfTime;
    private boolean isThreeGoalsScoredInSecondHalfTime;
    private boolean isFourOrMoreGoalsScoredInSecondHalfTime;

    public NumberOfGoalsModel(boolean isOneGoalsScored, boolean isTwoGoalsScored, boolean isThreeGoalsScored, boolean isFourOrMoreGoalsScored, boolean isOneGoalsScoredOnHalfTime, boolean isTwoGoalsScoredOnHalfTime, boolean isThreeGoalsScoredOnHalfTime, boolean isFourOrMoreGoalsScoredOnHalfTime, boolean isOneGoalsScoredInSecondHalfTime, boolean isTwoGoalsScoredInSecondHalfTime, boolean isThreeGoalsScoredInSecondHalfTime, boolean isFourOrMoreGoalsScoredInSecondHalfTime) {
        this.isOneGoalsScored = isOneGoalsScored;
        this.isTwoGoalsScored = isTwoGoalsScored;
        this.isThreeGoalsScored = isThreeGoalsScored;
        this.isFourOrMoreGoalsScored = isFourOrMoreGoalsScored;
        this.isOneGoalsScoredOnHalfTime = isOneGoalsScoredOnHalfTime;
        this.isTwoGoalsScoredOnHalfTime = isTwoGoalsScoredOnHalfTime;
        this.isThreeGoalsScoredOnHalfTime = isThreeGoalsScoredOnHalfTime;
        this.isFourOrMoreGoalsScoredOnHalfTime = isFourOrMoreGoalsScoredOnHalfTime;
        this.isOneGoalsScoredInSecondHalfTime = isOneGoalsScoredInSecondHalfTime;
        this.isTwoGoalsScoredInSecondHalfTime = isTwoGoalsScoredInSecondHalfTime;
        this.isThreeGoalsScoredInSecondHalfTime = isThreeGoalsScoredInSecondHalfTime;
        this.isFourOrMoreGoalsScoredInSecondHalfTime = isFourOrMoreGoalsScoredInSecondHalfTime;
    }

    public boolean isOneGoalsScored() {
        return isOneGoalsScored;
    }

    public boolean isTwoGoalsScored() {
        return isTwoGoalsScored;
    }

    public boolean isThreeGoalsScored() {
        return isThreeGoalsScored;
    }

    public boolean isFourOrMoreGoalsScored() {
        return isFourOrMoreGoalsScored;
    }

    public boolean isOneGoalsScoredOnHalfTime() {
        return isOneGoalsScoredOnHalfTime;
    }

    public boolean isTwoGoalsScoredOnHalfTime() {
        return isTwoGoalsScoredOnHalfTime;
    }

    public boolean isThreeGoalsScoredOnHalfTime() {
        return isThreeGoalsScoredOnHalfTime;
    }

    public boolean isFourOrMoreGoalsScoredOnHalfTime() {
        return isFourOrMoreGoalsScoredOnHalfTime;
    }

    public boolean isOneGoalsScoredInSecondHalfTime() {
        return isOneGoalsScoredInSecondHalfTime;
    }

    public boolean isTwoGoalsScoredInSecondHalfTime() {
        return isTwoGoalsScoredInSecondHalfTime;
    }

    public boolean isThreeGoalsScoredInSecondHalfTime() {
        return isThreeGoalsScoredInSecondHalfTime;
    }

    public boolean isFourOrMoreGoalsScoredInSecondHalfTime() {
        return isFourOrMoreGoalsScoredInSecondHalfTime;
    }

    @Override
    public String toString() {
        return "NumberOfGoalsModel{" +
                "isOneGoalsScored=" + isOneGoalsScored +
                ", isTwoGoalsScored=" + isTwoGoalsScored +
                ", isThreeGoalsScored=" + isThreeGoalsScored +
                ", isFourOrMoreGoalsScored=" + isFourOrMoreGoalsScored +
                ", isOneGoalsScoredOnHalfTime=" + isOneGoalsScoredOnHalfTime +
                ", isTwoGoalsScoredOnHalfTime=" + isTwoGoalsScoredOnHalfTime +
                ", isThreeGoalsScoredOnHalfTime=" + isThreeGoalsScoredOnHalfTime +
                ", isFourOrMoreGoalsScoredOnHalfTime=" + isFourOrMoreGoalsScoredOnHalfTime +
                ", isOneGoalsScoredInSecondHalfTime=" + isOneGoalsScoredInSecondHalfTime +
                ", isTwoGoalsScoredInSecondHalfTime=" + isTwoGoalsScoredInSecondHalfTime +
                ", isThreeGoalsScoredInSecondHalfTime=" + isThreeGoalsScoredInSecondHalfTime +
                ", isFourOrMoreGoalsScoredInSecondHalfTime=" + isFourOrMoreGoalsScoredInSecondHalfTime +
                '}';
    }
}
