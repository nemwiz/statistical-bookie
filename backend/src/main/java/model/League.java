package model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("leagues")
public class League {

    @Id
    private int id;
    private String leagueName;
    private String leagueNameShort;
    private String leagueCode;
    private String countryName;
    private String countryNameShort;
    private String liveScoreLink;
    private String countryCode;

    public int getId() {
        return id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getLeagueNameShort() {
        return leagueNameShort;
    }

    public String getLeagueCode() {
        return leagueCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryNameShort() {
        return countryNameShort;
    }

    public String getLiveScoreLink() {
        return liveScoreLink;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
