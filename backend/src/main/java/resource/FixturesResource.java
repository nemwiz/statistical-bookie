package resource;

import controller.FixturesController;
import model.Fixture;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Map;

@Path("/fixtures")
@Produces(MediaType.APPLICATION_JSON)
public class FixturesResource {

    private FixturesController fixturesController;

    public FixturesResource(FixturesController fixturesController) {
        this.fixturesController = fixturesController;
    }

    @GET
    @Path("/upcoming")
    public Response getUpcomingFixtures(@Context final Request request) {

        Map<String, Map<String, List<Fixture>>> upcomingFixtures = this.fixturesController.getUpcomingFixtures();

        CacheControl cc = new CacheControl();
        cc.setMaxAge(604800);

        return Response.ok(upcomingFixtures)
                .cacheControl(cc)
                .build();

    }

}
