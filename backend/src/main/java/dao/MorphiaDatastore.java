package dao;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class MorphiaDatastore {

    private static final String MODEL_PACKAGE_PATH = "src.main.java.model";

    private MongoClient mongoClient;
    private Morphia morphia;
    private Datastore datastore;

    public MorphiaDatastore(MongoClient mongoClient, String databaseName) {
        this.mongoClient = mongoClient;
        this.morphia = new Morphia();
        this.morphia.mapPackage(MODEL_PACKAGE_PATH);
        this.datastore = morphia.createDatastore(this.mongoClient, databaseName);
    }

    public Datastore getDatastore() {
        return datastore;
    }
}
