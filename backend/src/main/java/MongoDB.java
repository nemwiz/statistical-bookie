import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    private static final String DATABASE_NAME = "stats";

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public MongoDB() {
    }

    public MongoDB(String host, String port) {
        this.mongoClient = new MongoClient(host, Integer.parseInt(port));
        this.mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
