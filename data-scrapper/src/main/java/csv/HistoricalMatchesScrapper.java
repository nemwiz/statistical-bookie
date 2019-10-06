package csv;

import csv.model.CsvMatch;
import csv.model.DatabaseMatch;
import csv.model.MatchMapper;
import dao.LeaguesDAO;
import dao.MatchDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class HistoricalMatchesScrapper {

    private static String BASE_URL = "http://www.football-data.co.uk/";
    private static String EXTENSION = "m.php";

    private MatchDAO matchDAO;
    private LeaguesDAO leaguesDAO;

    public HistoricalMatchesScrapper(MatchDAO matchDAO, LeaguesDAO leaguesDAO) {
        this.matchDAO = matchDAO;
        this.leaguesDAO = leaguesDAO;
    }

    public void scrapeMatchesFromCsvFilesOnFootballDataSite() {

        CsvFileParser csvFileParser = new CsvFileParser();

        FootballDataScrapper footballDataScrapper = new FootballDataScrapper();

        List<String> countryUrls = this.leaguesDAO.getAllLeagues()
                .stream()
                .map(league -> BASE_URL + league.getCountryNameShort() + EXTENSION)
                .distinct()
                .collect(Collectors.toList());

        List<String> csvFilesUrls = footballDataScrapper.getUrlsToCsvFiles(countryUrls);

        System.out.println(csvFilesUrls);

        List<CsvMatch> allMatchesParsedFromCsv = new ArrayList<>();

        csvFilesUrls.stream()
                .map(csvFileParser::parseAndMapCsvFileFromUrl)
                .flatMap(Collection::stream)
                .forEach(allMatchesParsedFromCsv::add);

        List<DatabaseMatch> databaseMatches = MatchMapper.mapCsvMatchToDatabaseMatch(allMatchesParsedFromCsv);

        this.matchDAO.insertMatchesIfNotAlreadyPresent(databaseMatches);
    }

}
