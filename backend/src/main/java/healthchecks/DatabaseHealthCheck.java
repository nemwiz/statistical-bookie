package healthchecks;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.client.MongoDatabase;

public class DatabaseHealthCheck extends HealthCheck{

    private final MongoDatabase mongoDatabase;

    public DatabaseHealthCheck(MongoDatabase mongoDB) {
        this.mongoDatabase = mongoDB;
    }

    protected Result check() throws Exception {

        if(this.mongoDatabase.getName().equals(null)) {
            return Result.healthy();
        }

        return Result.unhealthy("Database is null");
    }
}
