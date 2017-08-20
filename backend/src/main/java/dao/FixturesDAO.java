package dao;

import model.Fixture;

import javax.inject.Inject;
import java.util.List;

public class FixturesDAO {

    private MorphiaDatastore datastore;

    @Inject
    public FixturesDAO(MorphiaDatastore datastore) {
        this.datastore = datastore;
    }

    public List<Fixture> getUpcomingFixtures(List<String> daysOfCurrentWeek) {

        return this.datastore.getDatastore()
                .createQuery(Fixture.class)
                .filter("date in", daysOfCurrentWeek)
                .asList();
    }
}
