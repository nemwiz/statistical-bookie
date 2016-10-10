package dao;

import model.Match;

import java.util.List;

public class MatchDAO {

    private MorphiaDatastore datastore;

    public MatchDAO(MorphiaDatastore datastore) {
        this.datastore = datastore;
    }

    public List<Match> getMatchesByTeamName(String teamName) {

       return this.datastore.getDatastore().createQuery(Match.class)
               .field("homeTeam")
               .contains(teamName)
               .asList();

    }

}
