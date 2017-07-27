package aggregator;

import collecter.NumberOfGoalsCollecter;
import helper.Constants;
import model.Match;
import viewmodel.NumberOfGoalsModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class NumberOfGoalsAggregator {

    public NumberOfGoalsAggregator() {
    }

    public NumberOfGoalsModel getAggregatedCount(List<Match> matches, String gamePeriod) {

        long[] homeTeamCount = new long[0];
        long[] awayTeamCount = new long[0];
        long[] bothTeamsCount = new long[0];


        switch(gamePeriod) {
            case Constants.FULLTIME:
                homeTeamCount = getCountFullTime(matches, Constants.HOME_TEAM);
                awayTeamCount = getCountFullTime(matches, Constants.AWAY_TEAM);
                bothTeamsCount = getCountFullTime(matches, Constants.BOTH_TEAMS);
                break;
            case Constants.FIRST_HALF:
                homeTeamCount = getCountHalfTime(matches, Constants.HOME_TEAM);
                awayTeamCount = getCountHalfTime(matches, Constants.AWAY_TEAM);
                bothTeamsCount = getCountHalfTime(matches, Constants.BOTH_TEAMS);
                break;
            case Constants.SECOND_HALF:
                homeTeamCount = getCountSecondHalfTime(matches, Constants.HOME_TEAM);
                awayTeamCount = getCountSecondHalfTime(matches, Constants.AWAY_TEAM);
                bothTeamsCount = getCountSecondHalfTime(matches, Constants.BOTH_TEAMS);
                break;
        }

        Map<String, Long> homeTeamResults = this.convertValuesToMap(homeTeamCount);
        Map<String, Long> awayTeamResults = this.convertValuesToMap(awayTeamCount);
        Map<String, Long> bothTeamsResults = this.convertValuesToMap(bothTeamsCount);

        return new NumberOfGoalsModel(
                homeTeamResults,
                awayTeamResults,
                bothTeamsResults
        );
    }

    private long[] getCountFullTime(List<Match> matches, String team) {

        Function<Match, Integer> sumTeamGoalsFunction;

        switch (team) {
            case Constants.HOME_TEAM:
                sumTeamGoalsFunction = NumberOfGoalsCollecter::sumGoalsOfHomeTeam;
                break;
            case Constants.AWAY_TEAM:
                sumTeamGoalsFunction = NumberOfGoalsCollecter::sumGoalsOfAwayTeam;
                break;
            case Constants.BOTH_TEAMS:
                sumTeamGoalsFunction = NumberOfGoalsCollecter::sumGoalsFullTime;
                break;
            default:
                sumTeamGoalsFunction = NumberOfGoalsCollecter::sumGoalsFullTime;
                break;
        }

        long noGoalsFullTime = countMatchesWhereNumberOfGoalsIsZero(matches, sumTeamGoalsFunction);

        long oneGoalFullTime = countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(matches, sumTeamGoalsFunction, Constants.ONE_GOAL);

        long twoGoalsFullTime = countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(matches, sumTeamGoalsFunction, Constants.TWO_GOALS);

        long threeGoalsFullTime = countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(matches, sumTeamGoalsFunction, Constants.THREE_GOALS);

        long fourOrMoreGoalsFullTime = countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(matches, sumTeamGoalsFunction, Constants.FOUR_GOALS);

        return new long[]{
                noGoalsFullTime,
                oneGoalFullTime,
                twoGoalsFullTime,
                threeGoalsFullTime,
                fourOrMoreGoalsFullTime};

    }

    private long[] getCountHalfTime(List<Match> matches, String team) {

        Function<Match, Integer> sumTeamGoalsFunction;

        switch (team) {
            case Constants.HOME_TEAM:
                sumTeamGoalsFunction = Match::getHomeTeamHalftimeGoals;
                break;
            case Constants.AWAY_TEAM:
                sumTeamGoalsFunction = Match::getAwayTeamHalftimeGoals;
                break;
            case Constants.BOTH_TEAMS:
                sumTeamGoalsFunction = NumberOfGoalsCollecter::sumGoalsHalfTime;
                break;
            default:
                sumTeamGoalsFunction = NumberOfGoalsCollecter::sumGoalsHalfTime;
                break;
        }

        long noGoalsOnHalfTime = countMatchesWhereNumberOfGoalsIsZero(matches, sumTeamGoalsFunction);

        long oneGoalOnHalfTime = countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(matches, sumTeamGoalsFunction, Constants.ONE_GOAL);

        long twoGoalsOnHalfTime = countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(matches, sumTeamGoalsFunction, Constants.TWO_GOALS);

        long threeGoalsOnHalfTime = countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(matches, sumTeamGoalsFunction, Constants.THREE_GOALS);

        long fourOrMoreGoalsOnHalfTime = countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(matches, sumTeamGoalsFunction, Constants.FOUR_GOALS);

        return new long[]{
                noGoalsOnHalfTime,
                oneGoalOnHalfTime,
                twoGoalsOnHalfTime,
                threeGoalsOnHalfTime,
                fourOrMoreGoalsOnHalfTime};

    }

    private long[] getCountSecondHalfTime(List<Match> matches, String team) {

        Function<Match, Integer> sumTeamGoalsFunction;

        switch (team) {
            case Constants.HOME_TEAM:
                sumTeamGoalsFunction = NumberOfGoalsCollecter::getHomeTeamGoalsScoredInSecondHalfTime;
                break;
            case Constants.AWAY_TEAM:
                sumTeamGoalsFunction = NumberOfGoalsCollecter::getAwayTeamGoalsScoredInSecondHalfTime;
                break;
            case Constants.BOTH_TEAMS:
                sumTeamGoalsFunction = NumberOfGoalsCollecter::sumGoalsInSecondHalfTime;
                break;
            default:
                sumTeamGoalsFunction = NumberOfGoalsCollecter::sumGoalsInSecondHalfTime;
                break;
        }

        long noGoalsScoredInSecondHalfTime = countMatchesWhereNumberOfGoalsIsZero(matches, sumTeamGoalsFunction);

        long oneGoalsScoredInSecondHalfTime = countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(matches, sumTeamGoalsFunction, Constants.ONE_GOAL);

        long twoGoalsScoredInSecondHalfTime = countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(matches, sumTeamGoalsFunction, Constants.TWO_GOALS);

        long threeGoalsScoredInSecondHalfTime = countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(matches, sumTeamGoalsFunction, Constants.THREE_GOALS);

        long fourOrMoreGoalsScoredInSecondHalfTime = countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(matches, sumTeamGoalsFunction, Constants.FOUR_GOALS);

        return new long[]{
                noGoalsScoredInSecondHalfTime,
                oneGoalsScoredInSecondHalfTime,
                twoGoalsScoredInSecondHalfTime,
                threeGoalsScoredInSecondHalfTime,
                fourOrMoreGoalsScoredInSecondHalfTime
        };
    }

    private long countMatchesWhereNumberOfGoalsIsEqualToOrAboveGoalLimit(List<Match> matches, Function<Match, Integer> sumTeamGoalsFunction, int goalLimit) {

        return matches.stream()
                .map(sumTeamGoalsFunction)
                .filter(numberOfGoals -> numberOfGoals >= goalLimit)
                .count();
    }

    private long countMatchesWhereNumberOfGoalsIsZero(List<Match> matches, Function<Match, Integer> sumTeamGoalsFunction) {

        return matches.stream()
                .map(sumTeamGoalsFunction)
                .filter(numberOfGoals -> numberOfGoals < Constants.ONE_GOAL)
                .count();
    }

    private Map<String, Long> convertValuesToMap(long[] goalsCount) {

        Map<String, Long> finalMap = new LinkedHashMap<>();

        finalMap.put("noGoals", goalsCount[0]);
        finalMap.put("oneGoal", goalsCount[1]);
        finalMap.put("twoGoals", goalsCount[2]);
        finalMap.put("threeGoals", goalsCount[3]);
        finalMap.put("fourOrMoreGoals", goalsCount[4]);

        return finalMap;
    }

}
