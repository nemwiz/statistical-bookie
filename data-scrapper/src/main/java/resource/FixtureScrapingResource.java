package resource;

import livescore.FixtureScraper;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/fixtures")
@Produces(MediaType.APPLICATION_JSON)
public class FixtureScrapingResource {

    private static String X_API_KEY_HEADER = "x-api-key";
    private FixtureScraper fixtureScraper;
    private String apiKey;

    public FixtureScrapingResource(FixtureScraper fixtureScraper, String apiKey) {
        this.fixtureScraper = fixtureScraper;
        this.apiKey = apiKey;
    }

    @POST
    public Response scrapeUpcomingFixtures(
            @Context final HttpHeaders headers) {

        String apiKey = headers.getRequestHeader(X_API_KEY_HEADER).get(0);

        if (!apiKey.equals(this.apiKey)) {
            return Response.noContent().build();
        } else {

            new Thread(() -> fixtureScraper.scrapeUpcomingFixtures()).start();
            return Response.ok().build();
        }
    }

    @POST
    @Path("/{leagueId}")
    public Response scrapeUpcomingFixturesForSpecificLeague(
            @PathParam("leagueId") String leagueId,
            @Context final HttpHeaders headers) {

        String apiKey = headers.getRequestHeader(X_API_KEY_HEADER).get(0);

        if (!apiKey.equals(this.apiKey)) {
            return Response.noContent().build();
        } else {
            new Thread(() -> fixtureScraper.scrapeUpcomingFixturesForSpecificLeague(leagueId)).start();
            return Response.ok().build();
        }
    }

}
