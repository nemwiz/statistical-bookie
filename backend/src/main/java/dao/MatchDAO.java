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

    public List<Match> getMatchesByTeamName(String homeTeamName) {

       return this.datastore.getDatastore().createQuery(Match.class)
               .field("homeTeam").contains(homeTeamName)
               .limit(10)
               .order("-date")
               .asList();

    }

    public List<Match> getMatchesByTeamNames(String homeTeamName, String awayTeamName) {

        return this.datastore.getDatastore().createQuery(Match.class)
                .field("homeTeam").contains(homeTeamName)
                .field("awayTeam").contains(awayTeamName)
                .limit(10)
                .order("-date")
                .asList();

    }

}
