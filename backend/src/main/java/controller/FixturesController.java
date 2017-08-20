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

    public Map<String, Map<String, List<Fixture>>> getUpcomingFixtures() {

        List<String> daysOfCurrentWeek = SeasonsAndDates.getEachDayOfTheCurrentWeek();
        List<Fixture> myFixtures = this.fixturesDAO.getUpcomingFixtures(daysOfCurrentWeek);

        Map<String, List<Fixture>> groupingByCountry = myFixtures.stream().collect(Collectors.groupingBy(Fixture::getCountryName));
        Map<String, Map<String, List<Fixture>>> groupingByCountryAndDate = new HashMap<>();

        groupingByCountry.forEach((key, value) -> groupingByCountryAndDate.put(key, value.stream().collect(Collectors.groupingBy(Fixture::getDate))));

        return groupingByCountryAndDate;
    }
}
