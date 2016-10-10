package model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("matches")
public class Match {

    @Id
    private int id;
    private String leagueName;
    private String date;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private String finalOutcome;
    private int homeTeamHalftimeGoals;
    private int awayTeamHalftimeGoals;
    private String halfTimeOutcome;
    private int homeTeamShots;
    private int awayTeamShots;
    private int homeTeamShotsOnTarget;
    private int awayTeamShotsOnTarget;
    private int homeTeamCorners;
    private int awayTeamCorners;
    private int homeTeamFouls;
    private int awayTeamFouls;
    private int homeTeamYellowCards;
    private int awayTeamYellowCards;
    private int homeTeamRedCards;
    private int awayTeamRedCards;

    public long getId() {
        return id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getDate() {
        return date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public String getFinalOutcome() {
        return finalOutcome;
    }

    public int getHomeTeamHalftimeGoals() {
        return homeTeamHalftimeGoals;
    }

    public int getAwayTeamHalftimeGoals() {
        return awayTeamHalftimeGoals;
    }

    public String getHalfTimeOutcome() {
        return halfTimeOutcome;
    }

    public int getHomeTeamShots() {
        return homeTeamShots;
    }

    public int getAwayTeamShots() {
        return awayTeamShots;
    }

    public int getHomeTeamShotsOnTarget() {
        return homeTeamShotsOnTarget;
    }

    public int getAwayTeamShotsOnTarget() {
        return awayTeamShotsOnTarget;
    }

    public int getHomeTeamCorners() {
        return homeTeamCorners;
    }

    public int getAwayTeamCorners() {
        return awayTeamCorners;
    }

    public int getHomeTeamFouls() {
        return homeTeamFouls;
    }

    public int getAwayTeamFouls() {
        return awayTeamFouls;
    }

    public int getHomeTeamYellowCards() {
        return homeTeamYellowCards;
    }

    public int getAwayTeamYellowCards() {
        return awayTeamYellowCards;
    }

    public int getHomeTeamRedCards() {
        return homeTeamRedCards;
    }

    public int getAwayTeamRedCards() {
        return awayTeamRedCards;
    }
}