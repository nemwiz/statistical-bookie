package resource;

import com.codahale.metrics.annotation.Timed;
import controller.MainController;
import viewmodel.AggregatedMatchesMetaView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/matches")
@Produces(MediaType.APPLICATION_JSON)
public class NumberOfGoalsPerMatchResource {

    private MainController mainController;

    public NumberOfGoalsPerMatchResource(MainController mainController) {
        this.mainController = mainController;
    }


    @GET
    @Timed
    public List<AggregatedMatchesMetaView> getMatches(@QueryParam("homeTeam") String homeTeam,
                                  @QueryParam("awayTeam") String awayTeam) {
        return mainController.getMatchesByTeamNames(homeTeam, awayTeam);
    }


    @Path("/aggregate/")
    @GET
    @Timed
    public Response getMatchesByTeams(
            @QueryParam("homeTeam") String homeTeam,
            @QueryParam("awayTeam") String awayTeam) {

        CacheControl cc = new CacheControl();
        cc.setMaxAge(604800);

        List<AggregatedMatchesMetaView> aggregatedMatches = this.mainController
                .getMatchesByTeamNames(homeTeam, awayTeam);

        return Response.ok(aggregatedMatches)
                .cacheControl(cc)
                .build();
    }

}
