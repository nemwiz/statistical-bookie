package livescore.model;

public class GoalDetails implements LiveScoreMatchDetail {

    private int minuteOccured;
    private String occuredByTeam;
    private String goalType;
    private String scoredBy;
    private String assistBy;
    private String actualScore;

    public GoalDetails(int minuteOccured, String occuredByTeam, String goalType, String scoredBy, String assistBy, String actualScore) {
        this.minuteOccured = minuteOccured;
        this.occuredByTeam = occuredByTeam;
        this.goalType = goalType;
        this.scoredBy = scoredBy;
        this.assistBy = assistBy;
        this.actualScore = actualScore;
    }

    public int getMinuteOccured() {
        return minuteOccured;
    }

    public String getOccuredByTeam() {
        return occuredByTeam;
    }

    public String getGoalType() {
        return goalType;
    }

    public String getScoredBy() {
        return scoredBy;
    }

    public String getAssistBy() {
        return assistBy;
    }

    public String getActualScore() {
        return actualScore;
    }

    @Override
    public String toString() {
        return "GoalDetails{" +
                "minuteOccured=" + minuteOccured +
                ", occuredByTeam='" + occuredByTeam + '\'' +
                ", goalType='" + goalType + '\'' +
                ", scoredBy='" + scoredBy + '\'' +
                ", assistBy='" + assistBy + '\'' +
                ", actualScore='" + actualScore + '\'' +
                '}';
    }
}
