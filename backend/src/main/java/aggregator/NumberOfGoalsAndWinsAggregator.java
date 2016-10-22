package aggregator;

import collecter.NumberOfGoalsAndWinsCollecter;
import collecter.model.NumberOfGoalsAndWinsModel;
import model.Match;
import viewmodel.NumberOfGoalsAndWinsView;

import java.util.ArrayList;
import java.util.List;

public class NumberOfGoalsAndWinsAggregator extends Aggregator {

    private List<Match> matches;
    private List<NumberOfGoalsAndWinsModel> matchesWithNumberOfGoalsAndWins;
    
    public NumberOfGoalsAndWinsAggregator(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public NumberOfGoalsAndWinsView getAggregatedCount() {
        aggregateMatches();
        
        return new NumberOfGoalsAndWinsView(
                countMatchesWithHomeTeamWinAndOneGoal(),
                countMatchesWithHomeTeamWinAndTwoGoals(),
                countMatchesWithHomeTeamWinAndThreeGoals(),
                countMatchesWithHomeTeamWinAndFourOrMoreGoals(),
                countMatchesWithDrawAndOneGoal(),
                countMatchesWithDrawAndTwoGoals(),
                countMatchesWithDrawAndThreeGoals(),
                countMatchesWithDrawAndFourOrMoreGoals(),
                countMatchesWithAwayTeamWinAndOneGoal(),
                countMatchesWithAwayTeamWinAndTwoGoals(),
                countMatchesWithAwayTeamWinAndThreeGoals(),
                countMatchesWithAwayTeamWinAndFourOrMoreGoals()
        );
    }
    

    private void aggregateMatches() {
        matchesWithNumberOfGoalsAndWins = new ArrayList<>();

        matches.forEach(
                match -> matchesWithNumberOfGoalsAndWins.add(
                        NumberOfGoalsAndWinsCollecter.getNumberOfGoalsAndWins(match)
                )
        );
    }
    
    private long countMatchesWithHomeTeamWinAndOneGoal() {
        return matchesWithNumberOfGoalsAndWins
                .stream()
                .filter(NumberOfGoalsAndWinsModel::isHomeTeamWinAndOneGoalScored)
                .count();
        
    }

    private long countMatchesWithHomeTeamWinAndTwoGoals() {
        return matchesWithNumberOfGoalsAndWins
                .stream()
                .filter(NumberOfGoalsAndWinsModel::isHomeTeamWinAndTwoGoalsScored)
                .count();

    }
    
    private long countMatchesWithHomeTeamWinAndThreeGoals() {

        return matchesWithNumberOfGoalsAndWins
                .stream()
                .filter(NumberOfGoalsAndWinsModel::isHomeTeamWinAndThreeGoalsScored)
                .count();

    }
    
    private long countMatchesWithHomeTeamWinAndFourOrMoreGoals() {

        return matchesWithNumberOfGoalsAndWins
                .stream()
                .filter(NumberOfGoalsAndWinsModel::isHomeTeamWinAndFourOrMoreGoalsScored)
                .count();

    }

    private long countMatchesWithDrawAndOneGoal() {
        return matchesWithNumberOfGoalsAndWins
                .stream()
                .filter(NumberOfGoalsAndWinsModel::isDrawAndOneGoalScored)
                .count();

    }

    private long countMatchesWithDrawAndTwoGoals() {
        return matchesWithNumberOfGoalsAndWins
                .stream()
                .filter(NumberOfGoalsAndWinsModel::isDrawAndTwoGoalsScored)
                .count();

    }

    private long countMatchesWithDrawAndThreeGoals() {

        return matchesWithNumberOfGoalsAndWins
                .stream()
                .filter(NumberOfGoalsAndWinsModel::isDrawAndThreeGoalsScored)
                .count();

    }

    private long countMatchesWithDrawAndFourOrMoreGoals() {

        return matchesWithNumberOfGoalsAndWins
                .stream()
                .filter(NumberOfGoalsAndWinsModel::isDrawAndFourOrMoreGoalsScored)
                .count();

    }

    private long countMatchesWithAwayTeamWinAndOneGoal() {
        return matchesWithNumberOfGoalsAndWins
                .stream()
                .filter(NumberOfGoalsAndWinsModel::isAwayTeamWinAndOneGoalScored)
                .count();

    }

    private long countMatchesWithAwayTeamWinAndTwoGoals() {
        return matchesWithNumberOfGoalsAndWins
                .stream()
                .filter(NumberOfGoalsAndWinsModel::isAwayTeamWinAndTwoGoalsScored)
                .count();

    }

    private long countMatchesWithAwayTeamWinAndThreeGoals() {

        return matchesWithNumberOfGoalsAndWins
                .stream()
                .filter(NumberOfGoalsAndWinsModel::isAwayTeamWinAndThreeGoalsScored)
                .count();

    }

    private long countMatchesWithAwayTeamWinAndFourOrMoreGoals() {

        return matchesWithNumberOfGoalsAndWins
                .stream()
                .filter(NumberOfGoalsAndWinsModel::isAwayTeamWinAndFourOrMoreGoalsScored)
                .count();

    }
    
}
