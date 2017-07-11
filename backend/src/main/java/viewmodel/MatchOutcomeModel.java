package viewmodel;

import java.util.Map;

public class MatchOutcomeModel {

    private Map<String, Long> homeTeam;
    private Map<String, Long> awayTeam;
    private Map<String, Long> draw;

    public MatchOutcomeModel(Map<String, Long> homeTeam, Map<String, Long> awayTeam, Map<String, Long> draw) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.draw = draw;
    }

    public Map<String, Long> getHomeTeam() {
        return homeTeam;
    }

    public Map<String, Long> getAwayTeam() {
        return awayTeam;
    }

    public Map<String, Long> getDraw() {
        return draw;
    }

    @Override
    public String toString() {
        return "MatchOutcomeModel{" +
                "homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", draw=" + draw +
                '}';
    }
}
