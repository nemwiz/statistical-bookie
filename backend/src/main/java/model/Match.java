package model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

@Entity("matches")
@Indexes({
        @Index(fields = @Field("homeTeam")),
        @Index(fields = @Field("awayTeam"))
})
public class Match {

    @Id
    private ObjectId id;
    private String leagueCode;
    private String countryName;
    private String leagueName;
    private String date;
    private String seasonYear;
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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLeagueCode() {
        return leagueCode;
    }

    public void setLeagueCode(String leagueCode) {
        this.leagueCode = leagueCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(String seasonYear) {
        this.seasonYear = seasonYear;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public String getFinalOutcome() {
        return finalOutcome;
    }

    public void setFinalOutcome(String finalOutcome) {
        this.finalOutcome = finalOutcome;
    }

    public int getHomeTeamHalftimeGoals() {
        return homeTeamHalftimeGoals;
    }

    public void setHomeTeamHalftimeGoals(int homeTeamHalftimeGoals) {
        this.homeTeamHalftimeGoals = homeTeamHalftimeGoals;
    }

    public int getAwayTeamHalftimeGoals() {
        return awayTeamHalftimeGoals;
    }

    public void setAwayTeamHalftimeGoals(int awayTeamHalftimeGoals) {
        this.awayTeamHalftimeGoals = awayTeamHalftimeGoals;
    }

    public String getHalfTimeOutcome() {
        return halfTimeOutcome;
    }

    public void setHalfTimeOutcome(String halfTimeOutcome) {
        this.halfTimeOutcome = halfTimeOutcome;
    }

    public int getHomeTeamShots() {
        return homeTeamShots;
    }

    public void setHomeTeamShots(int homeTeamShots) {
        this.homeTeamShots = homeTeamShots;
    }

    public int getAwayTeamShots() {
        return awayTeamShots;
    }

    public void setAwayTeamShots(int awayTeamShots) {
        this.awayTeamShots = awayTeamShots;
    }

    public int getHomeTeamShotsOnTarget() {
        return homeTeamShotsOnTarget;
    }

    public void setHomeTeamShotsOnTarget(int homeTeamShotsOnTarget) {
        this.homeTeamShotsOnTarget = homeTeamShotsOnTarget;
    }

    public int getAwayTeamShotsOnTarget() {
        return awayTeamShotsOnTarget;
    }

    public void setAwayTeamShotsOnTarget(int awayTeamShotsOnTarget) {
        this.awayTeamShotsOnTarget = awayTeamShotsOnTarget;
    }

    public int getHomeTeamCorners() {
        return homeTeamCorners;
    }

    public void setHomeTeamCorners(int homeTeamCorners) {
        this.homeTeamCorners = homeTeamCorners;
    }

    public int getAwayTeamCorners() {
        return awayTeamCorners;
    }

    public void setAwayTeamCorners(int awayTeamCorners) {
        this.awayTeamCorners = awayTeamCorners;
    }

    public int getHomeTeamFouls() {
        return homeTeamFouls;
    }

    public void setHomeTeamFouls(int homeTeamFouls) {
        this.homeTeamFouls = homeTeamFouls;
    }

    public int getAwayTeamFouls() {
        return awayTeamFouls;
    }

    public void setAwayTeamFouls(int awayTeamFouls) {
        this.awayTeamFouls = awayTeamFouls;
    }

    public int getHomeTeamYellowCards() {
        return homeTeamYellowCards;
    }

    public void setHomeTeamYellowCards(int homeTeamYellowCards) {
        this.homeTeamYellowCards = homeTeamYellowCards;
    }

    public int getAwayTeamYellowCards() {
        return awayTeamYellowCards;
    }

    public void setAwayTeamYellowCards(int awayTeamYellowCards) {
        this.awayTeamYellowCards = awayTeamYellowCards;
    }

    public int getHomeTeamRedCards() {
        return homeTeamRedCards;
    }

    public void setHomeTeamRedCards(int homeTeamRedCards) {
        this.homeTeamRedCards = homeTeamRedCards;
    }

    public int getAwayTeamRedCards() {
        return awayTeamRedCards;
    }

    public void setAwayTeamRedCards(int awayTeamRedCards) {
        this.awayTeamRedCards = awayTeamRedCards;
    }
}