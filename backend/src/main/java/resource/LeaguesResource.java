package resource;

import controller.LeaguesController;
import model.League;
import viewmodel.LeagueTable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/leagues")
@Produces(MediaType.APPLICATION_JSON)
public class LeaguesResource {

    private LeaguesController leaguesController;

    public LeaguesResource(LeaguesController leaguesController) {
        this.leaguesController = leaguesController;
    }

    @GET
    public Response getAllLeagues() {

        List<League> leagueList = this.leaguesController.getAllLeagues();

        CacheControl cc = new CacheControl();
        cc.setMaxAge(7778464);

        return Response.ok(leagueList)
                .cacheControl(cc)
                .build();
    }

    @GET
    @Path("/{leagueCode}/table")
    public Response getLeagueTable(@PathParam("leagueCode") String leagueCode) {

        List<LeagueTable> leagueTable = this.leaguesController.getLeagueTable(leagueCode);

        CacheControl cc = new CacheControl();
        cc.setMaxAge(404800);

        return Response.ok(leagueTable)
                .cacheControl(cc)
                .build();
    }

}
