package resource;

import com.codahale.metrics.annotation.Timed;
import controller.MainController;
import model.Match;
import viewmodel.AggregatedMatchesMetaView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/match")
@Produces(MediaType.APPLICATION_JSON)
public class NumberOfGoalsPerMatchResource {

    private MainController mainController;

    public NumberOfGoalsPerMatchResource(MainController mainController) {
        this.mainController = mainController;
    }


    @GET
    @Timed
    public List<Match> getMatches(@QueryParam("homeTeamName") String homeTeamName) {
        return mainController.getMatches(homeTeamName);
    }

    @Path("/aggregate")
    @GET
    @Timed
    public AggregatedMatchesMetaView getMetaView(@QueryParam("homeTeamName") String homeTeamName) {
        return mainController.getAggregatedMatches(homeTeamName);
    }

}
