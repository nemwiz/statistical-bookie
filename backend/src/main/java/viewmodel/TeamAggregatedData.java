package viewmodel;

import java.util.Map;

public class TeamAggregatedData {
    private Map<String, Long> numberOfGoalsFulltime;
    private Map<String, Long> numberOfGoalsFirstHalf;
    private Map<String, Long> numberOfGoalsSecondHalf;
    private Map<String, Long> teamScored;
    private Map<String, Long> halfTimesAndMatchOutcome;
    private Map<String, Long> numberOfGoalsAndWin;

    public TeamAggregatedData(Map<String, Long> numberOfGoalsFulltime, Map<String, Long> numberOfGoalsFirstHalf, Map<String, Long> numberOfGoalsSecondHalf, Map<String, Long> teamScored, Map<String, Long> halfTimesAndMatchOutcome, Map<String, Long> numberOfGoalsAndWin) {
        this.numberOfGoalsFulltime = numberOfGoalsFulltime;
        this.numberOfGoalsFirstHalf = numberOfGoalsFirstHalf;
        this.numberOfGoalsSecondHalf = numberOfGoalsSecondHalf;
        this.teamScored = teamScored;
        this.halfTimesAndMatchOutcome = halfTimesAndMatchOutcome;
        this.numberOfGoalsAndWin = numberOfGoalsAndWin;
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

    public Map<String, Long> getHalfTimesAndMatchOutcome() {
        return halfTimesAndMatchOutcome;
    }

    public Map<String, Long> getNumberOfGoalsAndWin() {
        return numberOfGoalsAndWin;
    }

    @Override
    public String toString() {
        return "TeamAggregatedData{" +
                "numberOfGoalsFulltime=" + numberOfGoalsFulltime +
                ", numberOfGoalsFirstHalf=" + numberOfGoalsFirstHalf +
                ", numberOfGoalsSecondHalf=" + numberOfGoalsSecondHalf +
                ", teamScored=" + teamScored +
                ", halfTimesAndMatchOutcome=" + halfTimesAndMatchOutcome +
                ", numberOfGoalsAndWin=" + numberOfGoalsAndWin +
                '}';
    }
}
