package resource;

import livescore.MatchScraper;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/matches")
@Produces(MediaType.APPLICATION_JSON)
public class MatchScrapingResource {

    private static String X_API_KEY_HEADER = "x-api-key";
    private MatchScraper matchScraper;
    private String apiKey;

    public MatchScrapingResource(MatchScraper matchScraper, String apiKey) {
        this.matchScraper = matchScraper;
        this.apiKey = apiKey;
    }

    @POST
    public Response scrapeAllMatchesForAllLeagues(@Context final HttpHeaders headers) {
        String apiKey = headers.getRequestHeader(X_API_KEY_HEADER).get(0);

        if (!apiKey.equals(this.apiKey)) {
            return Response.noContent().build();
        } else {
            new Thread(() -> matchScraper.scrapeAll()).start();
            return Response.ok().build();
        }
    }

    @POST
    @Path("/{leagueCode}")
    public Response scrapeAllMatchesForSpecificLeague(
            @Context final HttpHeaders headers,
            @PathParam("leagueCode") String leagueCode) {

        String apiKey = headers.getRequestHeader(X_API_KEY_HEADER).get(0);

        if (!apiKey.equals(this.apiKey)) {
            return Response.noContent().build();
        } else {
            new Thread(() -> matchScraper.scrapeSpecificLeague(leagueCode)).start();
            return Response.ok().build();
        }
    }

    @POST
    @Path("/{leagueCode}/round/{round}")
    public Response scrapeLeagueForSpecifiedRound(
            @Context final HttpHeaders headers,
            @PathParam("leagueCode") String leagueCode,
            @PathParam("round") int round) {

        String apiKey = headers.getRequestHeader(X_API_KEY_HEADER).get(0);

        if (!apiKey.equals(this.apiKey)) {
            return Response.noContent().build();
        } else {
            new Thread(() -> matchScraper.scrapeLeagueForSpecifiedRound(round, leagueCode)).start();
            return Response.ok().build();
        }
    }

    @POST
    @Path("/round/{round}")
    public Response scrapeAllMatchesFromSpecifiedRoundToCurrentRound(
            @Context final HttpHeaders headers,
            @PathParam("round") int round) {

        String apiKey = headers.getRequestHeader(X_API_KEY_HEADER).get(0);

        if (!apiKey.equals(this.apiKey)) {
            return Response.noContent().build();
        } else {
            new Thread(() -> matchScraper.scrapeAllMatchesFromSpecifiedRoundToCurrentRound(round)).start();
            return Response.ok().build();
        }
    }

    @POST
    @Path("/round/{round}/{leagueCode}")
    public Response scrapeSpecificLeagueFromSpecificRound(
            @Context final HttpHeaders headers,
            @PathParam("round") int round,
            @PathParam("leagueCode") String leagueCode) {

        String apiKey = headers.getRequestHeader(X_API_KEY_HEADER).get(0);

        if (!apiKey.equals(this.apiKey)) {
            return Response.noContent().build();
        } else {
            new Thread(() -> matchScraper.scrapeSpecificLeagueFromSpecificRound(round, leagueCode)).start();
            return Response.ok().build();
        }
    }

}
