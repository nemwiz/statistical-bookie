package scrapper.livescore.model;

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
}
