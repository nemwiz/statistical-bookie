package controller;

import dao.LeaguesDAO;
import model.League;

import java.util.List;

public class LeaguesController {

    private LeaguesDAO leaguesDAO;

    public LeaguesController(LeaguesDAO leaguesDAO) {
        this.leaguesDAO = leaguesDAO;
    }

    public List<League> getAllLeagues() {
        return this.leaguesDAO.getAllLeagues();
    }

    public String getLeagueTable() {
        return "League table";
    }
}
