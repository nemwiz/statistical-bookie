package livescore.model;

public class CardsDetails implements LiveScoreMatchDetail {

    private int minuteOccured;
    private String occuredByTeam;
    private String cardType;
    private String playerName;

    public CardsDetails(int minuteOccured, String occuredByTeam, String cardType, String playerName) {
        this.minuteOccured = minuteOccured;
        this.occuredByTeam = occuredByTeam;
        this.cardType = cardType;
        this.playerName = playerName;
    }

    public int getMinuteOccured() {
        return minuteOccured;
    }

    public String getOccuredByTeam() {
        return occuredByTeam;
    }

    public String getCardType() {
        return cardType;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return "CardsDetails{" +
                "minuteOccured=" + minuteOccured +
                ", occuredByTeam='" + occuredByTeam + '\'' +
                ", cardType='" + cardType + '\'' +
                ", playerName='" + playerName + '\'' +
                '}';
    }
}
