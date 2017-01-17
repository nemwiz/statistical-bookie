import org.junit.Before;
import org.junit.Test;
import scrapper.csv.helper.ScrapperHelper;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ScrapperHelperTest {

    private String currentSeason;
    private String nextSeason;
    private String previousSeason;

    private static final int MONTH_OF_JULY = 7;

    @Before
    public void setUp() {
        this.currentSeason = String.valueOf(LocalDate.now().getYear());
        this.nextSeason = String.valueOf(LocalDate.now().getYear() + 1);
        this.previousSeason = String.valueOf(LocalDate.now().getYear() - 1);
    }

    @Test
    public void aSeasonYearStringWithoutSlashIsReturnedDependingOnWhichMonthCurrentlyIs() {

        String expectedResult;
        String actualResult = ScrapperHelper.getCurrentSeasonYearShortFormat();

        if (LocalDate.now().getMonthValue() > MONTH_OF_JULY) {
            expectedResult = currentSeason.substring(2, 4) + nextSeason.substring(2, 4);
        } else {
            expectedResult = previousSeason.substring(2, 4) + currentSeason.substring(2, 4);
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void aSeasonYearStringWithSlashIsReturnedDependingOnWhichMonthCurrentlyIs() {

        String expectedResult;
        String actualResult = ScrapperHelper.getCurrentSeasonYearWithDash(LocalDate.now());

        if (LocalDate.now().getMonthValue() > MONTH_OF_JULY) {
            expectedResult = currentSeason + "/" + nextSeason;
        } else {
            expectedResult = previousSeason + "/" + currentSeason;
        }

        assertEquals(expectedResult, actualResult);
    }
}
