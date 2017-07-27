package resource;

import controller.FixturesController;
import model.Fixture;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/fixtures")
@Produces(MediaType.APPLICATION_JSON)
public class FixturesResource {

    private FixturesController fixturesController;

    public FixturesResource(FixturesController fixturesController) {
        this.fixturesController = fixturesController;
    }

    @GET
    @Path("/upcoming")
    public List<Fixture> getUpcomingFixtures(int leagueId) {

        // TODO - check if league id is null and return bad requst if so
        System.out.println("leagueId = " + leagueId);
        return this.fixturesController.getUpcomingFixtures(leagueId);
    }

}
