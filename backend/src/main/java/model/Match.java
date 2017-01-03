package model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("matches")
public class Match {

    @Id
    private int id;
    private String countryName;
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

    public Match() {
    }

    public long getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public void setFinalOutcome(String finalOutcome) {
        this.finalOutcome = finalOutcome;
    }

    public void setHomeTeamHalftimeGoals(int homeTeamHalftimeGoals) {
        this.homeTeamHalftimeGoals = homeTeamHalftimeGoals;
    }

    public void setAwayTeamHalftimeGoals(int awayTeamHalftimeGoals) {
        this.awayTeamHalftimeGoals = awayTeamHalftimeGoals;
    }

    public void setHalfTimeOutcome(String halfTimeOutcome) {
        this.halfTimeOutcome = halfTimeOutcome;
    }

    public void setHomeTeamShots(int homeTeamShots) {
        this.homeTeamShots = homeTeamShots;
    }

    public void setAwayTeamShots(int awayTeamShots) {
        this.awayTeamShots = awayTeamShots;
    }

    public void setHomeTeamShotsOnTarget(int homeTeamShotsOnTarget) {
        this.homeTeamShotsOnTarget = homeTeamShotsOnTarget;
    }

    public void setAwayTeamShotsOnTarget(int awayTeamShotsOnTarget) {
        this.awayTeamShotsOnTarget = awayTeamShotsOnTarget;
    }

    public void setHomeTeamCorners(int homeTeamCorners) {
        this.homeTeamCorners = homeTeamCorners;
    }

    public void setAwayTeamCorners(int awayTeamCorners) {
        this.awayTeamCorners = awayTeamCorners;
    }

    public void setHomeTeamFouls(int homeTeamFouls) {
        this.homeTeamFouls = homeTeamFouls;
    }

    public void setAwayTeamFouls(int awayTeamFouls) {
        this.awayTeamFouls = awayTeamFouls;
    }

    public void setHomeTeamYellowCards(int homeTeamYellowCards) {
        this.homeTeamYellowCards = homeTeamYellowCards;
    }

    public void setAwayTeamYellowCards(int awayTeamYellowCards) {
        this.awayTeamYellowCards = awayTeamYellowCards;
    }

    public void setHomeTeamRedCards(int homeTeamRedCards) {
        this.homeTeamRedCards = homeTeamRedCards;
    }

    public void setAwayTeamRedCards(int awayTeamRedCards) {
        this.awayTeamRedCards = awayTeamRedCards;
    }
}