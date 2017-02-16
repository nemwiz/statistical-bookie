package csv.model;


import csv.helper.LeagueCodes;
import csv.helper.ScrapperHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        if (date.length() == 0 || date.equals("")) {
            date = LocalDate.now().toString();
        } else if (date.length() > 8) {
            dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }

        LocalDate parsedDate = LocalDate.parse(date, dateTimeFormatter);

        return ScrapperHelper.getCurrentSeasonYearWithDash(parsedDate);
    }

}
