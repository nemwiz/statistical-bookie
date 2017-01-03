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

    public void insertFixtures(List<Fixture> fixtures) {

        this.datastore.getDatastore().save(fixtures);
    }

    public List<Fixture> getFixtures() {

        return this.datastore.getDatastore()
                .createQuery(Fixture.class)
                .order("-fixtureDate")
                .asList();
    }
}
