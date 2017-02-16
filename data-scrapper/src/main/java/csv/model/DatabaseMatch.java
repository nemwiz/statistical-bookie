package csv.model;

import livescore.model.LiveScoreMatchDetail;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

@Entity(value = "matches", noClassnameStored = true)
public class DatabaseMatch {

    @Id
    private ObjectId id;
    private String leagueCode;
    private String leagueName;
    private String countryName;
    private int currentRound;
    private String date;
    private String seasonYear;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private String finalOutcome;
    private int homeTeamHalftimeGoals;
    private int awayTeamHalftimeGoals;
    private String halfTimeOutcome;
    private List<LiveScoreMatchDetail> matchDetails;

    public DatabaseMatch() {
    }

    public DatabaseMatch(String leagueCode, String leagueName, String countryName, String date, String seasonYear, String homeTeam, String awayTeam, int homeTeamGoals, int awayTeamGoals, String finalOutcome, int homeTeamHalftimeGoals, int awayTeamHalftimeGoals, String halfTimeOutcome) {
        this.leagueCode = leagueCode;
        this.leagueName = leagueName;
        this.countryName = countryName;
        this.date = date;
        this.seasonYear = seasonYear;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.finalOutcome = finalOutcome;
        this.homeTeamHalftimeGoals = homeTeamHalftimeGoals;
        this.awayTeamHalftimeGoals = awayTeamHalftimeGoals;
        this.halfTimeOutcome = halfTimeOutcome;
    }

    public DatabaseMatch(String leagueCode, String leagueName, String countryName, int currentRound, String seasonYear, String homeTeam, String awayTeam, int homeTeamGoals, int awayTeamGoals, String finalOutcome, int homeTeamHalftimeGoals, int awayTeamHalftimeGoals, String halfTimeOutcome, List<LiveScoreMatchDetail> matchDetails) {
        this.leagueCode = leagueCode;
        this.leagueName = leagueName;
        this.countryName = countryName;
        this.currentRound = currentRound;
        this.seasonYear = seasonYear;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.finalOutcome = finalOutcome;
        this.homeTeamHalftimeGoals = homeTeamHalftimeGoals;
        this.awayTeamHalftimeGoals = awayTeamHalftimeGoals;
        this.halfTimeOutcome = halfTimeOutcome;
        this.matchDetails = matchDetails;
    }

    public ObjectId getId() {
        return id;
    }

    public String getLeagueCode() {
        return leagueCode;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public String getDate() {
        return date;
    }

    public String getSeasonYear() {
        return seasonYear;
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

    public List<LiveScoreMatchDetail> getMatchDetails() {
        return matchDetails;
    }

    @Override
    public String toString() {
        return "DatabaseMatch{" +
                "id=" + id +
                ", leagueCode='" + leagueCode + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", currentRound=" + currentRound +
                ", date='" + date + '\'' +
                ", seasonYear='" + seasonYear + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                ", finalOutcome='" + finalOutcome + '\'' +
                ", homeTeamHalftimeGoals=" + homeTeamHalftimeGoals +
                ", awayTeamHalftimeGoals=" + awayTeamHalftimeGoals +
                ", halfTimeOutcome='" + halfTimeOutcome + '\'' +
                ", matchDetails=" + matchDetails +
                '}';
    }
}
