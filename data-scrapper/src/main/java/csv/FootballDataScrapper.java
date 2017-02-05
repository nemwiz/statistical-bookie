package csv;

import csv.helper.Countries;
import csv.helper.ScrapperHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FootballDataScrapper {

    private Random random;

    private static final String FOOTBALL_DATA_MAIN_URL = "http://www.football-data.co.uk/";
    private static final String FOOTBALL_DATA_HISTORY_URL = FOOTBALL_DATA_MAIN_URL + "data.php";

    public FootballDataScrapper() {
        this.random = new Random();
    }

    public List<String> getUrlsForEachCountry() {

        List<String> countriesUrls = new ArrayList<>();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Document historyDataHtml = Jsoup
                    .connect(FOOTBALL_DATA_HISTORY_URL)
                    .userAgent(getRandomUserAgent())
                    .get();

            Elements htmlElements = historyDataHtml.select("div.menus");

            countriesUrls = htmlElements.stream()
                    .filter(getAllElementsWithBreakTagAndText())
                    .filter(isElementTextEqualToCountryName())
                    .map(element -> element.getElementsByTag("a").attr("href"))
                    .collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return countriesUrls;
    }

    private Predicate<Element> isElementTextEqualToCountryName() {
        return element -> Arrays.stream(Countries.values()).anyMatch(
                countries -> countries.name().equals(element.getElementsByTag("b").text())
        );
    }

    private Predicate<Element> getAllElementsWithBreakTagAndText() {
        return element -> element.getElementsByTag("b").hasText();
    }

    public List<String> getUrlsToCsvFiles(List<String> countryUrls) {

        List<String> csvFileUrls = new ArrayList<>();

        countryUrls.forEach(url -> {

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Document countryAndLeaguesHtml = Jsoup
                        .connect(url)
                        .userAgent(getRandomUserAgent())
                        .get();

                Elements htmlElements = countryAndLeaguesHtml.getElementsByTag("a");

                htmlElements.stream()
                        .filter(this::isCurrentSeason)
                        .map(element -> FOOTBALL_DATA_MAIN_URL + element.attr("href"))
                        .forEach(csvFileUrls::add);

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        return csvFileUrls;
    }

    /*
    Can be used to scrape all seasons that are available
     */

    private boolean isAllSeasons(Element element) {
        return element.attr("href").contains("mmz");
    }

    private boolean isCurrentSeason(Element element) {
        return element.attr("href").contains("mmz") && element.attr("href").contains(ScrapperHelper.getCurrentSeasonYearShortFormat());
    }

    private String getRandomUserAgent() {

        String[] userAgents = new String[]{
                "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:49.0) Gecko/20100101 Firefox/49.0",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.9.2.3) Gecko/20100401 Firefox/3.6.3 (FM Scene 4.6.1)",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.19 (KHTML, like Gecko) Chrome/1.0.154.53 Safari/525.19",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.36 Safari/535.7",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36",
                "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Safari/602.1.50",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36"
        };


        return userAgents[this.random.nextInt(userAgents.length)];

    }

}
