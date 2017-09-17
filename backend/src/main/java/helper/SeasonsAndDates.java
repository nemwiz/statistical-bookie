package helper;

import org.joda.time.DateTime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class SeasonsAndDates {

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

    public static List<String> getEachDayOfTheCurrentWeek() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        List<String> days = new ArrayList<>();

        LocalDate now = LocalDate.now();
        LocalDate first = now.with(previousOrSame(DayOfWeek.MONDAY));

        for (DayOfWeek day: DayOfWeek.values()) {
            days.add(first.with(nextOrSame(day)).format(formatter));
        }

        return days;
    }
}
