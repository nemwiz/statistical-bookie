package scrapper;

import scrapper.csv.CsvFileParser;
import scrapper.csv.FootballDataScrapper;
import scrapper.csv.model.CsvMatch;
import scrapper.csv.model.DatabaseMatch;
import scrapper.csv.model.MatchMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FixtureScrapper {

    public void main() {

        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used Memory before" + usedMemoryBefore / 1000000);
        // working code here

        CsvFileParser csvFileParser = new CsvFileParser();

        FootballDataScrapper footballDataScrapper = new FootballDataScrapper();

        List<String> countryUrls = footballDataScrapper.getUrlsForEachCountry();

        System.out.println(countryUrls);

//        List<String> countryUrls = new ArrayList<>();
//
//        countryUrls.add("http://www.football-data.co.uk/englandm.php");
//        countryUrls.add("http://www.football-data.co.uk/francem.php");

        List<String> csvFilesUrls = footballDataScrapper.getUrlsToCsvFiles(countryUrls);

//        String[] copyUrls = new String[]{
////                "http://www.football-data.co.uk/mmz4281/1617/E3.csv",
//                "http://www.football-data.co.uk/mmz4281/1617/EC.csv",
//                "http://www.football-data.co.uk/mmz4281/1617/B1.csv",
//                "http://www.football-data.co.uk/mmz4281/1617/T1.csv"
//        };

//        List<String> csvFilesUrls = Arrays.asList(copyUrls);

        System.out.println(csvFilesUrls);

        List<CsvMatch> finalList = new ArrayList<>();

       csvFilesUrls.stream()
               .map(url -> csvFileParser.parseAndMapCsvFileFromUrl(url))
               .flatMap(csvMatches -> csvMatches.stream())
               .forEach(csvMatch -> finalList.add(csvMatch));

        System.out.println(finalList);

        List<DatabaseMatch> databaseMatches = MatchMapper.mapCsvMatchToDatabaseMatch(finalList);


        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory increased:" + (usedMemoryAfter-usedMemoryBefore) / 1000000);


    }

}
