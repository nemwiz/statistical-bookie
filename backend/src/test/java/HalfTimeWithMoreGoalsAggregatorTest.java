import aggregator.HalfTimeWithMoreGoalsAggregator;
import model.Match;
import org.junit.Before;
import org.junit.Test;
import viewmodel.HalfTimeWithMoreGoals;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class HalfTimeWithMoreGoalsAggregatorTest {

    private HalfTimeWithMoreGoalsAggregator halfTimeWithMoreGoalsAggregator;
    private HalfTimeWithMoreGoals halfTimeWithMoreGoals;
    private List<Match> matches;

    @Before
    public void setUp() {
        this.matches = new ArrayList<>();
    }

    @Test
    public void moreGoalsAreScoredInFirstHalfTime() {

        long numberOfMatchesWhereMoreGoalsAreScoredInFirstHalfTime = 7;
        int firstHalfTimeGoals = 4;
        int totalGoals = 6;
        setUpMatchesWithNumberOfGoalsOnHalfTime(numberOfMatchesWhereMoreGoalsAreScoredInFirstHalfTime,
                firstHalfTimeGoals, totalGoals);
        halfTimeWithMoreGoalsAggregator = new HalfTimeWithMoreGoalsAggregator();

        halfTimeWithMoreGoals = halfTimeWithMoreGoalsAggregator.getAggregatedCount(this.matches);

        assertEquals(numberOfMatchesWhereMoreGoalsAreScoredInFirstHalfTime, halfTimeWithMoreGoals.getMoreGoalsWillBeScoredInFirstHalfTime());
    }

    @Test
    public void moreGoalsAreScoredInSecondHalfTime() {

        long numberOfMatchesWhereMoreGoalsAreScoredInSecondHalfTime = 5;
        int firstHalfTimeGoals = 1;
        int totalGoals = 5;
        setUpMatchesWithNumberOfGoalsOnHalfTime(numberOfMatchesWhereMoreGoalsAreScoredInSecondHalfTime,
                firstHalfTimeGoals, totalGoals);
        halfTimeWithMoreGoalsAggregator = new HalfTimeWithMoreGoalsAggregator();

        halfTimeWithMoreGoals = halfTimeWithMoreGoalsAggregator.getAggregatedCount(this.matches);

        assertEquals(numberOfMatchesWhereMoreGoalsAreScoredInSecondHalfTime, halfTimeWithMoreGoals.getMoreGoalsWillBeScoredInSecondtHalfTime());
    }

    @Test
    public void evenNumberOfGoalsIsScoredInBothHalfTimes() {

        long numberOfMatchesWhereEvenNumberOfGoalsIsScoredInBothHalfTimes = 12;
        int firstHalfTimeGoals = 3;
        int totalGoals = 6;
        setUpMatchesWithNumberOfGoalsOnHalfTime(numberOfMatchesWhereEvenNumberOfGoalsIsScoredInBothHalfTimes,
                firstHalfTimeGoals, totalGoals);
        halfTimeWithMoreGoalsAggregator = new HalfTimeWithMoreGoalsAggregator();

        halfTimeWithMoreGoals = halfTimeWithMoreGoalsAggregator.getAggregatedCount(this.matches);

        assertEquals(numberOfMatchesWhereEvenNumberOfGoalsIsScoredInBothHalfTimes, halfTimeWithMoreGoals.getEvenNumberOfGoalsWillBeScoredInBothHalfTimes());
    }


    private void setUpMatchesWithNumberOfGoalsOnHalfTime(long numberOfMatchesToCreate,
                                                         int numberOfGoalsFirstHalfTime,
                                                         int totalNumberOfGoals) {

        for (int i = 0; i < numberOfMatchesToCreate; i++) {

            Match match = new Match();

            match.setHomeTeamGoals(totalNumberOfGoals);
            match.setHomeTeamHalftimeGoals(numberOfGoalsFirstHalfTime);

            matches.add(match);

        }

    }

}
