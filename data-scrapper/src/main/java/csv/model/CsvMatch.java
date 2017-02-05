package csv.model;

import com.univocity.parsers.annotations.Parsed;

public class CsvMatch {

    @Parsed(field = "Div")
    private String divisionName;
    @Parsed(field = "Date")
    private String date;
    @Parsed(field = "HomeTeam")
    private String homeTeam;
    @Parsed(field = "AwayTeam")
    private String awayTeam;
    @Parsed(field = "FTHG")
    private int homeTeamGoals;
    @Parsed(field = "FTAG")
    private int awayTeamGoals;
    @Parsed(field = "FTR")
    private String finalOutcome;
    @Parsed(field = "HTHG")
    private int homeTeamHalftimeGoals;
    @Parsed(field = "HTAG")
    private int awayTeamHalftimeGoals;
    @Parsed(field = "HTR")
    private String halfTimeOutcome;

    public String getDivisionName() {
        return divisionName;
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

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
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

    @Override
    public String toString() {
        return "CsvMatch{" +
                "divisionName='" + divisionName + '\'' +
                ", date='" + date + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                ", finalOutcome='" + finalOutcome + '\'' +
                ", homeTeamHalftimeGoals=" + homeTeamHalftimeGoals +
                ", awayTeamHalftimeGoals=" + awayTeamHalftimeGoals +
                ", halfTimeOutcome='" + halfTimeOutcome + '\'' +
                '}';
    }
}
