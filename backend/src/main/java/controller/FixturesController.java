package controller;

import dao.FixturesDAO;
import model.Fixture;

import java.util.List;

public class FixturesController {

    private FixturesDAO fixturesDAO;

    public FixturesController(FixturesDAO fixturesDAO) {
        this.fixturesDAO = fixturesDAO;
    }

    public List<Fixture> getUpcomingFixtures(int leagueId) {
        return this.fixturesDAO.getUpcomingFixtures(leagueId);
    }
}
