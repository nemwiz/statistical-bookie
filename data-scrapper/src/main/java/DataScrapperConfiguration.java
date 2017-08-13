import com.fasterxml.jackson.annotation.JsonProperty;
import com.meltmedia.dropwizard.mongo.MongoConfiguration;
import io.dropwizard.Configuration;

public class DataScrapperConfiguration extends Configuration {

    @JsonProperty
    protected MongoConfiguration mongo;
    @JsonProperty
    protected String apiKey;

    public MongoConfiguration getMongo() {
        return mongo;
    }

    public void setMongo(MongoConfiguration mongo) {
        this.mongo = mongo;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
