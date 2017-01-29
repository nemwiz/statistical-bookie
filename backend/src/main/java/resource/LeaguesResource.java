package resource;

import com.codahale.metrics.annotation.Timed;
import controller.LeaguesController;
import model.Fixture;
import model.League;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/leagues")
@Produces(MediaType.APPLICATION_JSON)
public class LeaguesResource {

    private LeaguesController leaguesController;
    @Context
    private ResourceContext resourceContext;

    public LeaguesResource(LeaguesController leaguesController) {
        this.leaguesController = leaguesController;
    }

    @GET
    @Timed
    public List<League> getAllLeagues() {
        return this.leaguesController.getAllLeagues();
    }

    @GET
    @Timed
    @Path("{leagueId}/leaguetable")
    public String getLeagueTable() {
        return this.leaguesController.getLeagueTable();
    }

    @Path("{leagueId}/fixtures/upcoming")
    @GET
    @Timed

    public List<Fixture> getSubResource(@PathParam("leagueId") int leagueId) {
        return this.resourceContext.getResource(FixturesResource.class).getUpcomingFixtures(leagueId);
    }

}
