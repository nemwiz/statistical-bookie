package scrapper.csv.model;

import scrapper.csv.helper.LeagueCodes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MatchMapper {

    private static int MONTH_OF_JULY = 7;

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
                                getSeasonYearFromDate(csvMatch.getDate()),
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

    private static String getSeasonYearFromDate(String date) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        LocalDate parsedDate = LocalDate.parse(date, dateTimeFormatter);

        String currentSeason = String.valueOf(parsedDate.getYear());

        if (parsedDate.getMonth().getValue() >= MONTH_OF_JULY) {
            String nextSeason = String.valueOf(parsedDate.getYear() + 1);
            return addForwardSlash(currentSeason, nextSeason);
        } else {
            String previousSeason = String.valueOf(parsedDate.getYear() - 1);
            return addForwardSlash(previousSeason, currentSeason);
        }

    }

    private static String addForwardSlash(String season1, String season2) {

        return season1 + "/" + season2;
    }

}
