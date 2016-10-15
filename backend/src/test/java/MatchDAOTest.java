import dao.MatchDAO;
import dao.MorphiaDatastore;
import model.Match;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MatchDAOTest {

    private MatchDAO matchDAO;
    private MorphiaDatastore morphiaDatastore;
    private Datastore datastore;
    private List<Match> dummyMatches;

//    @Before
//    public void setUp() {
//
//        morphiaDatastore = mock(MorphiaDatastore.class);
//        datastore = mock(Datastore.class);
//        dummyMatches = new ArrayList<>();
//
//        dummyMatches.add(new Match());
//        dummyMatches.add(new Match());
//        dummyMatches.add(new Match());
//        dummyMatches.add(new Match());
//
//        when(morphiaDatastore.getDatastore()).thenReturn(datastore);
//        when(morphiaDatastore.getDatastore()
//        .createQuery(Match.class)
//        .asList())
//        .thenReturn(dummyMatches);
//
//        matchDAO = new MatchDAO(morphiaDatastore);
//
//    }
//
//    @Test
//    public void ensureMatchesAreNotEmpty() {
//
//        List<Match> matchList = matchDAO.getMatchesByTeamName("Chelsea");
//        assertTrue(matchList.size() == 3);
//
//    }


}
