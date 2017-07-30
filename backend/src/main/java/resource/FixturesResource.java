package resource;

import controller.FixturesController;
import model.Fixture;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
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
    public Response getUpcomingFixtures(int leagueId, @Context final Request request) {
        // TODO Remove leagueId

        List<Fixture> upcomingFixtures = this.fixturesController.getUpcomingFixtures(leagueId);

        // TODO This expects list to be sorted e.g. first match never changes, check if okay for ETag

        final EntityTag eTag = new EntityTag(String.valueOf(upcomingFixtures.get(0).hashCode()));

        CacheControl cc = new CacheControl();
        cc.setMaxAge(604800);

        Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(eTag);

        if (responseBuilder == null) {
            return Response.ok(upcomingFixtures)
                    .tag(eTag)
                    .cacheControl(cc)
                    .build();
        }

        return Response.notModified()
                .tag(eTag)
                .cacheControl(cc)
                .build();
    }

}
