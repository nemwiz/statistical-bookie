package dao;

import livescore.model.Fixture;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

public class FixtureDAO {

    private MorphiaDatastore datastore;

    public FixtureDAO(MorphiaDatastore datastore) {
        this.datastore = datastore;
    }

    public void insertFixtureIfNotAlreadyPresent(List<Fixture> fixtures) {

        fixtures.forEach(fixture -> {
            UpdateOperations<Fixture> updateOperations = this.datastore.getDatastore()
                    .createUpdateOperations(Fixture.class)
                    .set("leagueId", fixture.getLeagueId())
                    .set("leagueCode", fixture.getLeagueCode())
                    .set("leagueName", fixture.getLeagueName())
                    .set("countryName", fixture.getCountryName())
                    .set("round", fixture.getRound())
                    .set("date", fixture.getDate())
                    .set("time", fixture.getTime())
                    .set("seasonYear", fixture.getSeasonYear())
                    .set("homeTeam", fixture.getHomeTeam())
                    .set("awayTeam", fixture.getAwayTeam());

            this.datastore.getDatastore()
                    .updateFirst(this.datastore.getDatastore().createQuery(Fixture.class)
                            .field("homeTeam").equal(fixture.getHomeTeam())
                            .field("awayTeam").equal(fixture.getAwayTeam())
                            .field("seasonYear").equal(fixture.getSeasonYear())
                            .field("round").equal(fixture.getRound())
                            .field("leagueCode").equal(fixture.getLeagueCode()), updateOperations, true);
        });


    }
}
