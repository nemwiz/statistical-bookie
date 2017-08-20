package model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

@Entity("fixtures")
@Indexes({
        @Index(fields = @Field("date"))
})
public class Fixture {

    @Id
    private ObjectId fixtureId;
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

    public ObjectId getFixtureId() {
        return fixtureId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fixture fixture = (Fixture) o;

        if (leagueId != fixture.leagueId) return false;
        return fixtureId.equals(fixture.fixtureId);
    }

    @Override
    public int hashCode() {
        int result = fixtureId.hashCode();
        result = 31 * result + leagueId;
        return result;
    }
}
