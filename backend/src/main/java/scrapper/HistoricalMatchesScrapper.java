package scrapper;

import dao.MatchDAO;
import scrapper.csv.CsvFileParser;
import scrapper.csv.FootballDataScrapper;
import scrapper.csv.model.CsvMatch;
import scrapper.csv.model.DatabaseMatch;
import scrapper.csv.model.MatchMapper;

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

        List<DatabaseMatch> databaseMatches = MatchMapper.mapCsvMatchToDatabaseMatch(allMatchesParsedFromCsv);

//        this.matchDAO.insertMatchesIntoDatabase(databaseMatches);

    }

}
