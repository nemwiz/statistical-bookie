package helper;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
        Calendar now = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

        String[] days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) - 1; //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, delta );
        for (int i = 0; i < 7; i++)
        {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }

        return Arrays.asList(days);
    }
}
