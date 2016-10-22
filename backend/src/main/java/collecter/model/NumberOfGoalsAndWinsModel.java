package collecter.model;

public class NumberOfGoalsAndWinsModel {

    private boolean homeTeamWinAndOneGoalScored;
    private boolean homeTeamWinAndTwoGoalsScored;
    private boolean homeTeamWinAndThreeGoalsScored;
    private boolean homeTeamWinAndFourOrMoreGoalsScored;
    private boolean drawAndOneGoalScored;
    private boolean drawAndTwoGoalsScored;
    private boolean drawAndThreeGoalsScored;
    private boolean drawAndFourOrMoreGoalsScored;
    private boolean awayTeamWinAndOneGoalScored;
    private boolean awayTeamWinAndTwoGoalsScored;
    private boolean awayTeamWinAndThreeGoalsScored;
    private boolean awayTeamWinAndFourOrMoreGoalsScored;

    public NumberOfGoalsAndWinsModel(boolean homeTeamWinAndOneGoalScored, boolean homeTeamWinAndTwoGoalsScored, boolean homeTeamWinAndThreeGoalsScored, boolean homeTeamWinAndFourOrMoreGoalsScored, boolean drawAndOneGoalScored, boolean drawAndTwoGoalsScored, boolean drawAndThreeGoalsScored, boolean drawAndFourOrMoreGoalsScored, boolean awayTeamWinAndOneGoalScored, boolean awayTeamWinAndTwoGoalsScored, boolean awayTeamWinAndThreeGoalsScored, boolean awayTeamWinAndFourOrMoreGoalsScored) {
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

    public boolean isHomeTeamWinAndOneGoalScored() {
        return homeTeamWinAndOneGoalScored;
    }

    public boolean isHomeTeamWinAndTwoGoalsScored() {
        return homeTeamWinAndTwoGoalsScored;
    }

    public boolean isHomeTeamWinAndThreeGoalsScored() {
        return homeTeamWinAndThreeGoalsScored;
    }

    public boolean isHomeTeamWinAndFourOrMoreGoalsScored() {
        return homeTeamWinAndFourOrMoreGoalsScored;
    }

    public boolean isDrawAndOneGoalScored() {
        return drawAndOneGoalScored;
    }

    public boolean isDrawAndTwoGoalsScored() {
        return drawAndTwoGoalsScored;
    }

    public boolean isDrawAndThreeGoalsScored() {
        return drawAndThreeGoalsScored;
    }

    public boolean isDrawAndFourOrMoreGoalsScored() {
        return drawAndFourOrMoreGoalsScored;
    }

    public boolean isAwayTeamWinAndOneGoalScored() {
        return awayTeamWinAndOneGoalScored;
    }

    public boolean isAwayTeamWinAndTwoGoalsScored() {
        return awayTeamWinAndTwoGoalsScored;
    }

    public boolean isAwayTeamWinAndThreeGoalsScored() {
        return awayTeamWinAndThreeGoalsScored;
    }

    public boolean isAwayTeamWinAndFourOrMoreGoalsScored() {
        return awayTeamWinAndFourOrMoreGoalsScored;
    }
}
