package resource;

import com.codahale.metrics.annotation.Timed;
import controller.MatchController;
import model.Match;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/match")
@Produces(MediaType.APPLICATION_JSON)
public class NumberOfGoalsPerMatchResource {

    private MatchController matchController;

    public NumberOfGoalsPerMatchResource(MatchController matchController) {
        this.matchController = matchController;
    }

//    @GET
//    @Timed
//    public NumberOfGoalsModel getNumberOfGoalsFromLastMatches(@QueryParam("homeTeamName") String homeTeamName) {
//        return matchController.getNumberOfGoalsFromLastMatches(homeTeamName);
//    }

    @GET
    @Timed
    public List<Match> getMatches(@QueryParam("homeTeamName") String homeTeamName) {
        return matchController.getMatches(homeTeamName);
    }

}
