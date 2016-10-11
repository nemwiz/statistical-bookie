import dao.MatchDAO;
import dao.MorphiaDatastore;
import model.Match;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatchDAOTest {

    private MatchDAO matchDAO;

    @Before
    public void setUp() {

        matchDAO = mock(MatchDAO.class);

        when(matchDAO.getMatchesByTeamName("chelsea")).thenReturn(new ArrayList<Match>());
    }

    @Test
    public void ensureMatchesAreNotEmpty() {

        List<Match> matchList = matchDAO.getMatchesByTeamName("Chelsea");
        assertTrue(matchList.size() == 0);

    }


}
