package model;

public class TeamScore {

    private String teamName;
    private int pointsWon;

    public TeamScore(String teamName, int pointsWon) {
        this.teamName = teamName;
        this.pointsWon = pointsWon;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPointsWon() {
        return pointsWon;
    }

    @Override
    public String toString() {
        return "TeamScore{" +
                "teamName='" + teamName + '\'' +
                ", pointsWon=" + pointsWon +
                '}';
    }
}
