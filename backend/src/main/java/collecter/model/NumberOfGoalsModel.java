package collecter.model;

public class NumberOfGoalsModel {

    private boolean isOneGoalsScored;
    private boolean isTwoGoalsScored;
    private boolean isThreeGoalsScored;
    private boolean isFourOrMoreGoalsScored;

    public NumberOfGoalsModel(boolean isOneGoalsScored, boolean isTwoGoalsScored, boolean isThreeGoalsScored, boolean isFourOrMoreGoalsScored) {
        this.isOneGoalsScored = isOneGoalsScored;
        this.isTwoGoalsScored = isTwoGoalsScored;
        this.isThreeGoalsScored = isThreeGoalsScored;
        this.isFourOrMoreGoalsScored = isFourOrMoreGoalsScored;
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

    @Override
    public String toString() {
        return "NumberOfGoalsModel{" +
                "isOneGoalsScored=" + isOneGoalsScored +
                ", isTwoGoalsScored=" + isTwoGoalsScored +
                ", isThreeGoalsScored=" + isThreeGoalsScored +
                ", isFourOrMoreGoalsScored=" + isFourOrMoreGoalsScored +
                '}';
    }
}
