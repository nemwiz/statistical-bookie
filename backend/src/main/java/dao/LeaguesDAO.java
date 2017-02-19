package dao;

import model.League;

import javax.inject.Inject;
import java.util.List;

public class LeaguesDAO {

    private MorphiaDatastore datastore;

    @Inject
    public LeaguesDAO(MorphiaDatastore datastore) {
        this.datastore = datastore;
    }

    public List<League> getAllLeagues() {

        return this.datastore.getDatastore()
                .createQuery(League.class)
                .order("leagueId")
                .asList();
    }

}
