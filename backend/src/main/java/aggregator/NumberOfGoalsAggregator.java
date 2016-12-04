package aggregator;

import collecter.NumberOfGoalsCollecter;
import helper.Constants;
import model.Match;
import viewmodel.NumberOfGoalsMetaView;
import viewmodel.NumberOfGoalsView;

import java.util.List;
import java.util.function.Function;

public class NumberOfGoalsAggregator extends Aggregator {

    private List<Match> matches;

    public NumberOfGoalsAggregator(List<Match> matches) {
        this.matches = matches;
    }

    public NumberOfGoalsMetaView getAggregatedCount() {

        long[] countOfFullTimeGoals = getCountFullTime(this.matches, Constants.BOTH_TEAMS);
        long[] countOfHalfTimeGoals = getCountHalfTime(this.matches, Constants.BOTH_TEAMS);
        long[] countOfSecondHalfTimeGoals = getCountSecondHalfTime(this.matches, Constants.BOTH_TEAMS);

        NumberOfGoalsView bothTeamsNumberOfGoals = mapArraysToViewModel(
                countOfFullTimeGoals,
                countOfHalfTimeGoals,
                countOfSecondHalfTimeGoals);

        long[] countOfFullTimeGoalsHomeTeam = getCountFullTime(this.matches, Constants.HOME_TEAM);
        long[] countOfHalfTimeGoalsHomeTeam = getCountHalfTime(this.matches, Constants.HOME_TEAM);
        long[] countOfSecondHalfTimeGoalsHomeTeam = getCountSecondHalfTime(this.matches, Constants.HOME_TEAM);

        NumberOfGoalsView homeTeamNumberOfGoals = mapArraysToViewModel(
                countOfFullTimeGoalsHomeTeam,
                countOfHalfTimeGoalsHomeTeam,
                countOfSecondHalfTimeGoalsHomeTeam);

        long[] countOfFullTimeGoalsAwayTeam = getCountFullTime(this.matches, Constants.AWAY_TEAM);
        long[] countOfHalfTimeGoalsAwayTeam = getCountHalfTime(this.matches, Constants.AWAY_TEAM);
        long[] countOfSecondHalfTimeGoalsAwayTeam = getCountSecondHalfTime(this.matches, Constants.AWAY_TEAM);

        NumberOfGoalsView awayTeamNumberOfGoals = mapArraysToViewModel(
                countOfFullTimeGoalsAwayTeam,
                countOfHalfTimeGoalsAwayTeam,
                countOfSecondHalfTimeGoalsAwayTeam);

        return new NumberOfGoalsMetaView(
                bothTeamsNumberOfGoals,
                homeTeamNumberOfGoals,
                awayTeamNumberOfGoals
        );

    }

    private NumberOfGoalsView mapArraysToViewModel(long[] countOfFullTimeGoals,
                                                   long[] countOfHalfTimeGoals,
                                                   long[] countOfSecondHalfTimeGoals) {
        return new NumberOfGoalsView(
                countOfFullTimeGoals[0],
                countOfFullTimeGoals[1],
                countOfFullTimeGoals[2],
                countOfFullTimeGoals[3],
                countOfFullTimeGoals[4],
                countOfHalfTimeGoals[0],
                countOfHalfTimeGoals[1],
                countOfHalfTimeGoals[2],
                countOfHalfTimeGoals[3],
                countOfHalfTimeGoals[4],
                countOfSecondHalfTimeGoals[0],
                countOfSecondHalfTimeGoals[1],
                countOfSecondHalfTimeGoals[2],
                countOfSecondHalfTimeGoals[3],
                countOfSecondHalfTimeGoals[4]
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


}
