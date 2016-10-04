package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Match {

    private String homeTeam;

    public Match() {
    }

    public Match(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    @JsonProperty
    public String getHomeTeam() {
        return homeTeam;
    }

}
