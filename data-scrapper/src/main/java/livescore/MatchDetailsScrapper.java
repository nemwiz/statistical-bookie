package livescore;

import livescore.model.CardsDetails;
import livescore.model.GoalDetails;
import livescore.model.LiveScoreMatchDetail;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

class MatchDetailsScrapper {

    MatchDetailsScrapper() {
    }

    private static String DATA_TYPE = "data-type";
    private static String HOME_TEAM = "H";
    private static String AWAY_TEAM = "A";

    List<LiveScoreMatchDetail> getMatchDetails(Document matchDetailsDocument) {

        List<LiveScoreMatchDetail> matchDetails = new ArrayList<>();

        matchDetailsDocument.getElementsByAttributeValue(DATA_TYPE, "incident")
                .stream()
                .filter(detailElement -> isCurrentElementAMatchDetailElement(detailElement))
                .forEach(detailElement -> matchDetails.add(mapDetailsElementToMatchDetail(detailElement)));

        System.out.println("matchDetails = " + matchDetails);

        return matchDetails;

    }

    private LiveScoreMatchDetail mapDetailsElementToMatchDetail(Element matchDetailElement) {

        int minuteOccured = Integer.parseInt(matchDetailElement.getElementsByAttributeValue(DATA_TYPE, "time").text().replace("'", ""));
        String occuredByTeam = occuredByTeam(matchDetailElement);
        Elements homeOrAwayElement = getHomeOrAwayElement(occuredByTeam, matchDetailElement);

        String playerName = getPlayerName(homeOrAwayElement);

        Elements elementThatContainsGoalAndCardInformation = getElementWithGoalAndCardInformation(occuredByTeam, matchDetailElement);

        return mapDetailToGoalOrCardDetail(matchDetailElement, minuteOccured, occuredByTeam, homeOrAwayElement, playerName, elementThatContainsGoalAndCardInformation);

    }

    private LiveScoreMatchDetail mapDetailToGoalOrCardDetail(Element matchDetailElement, int minuteOccured, String occuredByTeam, Elements homeOrAwayElement, String playerName, Elements elementThatContainsGoalAndCardInformation) {
        LiveScoreMatchDetail matchDetail;

        if (isMatchDetailAGoal(matchDetailElement)) {

            String assistBy = "";

            if (isThereAnAssist(matchDetailElement)) {
                assistBy = getAssistBy(homeOrAwayElement);
            }

            String actualScore = matchDetailElement.getElementsByAttributeValue(DATA_TYPE, "score").get(0).text();
            String goalType = getGoalType(elementThatContainsGoalAndCardInformation);

            matchDetail = new GoalDetails(minuteOccured, occuredByTeam, goalType, playerName, assistBy, actualScore);

        } else {

            String cardType = getCardType(elementThatContainsGoalAndCardInformation);
            matchDetail = new CardsDetails(minuteOccured, occuredByTeam, cardType, playerName);

        }
        return matchDetail;
    }

    private boolean isCurrentElementAMatchDetailElement(Element matchDetailElement) {
        return !matchDetailElement.getElementsByAttributeValue(DATA_TYPE, "time").text().replace("'", "").equals("");
    }

    private boolean isThereAnAssist(Element element) {
        return !element.getElementsByAttributeValue(DATA_TYPE, "sub-incident").isEmpty();
    }

    private Elements getHomeOrAwayElement(String occuredByTeam, Element element) {
        return occuredByTeam.equals(HOME_TEAM) ? element.getElementsByAttributeValue(DATA_TYPE, "home") : element.getElementsByAttributeValue(DATA_TYPE, "away");
    }

    private Elements getElementWithGoalAndCardInformation(String occuredByTeam, Element element) {

        return occuredByTeam.equals(HOME_TEAM) ?
                element.getElementsByAttributeValue(DATA_TYPE, "home-icon") :
                element.getElementsByAttributeValue(DATA_TYPE, "away-icon");
    }

    private String getPlayerName(Elements homeOrAwayElement) {
        return homeOrAwayElement.get(0).getElementsByAttributeValue(DATA_TYPE, "player-name").text();
    }

    private String getAssistBy(Elements homeOrAwayElement) {
        return homeOrAwayElement.get(0).getElementsByAttributeValue(DATA_TYPE, "sub-incident").get(0).text();
    }

    private String getGoalType(Elements elements) {
        return elements.hasClass("goal-pen") ? "penalty" : elements.hasClass("goal-own") ? "own-goal" : "regular";
    }

    private String getCardType(Elements elements) {
        return elements.hasClass("yellowcard") ? "yellow" : "red";
    }

    private boolean isMatchDetailAGoal(Element element) {
        return element.getElementsByAttributeValue(DATA_TYPE, "middle").get(0).getElementsByAttributeValue(DATA_TYPE, "score").text().length() > 2;
    }

    private String occuredByTeam(Element element) {
        return element.getElementsByAttributeValue(DATA_TYPE, "home").get(0).getElementsByAttributeValue(DATA_TYPE, "player-name").text().length() > 2 ? HOME_TEAM : AWAY_TEAM;
    }

}
