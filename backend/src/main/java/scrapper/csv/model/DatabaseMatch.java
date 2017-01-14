package scrapper.csv.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "matches", noClassnameStored = true)
public class DatabaseMatch {

    @Id
    private ObjectId id;
    private String divisionCode;
    private String leagueName;
    private String countryName;
    private String date;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private String finalOutcome;
    private int homeTeamHalftimeGoals;
    private int awayTeamHalftimeGoals;
    private String halfTimeOutcome;

    public DatabaseMatch(String divisionCode, String leagueName, String countryName, String date, String homeTeam, String awayTeam, int homeTeamGoals, int awayTeamGoals, String finalOutcome, int homeTeamHalftimeGoals, int awayTeamHalftimeGoals, String halfTimeOutcome) {
        this.divisionCode = divisionCode;
        this.leagueName = leagueName;
        this.countryName = countryName;
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.finalOutcome = finalOutcome;
        this.homeTeamHalftimeGoals = homeTeamHalftimeGoals;
        this.awayTeamHalftimeGoals = awayTeamHalftimeGoals;
        this.halfTimeOutcome = halfTimeOutcome;
    }

    public ObjectId getId() {
        return id;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDate() {
        return date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public String getFinalOutcome() {
        return finalOutcome;
    }

    public int getHomeTeamHalftimeGoals() {
        return homeTeamHalftimeGoals;
    }

    public int getAwayTeamHalftimeGoals() {
        return awayTeamHalftimeGoals;
    }

    public String getHalfTimeOutcome() {
        return halfTimeOutcome;
    }

    @Override
    public String toString() {
        return "DatabaseMatch{" +
                "id=" + id +
                ", divisionCode='" + divisionCode + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", date='" + date + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                ", finalOutcome='" + finalOutcome + '\'' +
                ", homeTeamHalftimeGoals=" + homeTeamHalftimeGoals +
                ", awayTeamHalftimeGoals=" + awayTeamHalftimeGoals +
                ", halfTimeOutcome='" + halfTimeOutcome + '\'' +
                '}';
    }
}
