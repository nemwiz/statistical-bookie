package dao;

import model.Fixture;

import javax.inject.Inject;
import java.util.List;

public class FixturesDAO {

    private MorphiaDatastore datastore;
    private static final String LEAGUE_ID = "leagueId";
    private static final String SEASON_YEAR = "seasonYear";
    private static final String WEEK = "week";

    @Inject
    public FixturesDAO(MorphiaDatastore datastore) {
        this.datastore = datastore;
    }

    public void insertFixtures(List<Fixture> fixtures) {

        this.datastore.getDatastore().save(fixtures);
    }

    public List<Fixture> getUpcomingFixtures(int leagueId) {


        return this.datastore.getDatastore()
                .createQuery(Fixture.class)
                .field(LEAGUE_ID).equal(leagueId)
                .order("date")
                .asList();
    }
}
