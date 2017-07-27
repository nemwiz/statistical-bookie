package dao;

import dao.model.League;

import java.util.List;

public class LeaguesDAO {

    private MorphiaDatastore datastore;

    public LeaguesDAO(MorphiaDatastore datastore) {
        this.datastore = datastore;
    }

    public List<League> getAllLeagues() {
        return this.datastore.getDatastore()
                .createQuery(League.class)
                .order("_id")
                .asList();
    }

    public League getLeagueByLeagueCode(String leagueCode) {
        return this.datastore.getDatastore()
                .createQuery(League.class)
                .field("leagueCode").equal(leagueCode)
                .get();
    }
}
