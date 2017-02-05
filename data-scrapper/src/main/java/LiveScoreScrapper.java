import com.ui4j.api.browser.Page;
import model.GoalDetails;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import static com.ui4j.api.browser.BrowserFactory.getWebKit;

public class LiveScoreScrapper {

    private static String BASE_URL = "http://www.livescore.com";

    public void main() {
        try (Page page = getWebKit().navigate("http://www.livescore.com/soccer/england/premier-league/")) {

            String html = page.getDocument().queryAll(".content").toString();

            Document document = Jsoup.parse(html);

            List<String> individualMatchUrls = new ArrayList<>();

            document.getElementsByAttributeValue("data-type", "container").
                    forEach(element -> element.getElementsByTag("a")
                            .forEach(aTag -> individualMatchUrls.add(BASE_URL + aTag.getElementsByClass("scorelink").attr("href"))));

            individualMatchUrls.remove(BASE_URL);

            Page detailPage = getWebKit().navigate(individualMatchUrls.get(2));

            String detailPageHtml = detailPage.getDocument().getBody().getInnerHTML();

            System.out.println("detailPageHtml = " + detailPageHtml);

            Document detailDocument = Jsoup.parse(detailPageHtml);

            System.out.println("home team = " + detailDocument.getElementsByAttributeValue("data-type", "home-team").text());
            System.out.println("away team = " + detailDocument.getElementsByAttributeValue("data-type", "away-team").text());
            System.out.println("homeTeamScore = " + detailDocument.getElementsByAttributeValue("data-type", "score").get(0).getElementsByAttributeValue("data-type", "home").text());
            System.out.println("awayTeamScore = " + detailDocument.getElementsByAttributeValue("data-type", "score").get(0).getElementsByAttributeValue("data-type", "away").text());
            System.out.println("header score = " + detailDocument.getElementsByAttributeValue("data-type", "header-score").get(0).getElementsByAttributeValue("data-type", "score").text());

            detailDocument.getElementsByAttributeValue("data-type", "incident")
                    .forEach(element -> incidentToMatchDetail(element));



        }


    }

    private void incidentToMatchDetail(Element element) {

        List<GoalDetails> goalDetails = new ArrayList<>();

        int minuteOccured = Integer.parseInt(element.getElementsByAttributeValue("data-type", "time").text().replace("'",""));
        String occuredByTeam = occuredByTeam(element);

        if (isMatchDetailAGoal(element)) {
        }

        System.out.println("minute occured = " + element.getElementsByAttributeValue("data-type", "time").text().replace("'",""));
        System.out.println("isOccuredByHomeTeam = " + (element.getElementsByAttributeValue("data-type", "home").get(0).getElementsByAttributeValue("data-type", "player-name").text().length() > 2));

    }

    private boolean isMatchDetailAGoal(Element element) {
        return element.getElementsByAttributeValue("data-type", "middle").get(0).getElementsByAttributeValue("data-type", "score").text().length() > 2;
    }

    private String occuredByTeam(Element element) {
        return element.getElementsByAttributeValue("data-type", "home").get(0).getElementsByAttributeValue("data-type", "player-name").text().length() > 2 ? "H" : "A";
    }
}