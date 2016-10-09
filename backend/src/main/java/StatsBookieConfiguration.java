import com.esotericsoftware.kryo.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

class StatsBookieConfiguration extends Configuration {

    @NotEmpty
    private String databaseUrl;
    @NotEmpty
    private String databasePort;

    @JsonProperty
    public String getDatabaseUrl() {
        return databaseUrl;
    }

    @JsonProperty
    public String getDatabasePort() {
        return databasePort;
    }

    @JsonProperty
    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    @JsonProperty
    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort;
    }
}
