package viewmodel;

import java.util.Map;

public class NumberOfGoalsModel {

    private Map<String, Long> homeTeam;
    private Map<String, Long> awayTeam;
    private Map<String, Long> bothTeams;

    public NumberOfGoalsModel(Map<String, Long> homeTeam, Map<String, Long> awayTeam, Map<String, Long> bothTeams) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.bothTeams = bothTeams;
    }

    public Map<String, Long> getHomeTeam() {
        return homeTeam;
    }

    public Map<String, Long> getAwayTeam() {
        return awayTeam;
    }

    public Map<String, Long> getBothTeams() {
        return bothTeams;
    }

    @Override
    public String toString() {
        return "NumberOfGoalsModel{" +
                "homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", bothTeams=" + bothTeams +
                '}';
    }
}
