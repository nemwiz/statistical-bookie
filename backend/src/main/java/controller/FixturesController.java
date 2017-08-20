package controller;

import dao.FixturesDAO;
import helper.SeasonsAndDates;
import model.Fixture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
