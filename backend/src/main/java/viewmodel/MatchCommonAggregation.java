package viewmodel;

import java.util.Map;

public class MatchCommonAggregation {

    private Map<String, Long> drawOutcome;
    private Map<String, Long> drawOutcomeAndGoalsScored;
    private HalfTimeFullTime halfTimeFullTime;
    private HalfTimeWithMoreGoals halfTimeWithMoreGoals;
    private Map<String, Long> exactResults;

    public MatchCommonAggregation(Map<String, Long> drawOutcome, Map<String, Long> drawOutcomeAndGoalsScored, HalfTimeFullTime halfTimeFullTime, HalfTimeWithMoreGoals halfTimeWithMoreGoals, Map<String, Long> exactResults) {
        this.drawOutcome = drawOutcome;
        this.drawOutcomeAndGoalsScored = drawOutcomeAndGoalsScored;
        this.halfTimeFullTime = halfTimeFullTime;
        this.halfTimeWithMoreGoals = halfTimeWithMoreGoals;
        this.exactResults = exactResults;
    }

    public Map<String, Long> getDrawOutcome() {
        return drawOutcome;
    }

    public Map<String, Long> getDrawOutcomeAndGoalsScored() {
        return drawOutcomeAndGoalsScored;
    }

    public HalfTimeFullTime getHalfTimeFullTime() {
        return halfTimeFullTime;
    }

    public HalfTimeWithMoreGoals getHalfTimeWithMoreGoals() {
        return halfTimeWithMoreGoals;
    }

    public Map<String, Long> getExactResults() {
        return exactResults;
    }

    @Override
    public String toString() {
        return "MatchCommonAggregation{" +
                "drawOutcome=" + drawOutcome +
                ", drawOutcomeAndGoalsScored=" + drawOutcomeAndGoalsScored +
                ", halfTimeFullTime=" + halfTimeFullTime +
                ", halfTimeWithMoreGoals=" + halfTimeWithMoreGoals +
                ", exactResults=" + exactResults +
                '}';
    }
}
