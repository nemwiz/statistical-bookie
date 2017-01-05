package dao;

import model.Fixture;

import javax.inject.Inject;
import java.util.List;

public class FixturesDAO {

    private MorphiaDatastore datastore;
    private static final String COUNTRY = "countryName";
    private static final String LEAGUE = "leagueName";

    @Inject
    public FixturesDAO(MorphiaDatastore datastore) {
        this.datastore = datastore;
    }

    public void insertFixtures(List<Fixture> fixtures) {

        this.datastore.getDatastore().save(fixtures);
    }

    public List<Fixture> getFixturesByCountryAndLeague(String countryName, String leagueName) {

        return this.datastore.getDatastore()
                .createQuery(Fixture.class)
                .field(COUNTRY).equal(countryName)
                .field(LEAGUE).equal(leagueName)
                .order("-fixtureDate")
                .asList();
    }
}
