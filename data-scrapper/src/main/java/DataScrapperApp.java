import com.mongodb.MongoClient;
import dao.MatchDAO;
import dao.MorphiaDatastore;

public class DataScrapperApp {

    private static String MONGO_DB_HOST = "localhost";
    private static int MONGO_DB_PORT = 28008;
    private static String MONGO_DB_NAME = "stats";

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient(MONGO_DB_HOST, MONGO_DB_PORT);
        MorphiaDatastore morphiaDatastore = new MorphiaDatastore(mongoClient, MONGO_DB_NAME);

        MatchDAO matchDAO = new MatchDAO(morphiaDatastore);

        LiveScoreScrapper liveScoreScrapper = new LiveScoreScrapper();
        liveScoreScrapper.main();

    }

}
