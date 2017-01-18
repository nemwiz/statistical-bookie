package scrapper;

import static com.ui4j.api.browser.BrowserFactory.getWebKit;

import com.ui4j.api.browser.Page;

public class LiveScoreScrapper {

    public void main() {
        try (Page page = getWebKit().navigate("http://www.livescore.com/soccer/england/premier-league/")) {

            page.getDocument().queryAll(".content")
                    .forEach(element -> System.out.println("element = " + element.getInnerHTML()));

        }
    }

}