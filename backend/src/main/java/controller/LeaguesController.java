package controller;

import aggregator.LeagueTableAggregator;
import dao.LeaguesDAO;
import dao.MatchDAO;
import model.League;
import model.Match;
import viewmodel.LeagueTable;

import java.util.List;

public class LeaguesController {

    private LeaguesDAO leaguesDAO;
    private MatchDAO matchDAO;

    public LeaguesController(LeaguesDAO leaguesDAO, MatchDAO matchDAO) {
        this.leaguesDAO = leaguesDAO;
        this.matchDAO = matchDAO;
    }

    public List<League> getAllLeagues() {
        return this.leaguesDAO.getAllLeagues();
    }

    public List<LeagueTable> getLeagueTable(String leagueCode) {

        LeagueTableAggregator leagueTableAggregator = new LeagueTableAggregator();

        List<Match> allLeagueMatches = this.matchDAO.getAllMatchesForCurrentSeason(leagueCode);

        return leagueTableAggregator.aggregate(allLeagueMatches);

    }
}
