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

    public List<Fixture> getUpcomingFixtures() {

        // TODO - add date criterion
        return this.datastore.getDatastore()
                .createQuery(Fixture.class)
                .order("date")
                .field(LEAGUE_ID).equal(1)
                .limit(15)
                .asList();
    }
}
