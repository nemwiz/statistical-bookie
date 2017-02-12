package livescore;

import com.ui4j.api.browser.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import static com.ui4j.api.browser.BrowserFactory.getWebKit;

public class LiveScoreScrapper {

    private static String BASE_URL = "http://www.livescore.com";
    private static String DATA_TYPE = "data-type";
    private static String HOME_TEAM = "H";
    private static String AWAY_TEAM = "A";
    private static String DRAW = "D";

    public void main() {
        try (Page page = getWebKit().navigate("http://www.livescore.com/soccer/england/premier-league/")) {

            String html = page.getDocument().queryAll(".content").toString();

            Document document = Jsoup.parse(html);

            List<String> individualMatchUrls = new ArrayList<>();

//            document.getElementsByAttributeValue(DATA_TYPE, "container").
//                    forEach(element -> element.getElementsByTag("a")
//                            .forEach(aTag -> individualMatchUrls.add(BASE_URL + aTag.getElementsByClass("scorelink").attr("href"))));
//
//            individualMatchUrls.remove(BASE_URL);
//            individualMatchUrls.remove(BASE_URL);
//
//            System.out.println("individualMatchUrls = " + individualMatchUrls);

            individualMatchUrls.add("http://www.livescore.com/soccer/england/premier-league/stoke-city-vs-crystal-palace/1-2252428/");

            individualMatchUrls.forEach(url -> {

                System.out.println("url = " + url);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Page detailPage = getWebKit().navigate(url);

                String detailPageHtml = detailPage.getDocument().getBody().getInnerHTML();

                System.out.println("detailPageHtml = " + detailPageHtml);

                Document detailDocument = Jsoup.parse(detailPageHtml);

                String homeTeam = detailDocument.getElementsByAttributeValue(DATA_TYPE, "home-team").text();
                String awayTeam = detailDocument.getElementsByAttributeValue(DATA_TYPE, "away-team").text();

                int homeTeamGoals = Integer.parseInt(detailDocument.getElementsByAttributeValue(DATA_TYPE, "score").get(0).getElementsByAttributeValue(DATA_TYPE, "home").text());
                int awayTeamGoals = Integer.parseInt(detailDocument.getElementsByAttributeValue(DATA_TYPE, "score").get(0).getElementsByAttributeValue(DATA_TYPE, "away").text());

                String finalOutcome = determineOutcome(homeTeamGoals, awayTeamGoals);

                String halfTimeScore = detailDocument.getElementsByAttributeValue(DATA_TYPE, "header-score").get(0).getElementsByAttributeValue(DATA_TYPE, "score").text();

                int homeTeamHalfTimeGoals = 0;
                int awayTeamHalfTimeGoals = 0;
                String halfTimeOutCome = DRAW;

                if (halfTimeScore.length() > 2) {

                    String halfTimeScoreFormatted = formatHalfTimeScoreString(halfTimeScore);

                    homeTeamHalfTimeGoals = Integer.parseInt(halfTimeScoreFormatted.substring(0,1));
                    awayTeamHalfTimeGoals = Integer.parseInt(halfTimeScoreFormatted.substring(halfTimeScoreFormatted.length() - 1, halfTimeScoreFormatted.length()));
                    halfTimeOutCome = determineOutcome(homeTeamHalfTimeGoals, awayTeamHalfTimeGoals);
                }


                System.out.println("homeTeam = " + homeTeam);
                System.out.println("awayTeam = " + awayTeam);
                System.out.println("homeTeamGoals = " + homeTeamGoals);
                System.out.println("awayTeamGoals = " + awayTeamGoals);
                System.out.println("finalOutcome = " + finalOutcome);
                System.out.println("halfTimeScore = " + halfTimeScore);
                System.out.println("homeTeamHalfTimeGoals = " + homeTeamHalfTimeGoals);
                System.out.println("awayTeamHalfTimeGoals = " + awayTeamHalfTimeGoals);
                System.out.println("halfTimeOutCome = " + halfTimeOutCome);

                MatchDetailsScrapper matchDetailsScrapper = new MatchDetailsScrapper();
                matchDetailsScrapper.getMatchDetails(detailDocument);

                System.out.println(" ");


            });


        }


    }

    private String determineOutcome(int homeTeamGoals, int awayTeamGoals) {
        return homeTeamGoals > awayTeamGoals ? HOME_TEAM : awayTeamGoals > homeTeamGoals ? AWAY_TEAM : DRAW;
    }

    private String formatHalfTimeScoreString(String halfTimeScore) {
        return halfTimeScore.replaceAll("[^0-9]+", " ").trim();
    }

}