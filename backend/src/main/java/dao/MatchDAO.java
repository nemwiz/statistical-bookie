package dao;

import model.Match;

import javax.inject.Inject;
import java.util.List;

public class MatchDAO {

    private MorphiaDatastore datastore;

    @Inject
    public MatchDAO(MorphiaDatastore datastore) {
        this.datastore = datastore;
    }

    public List<Match> getMatchesByTeamName(String teamName) {

       return this.datastore.getDatastore().createQuery(Match.class)
               .field("homeTeam")
               .contains(teamName)
               .limit(10)
               .order("-date")
               .asList();

    }

}
