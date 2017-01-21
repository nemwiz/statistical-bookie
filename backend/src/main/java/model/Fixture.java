package model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("fixtures")
public class Fixture {

    @Id
    private int fixtureId;
    private String countryName;
    private int leagueId;
    private String homeTeamName;
    private String awayTeamName;
    private String fixtureDate;
    private String seasonYears;
    private String week;

    public int getFixtureId() {
        return fixtureId;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getLeagueId() {
        return leagueId;
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

    public String getSeasonYears() {
        return seasonYears;
    }

    public String getWeek() {
        return week;
    }
}
