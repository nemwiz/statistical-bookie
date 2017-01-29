package dao;

import model.Match;
import org.mongodb.morphia.query.Query;
import scrapper.csv.helper.ScrapperHelper;
import scrapper.csv.model.DatabaseMatch;

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

    public List<Match> getMatchesByTeamNames(String homeTeamName, String awayTeamName) {

        return this.datastore.getDatastore().createQuery(Match.class)
                .field("homeTeam").contains(homeTeamName)
                .field("awayTeam").contains(awayTeamName)
                .limit(10)
                .order("-date")
                .asList();

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

    public List<Match> getAllMatchesForCurrentSeason() {

        return this.datastore.getDatastore().createQuery(Match.class)
                .field("seasonYear").contains(ScrapperHelper.getCurrentSeasonYearWithDash(LocalDate.now()))
                .asList();

    }

}
