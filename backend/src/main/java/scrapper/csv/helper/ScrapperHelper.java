package scrapper.csv.helper;

import org.joda.time.DateTime;

import java.time.LocalDate;

public class ScrapperHelper {

    private static final int MONTH_OF_JULY = 7;

    public static String getCurrentSeasonYearShortFormat() {

        String currentSeason = DateTime.now().year().getAsShortText().substring(2, 4);

        if (DateTime.now().monthOfYear().get() >= MONTH_OF_JULY) {
            String nextSeason = String.valueOf(DateTime.now().year().get() + 1).substring(2, 4);
            return currentSeason + nextSeason;
        } else {
            String previousSeason = String.valueOf(DateTime.now().year().get() - 1).substring(2, 4);
            return previousSeason + currentSeason;
        }

    }

    public static String getCurrentSeasonYearWithDash(LocalDate date) {

        String currentSeason = String.valueOf(date.getYear());

        if (date.getMonthValue() >= MONTH_OF_JULY) {
            String nextSeason = String.valueOf(date.getYear() + 1);
            return addForwardSlash(currentSeason, nextSeason);
        } else {
            String previousSeason = String.valueOf(date.getYear() - 1);
            return addForwardSlash(previousSeason, currentSeason);
        }
    }

    private static String addForwardSlash(String season1, String season2) {
        return season1 + "/" + season2;
    }
}
