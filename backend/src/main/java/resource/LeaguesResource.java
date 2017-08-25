package resource;

import controller.LeaguesController;
import model.League;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
        cc.setMaxAge(15778463);

        return Response.ok(leagueList)
                .cacheControl(cc)
                .build();
    }

}
