package livescore.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "fixtures", noClassnameStored = true)
public class Fixture {

    @Id
    private ObjectId id;
    private int leagueId;
    private String leagueCode;
    private String leagueName;
    private String countryName;
    private int round;
    private String date;
    private String time;
    private String seasonYear;
    private String homeTeam;
    private String awayTeam;

    public Fixture(int leagueId, String leagueCode, String leagueName, String countryName, int round, String date, String time, String seasonYear, String homeTeam, String awayTeam) {
        this.leagueId = leagueId;
        this.leagueCode = leagueCode;
        this.leagueName = leagueName;
        this.countryName = countryName;
        this.round = round;
        this.date = date;
        this.time = time;
        this.seasonYear = seasonYear;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public ObjectId getId() {
        return id;
    }

    public int getLeagueId() {
        return leagueId;
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

    public int getRound() {
        return round;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
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

    @Override
    public String toString() {
        return "Fixture{" +
                "id=" + id +
                ", leagueId=" + leagueId +
                ", leagueCode='" + leagueCode + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", round=" + round +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", seasonYear='" + seasonYear + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                '}';
    }
}
