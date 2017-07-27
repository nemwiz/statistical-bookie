package dao;

import csv.helper.ScrapperHelper;
import csv.model.DatabaseMatch;
import org.mongodb.morphia.query.Query;

import java.time.LocalDate;
import java.util.List;

public class MatchDAO {

    private MorphiaDatastore datastore;

    public MatchDAO(MorphiaDatastore datastore) {
        this.datastore = datastore;
    }


    public void insertMatchesIntoDatabase(List<DatabaseMatch> databaseMatches) {
        this.datastore.getDatastore().save(databaseMatches);
    }

    public boolean deleteMatchesForCurrentSeason(String leagueCode) {

        Query<DatabaseMatch> removeMatchesQuery = this.datastore.getDatastore()
                .createQuery(DatabaseMatch.class);

        removeMatchesQuery.and(
                removeMatchesQuery.criteria("leagueCode").equal(leagueCode),
                removeMatchesQuery.criteria("seasonYear").equal(ScrapperHelper.getCurrentSeasonYearWithDash(LocalDate.now()))
        );

        return this.datastore.getDatastore().delete(removeMatchesQuery).wasAcknowledged();
    }

}
