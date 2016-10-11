package controller;

import dao.MatchDAO;
import model.Match;

import java.util.List;

public class MatchController {

    private MatchDAO matchDAO;

    public MatchController(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> getAllMatchesByTeamName(String teamName) {
        return this.matchDAO.getMatchesByTeamName(teamName);
    }

}
