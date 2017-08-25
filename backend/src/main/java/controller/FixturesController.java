package controller;

import dao.FixturesDAO;
import helper.SeasonsAndDates;
import model.Fixture;

import java.util.List;

public class FixturesController {

    private FixturesDAO fixturesDAO;

    public FixturesController(FixturesDAO fixturesDAO) {
        this.fixturesDAO = fixturesDAO;
    }

    public List<Fixture> getUpcomingFixtures() {

        List<String> daysOfCurrentWeek = SeasonsAndDates.getEachDayOfTheCurrentWeek();
        return this.fixturesDAO.getUpcomingFixtures(daysOfCurrentWeek);
    }
}
