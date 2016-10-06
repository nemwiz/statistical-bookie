package resource;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import model.Match;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/match")
@Produces(MediaType.APPLICATION_JSON)
public class MatchResource {

    @GET
    @Timed
    public Match teamName() {
        return new Match("Chelsea boys in blue, Chelsea Chelsea we love you!");
    }

}
