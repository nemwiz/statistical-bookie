import com.fasterxml.jackson.annotation.JsonProperty;
import com.meltmedia.dropwizard.mongo.MongoConfiguration;
import io.dropwizard.Configuration;

public class DataScrapperConfiguration extends Configuration {

    @JsonProperty
    protected MongoConfiguration mongo;

    public MongoConfiguration getMongo() {
        return mongo;
    }

    public void setMongo(MongoConfiguration mongo) {
        this.mongo = mongo;
    }
}
