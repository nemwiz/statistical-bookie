package healthchecks;

import com.codahale.metrics.health.HealthCheck;
import org.mongodb.morphia.Datastore;

public class DatabaseHealthCheck extends HealthCheck {

    private final Datastore database;

    public DatabaseHealthCheck(Datastore mongoDB) {
        this.database = mongoDB;
    }

    protected Result check() throws Exception {

        if (this.database.getDB().getName().isEmpty()) {
            return Result.unhealthy("MorphiaDatastore is null");
        }
        return Result.healthy();

    }
}
