package scrapper.csv.model;

import scrapper.csv.LeagueCodes;

import java.util.List;
import java.util.stream.Collectors;

public class MatchMapper {

    private MatchMapper() {
    }

    public static List<DatabaseMatch> mapCsvMatchToDatabaseMatch(List<CsvMatch> csvMatches) {

        return csvMatches.stream()
                .map(csvMatch ->
                        new DatabaseMatch(
                                csvMatch.getDivisionName(),
                                LeagueCodes.leagueCodeToLeagueNameMap.get(csvMatch.getDivisionName()),
                                LeagueCodes.leagueCodeToCountryNameMap.get(csvMatch.getDivisionName()),
                                csvMatch.getDate(),
                                csvMatch.getHomeTeam(),
                                csvMatch.getAwayTeam(),
                                csvMatch.getHomeTeamGoals(),
                                csvMatch.getAwayTeamGoals(),
                                csvMatch.getFinalOutcome(),
                                csvMatch.getHomeTeamHalftimeGoals(),
                                csvMatch.getAwayTeamHalftimeGoals(),
                                csvMatch.getHalfTimeOutcome()
                        )
                ).collect(Collectors.toList());
    }

}
