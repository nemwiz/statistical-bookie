package dao;

import csv.model.DatabaseMatch;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

public class MatchDAO {

    private MorphiaDatastore datastore;

    public MatchDAO(MorphiaDatastore datastore) {
        this.datastore = datastore;
    }


    public void insertMatchesIntoDatabase(List<DatabaseMatch> databaseMatches) {
        this.datastore.getDatastore().save(databaseMatches);
    }

    public void insertMatchesIfNotAlreadyPresent(List<DatabaseMatch> matches) {

        matches.forEach(match -> {
            UpdateOperations<DatabaseMatch> updateOperations = this.datastore.getDatastore()
                    .createUpdateOperations(DatabaseMatch.class)
                    .set("leagueCode", match.getLeagueCode())
                    .set("leagueName", match.getLeagueName())
                    .set("countryName", match.getCountryName())
                    .set("currentRound", match.getCurrentRound())
                    .set("date", match.getDate())
                    .set("seasonYear", match.getSeasonYear())
                    .set("homeTeam", match.getHomeTeam())
                    .set("awayTeam", match.getAwayTeam())
                    .set("homeTeamGoals", match.getHomeTeamGoals())
                    .set("awayTeamGoals", match.getAwayTeamGoals())
                    .set("finalOutcome", match.getFinalOutcome())
                    .set("homeTeamHalftimeGoals", match.getHomeTeamHalftimeGoals())
                    .set("awayTeamHalftimeGoals", match.getAwayTeamHalftimeGoals())
                    .set("halfTimeOutcome", match.getHalfTimeOutcome())
                    .set("matchDetails", match.getMatchDetails());

            this.datastore.getDatastore()
                    .updateFirst(this.datastore.getDatastore().createQuery(DatabaseMatch.class)
                            .field("homeTeam").equal(match.getHomeTeam())
                            .field("awayTeam").equal(match.getAwayTeam())
                            .field("seasonYear").equal(match.getSeasonYear())
                            .field("date").equal(match.getDate())
                            .field("currentRound").equal(match.getCurrentRound())
                            .field("leagueCode").equal(match.getLeagueCode()), updateOperations, true);
        });
    }

}
