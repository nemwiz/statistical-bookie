package resource;

import com.codahale.metrics.annotation.Timed;
import controller.MatchController;
import dao.MatchDAO;
import model.Match;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/match")
@Produces(MediaType.APPLICATION_JSON)
public class MatchResource {

    private MatchController matchController;

    public MatchResource(MatchController matchController) {
        this.matchController = matchController;
    }

    @GET
    @Timed
    public List<Match> getAllMatches(@QueryParam("teamName") String teamName) {
        return matchController.getAllMatchesByTeamName(teamName);
    }

}
