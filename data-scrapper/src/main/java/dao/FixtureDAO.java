package dao;

import livescore.model.Fixture;

import java.util.List;

public class FixtureDAO {

    private MorphiaDatastore datastore;

    public FixtureDAO(MorphiaDatastore datastore) {
        this.datastore = datastore;
    }

    public void insertFixtures(List<Fixture> fixtures) {
        this.datastore.getDatastore().save(fixtures);
    }
}
