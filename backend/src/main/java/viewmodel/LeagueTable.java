package viewmodel;

public class LeagueTable {
    private String teamName;
    private long wins;
    private long draws;
    private long losses;
    private int pointsWon;

    public LeagueTable(String teamName, long wins, long draws, long losses, int pointsWon) {
        this.teamName = teamName;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.pointsWon = pointsWon;
    }

    public String getTeamName() {
        return teamName;
    }

    public long getWins() {
        return wins;
    }

    public long getDraws() {
        return draws;
    }

    public long getLosses() {
        return losses;
    }

    public int getPointsWon() {
        return pointsWon;
    }

    @Override
    public String toString() {
        return "LeagueTable{" +
                "teamName='" + teamName + '\'' +
                ", wins=" + wins +
                ", draws=" + draws +
                ", losses=" + losses +
                ", pointsWon=" + pointsWon +
                '}';
    }
}
