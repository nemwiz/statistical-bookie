
import csv.CsvFileParser;
import csv.FootballDataScrapper;
import csv.model.CsvMatch;
import csv.model.DatabaseMatch;
import csv.model.MatchMapper;
import dao.MatchDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HistoricalMatchesScrapper {

    private MatchDAO matchDAO;

    public HistoricalMatchesScrapper(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public void scrapeMatchesFromCsvFilesOnFootballDataSite() {

        CsvFileParser csvFileParser = new CsvFileParser();

        FootballDataScrapper footballDataScrapper = new FootballDataScrapper();

        List<String> countryUrls = footballDataScrapper.getUrlsForEachCountry();

        List<String> csvFilesUrls = footballDataScrapper.getUrlsToCsvFiles(countryUrls);

        System.out.println(csvFilesUrls);

        List<CsvMatch> allMatchesParsedFromCsv = new ArrayList<>();

        csvFilesUrls.stream()
                .map(csvFileParser::parseAndMapCsvFileFromUrl)
                .flatMap(Collection::stream)
                .forEach(allMatchesParsedFromCsv::add);

        allMatchesParsedFromCsv.stream()
                .map(CsvMatch::getDivisionName)
                .distinct()
                .forEach(divisionName -> this.matchDAO.deleteMatchesForCurrentSeason(divisionName));

        List<DatabaseMatch> databaseMatches = MatchMapper.mapCsvMatchToDatabaseMatch(allMatchesParsedFromCsv);

        this.matchDAO.insertMatchesIntoDatabase(databaseMatches);

    }

}
