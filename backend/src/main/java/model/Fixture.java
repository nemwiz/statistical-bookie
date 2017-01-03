package model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("fixtures")
public class Fixture {

    @Id
    private int fixtureId;
    private String countryName;
    private String leagueName;
    private String homeTeamName;
    private String awayTeamName;
    private String fixtureDate;

    public int getFixtureId() {
        return fixtureId;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public String getFixtureDate() {
        return fixtureDate;
    }
}
