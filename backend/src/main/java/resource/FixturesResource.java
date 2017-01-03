package resource;

import com.codahale.metrics.annotation.Timed;
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
    @Timed
    public List<Fixture> returnAllFixtures() {
        return this.fixturesController.getFixtures();
    }

}
