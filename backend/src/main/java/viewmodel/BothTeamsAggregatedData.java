package viewmodel;

import java.util.Map;

public class BothTeamsAggregatedData {

    private Map<String, Long> numberOfGoalsFulltime;
    private Map<String, Long> numberOfGoalsFirstHalf;
    private Map<String, Long> numberOfGoalsSecondHalf;
    private Map<String, Long> teamScored;

    public BothTeamsAggregatedData(Map<String, Long> numberOfGoalsFulltime, Map<String, Long> numberOfGoalsFirstHalf, Map<String, Long> numberOfGoalsSecondHalf, Map<String, Long> teamScored) {
        this.numberOfGoalsFulltime = numberOfGoalsFulltime;
        this.numberOfGoalsFirstHalf = numberOfGoalsFirstHalf;
        this.numberOfGoalsSecondHalf = numberOfGoalsSecondHalf;
        this.teamScored = teamScored;
    }

    public Map<String, Long> getNumberOfGoalsFulltime() {
        return numberOfGoalsFulltime;
    }

    public Map<String, Long> getNumberOfGoalsFirstHalf() {
        return numberOfGoalsFirstHalf;
    }

    public Map<String, Long> getNumberOfGoalsSecondHalf() {
        return numberOfGoalsSecondHalf;
    }

    public Map<String, Long> getTeamScored() {
        return teamScored;
    }

    @Override
    public String toString() {
        return "BothTeamsAggregatedData{" +
                "numberOfGoalsFulltime=" + numberOfGoalsFulltime +
                ", numberOfGoalsFirstHalf=" + numberOfGoalsFirstHalf +
                ", numberOfGoalsSecondHalf=" + numberOfGoalsSecondHalf +
                ", teamScored=" + teamScored +
                '}';
    }
}
