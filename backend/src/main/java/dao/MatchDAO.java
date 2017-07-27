package dao;

import model.Match;
import helper.SeasonsAndDates;

import javax.inject.Inject;
import java.time.LocalDate;
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

    public List<Match> getMatchesByTeamNames(String homeTeamName, String awayTeamName, int numberOfMatches) {

        return this.datastore.getDatastore().createQuery(Match.class)
                .field("homeTeam").equal(homeTeamName)
                .field("awayTeam").equal(awayTeamName)
                .limit(numberOfMatches)
                .asList();

    }

    public List<Match> getAllMatchesForCurrentSeason() {

        return this.datastore.getDatastore().createQuery(Match.class)
                .field("seasonYear").contains(SeasonsAndDates.getCurrentSeasonYearWithDash(LocalDate.now()))
                .asList();

    }

}
