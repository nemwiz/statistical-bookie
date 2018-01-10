from collections import Counter, OrderedDict

import aggregators.goals as goals_aggregator
import aggregators.mixed as mixed_games_aggregator
import aggregators.outcome as outcome_aggregator
import aggregators.team_goals as team_goals_aggregator
from dao.matches import find_all_previous_matches_from_teams_in_this_setup, find_all_previous_matches_from_teams
from helpers.aggregation import combine_aggregations
from models.match import Match


def get_match_aggregation(home_team: str, away_team: str) -> dict:
    matches = find_all_previous_matches_from_teams_in_this_setup(home_team, away_team)

    match_count = len(matches)
    if match_count < 5:
        matches = find_all_previous_matches_from_teams(home_team, away_team)
        match_count = len(matches)
        if match_count < 5:
            return {}

    fulltime_outcomes = []
    double_chance_outcome = []
    outcomes_1st_half = []
    outcomes_2nd_half = []
    from1st_half_to_2nd_half = []
    double_chance_both_halves = []

    goals_fulltime = []
    goals_1st_half = []
    goals_2nd_half = []

    team_goals_both_teams = []
    team_goals_home_team = []
    team_goals_away_team = []

    mixed = []
    exact_result = []
    halftime_more_goals = []

    for match in matches:
        m = Match(match)

        # match outcomes
        fulltime_outcomes.append(Counter(outcome_aggregator.get_fulltime_outcome(m)))
        double_chance_outcome.append(Counter(outcome_aggregator.get_double_chance_fulltime(m)))
        outcomes_1st_half.append(Counter(outcome_aggregator.get_1st_half_outcome(m)))
        outcomes_2nd_half.append(Counter(outcome_aggregator.get_2nd_half_outcome(m)))
        from1st_half_to_2nd_half.append(Counter(outcome_aggregator.get_1st_half_2nd_half(m)))
        double_chance_both_halves.append(Counter(outcome_aggregator.get_double_chance_1st_half_2nd_half(m)))

        # total goals
        goals_fulltime.append(Counter(goals_aggregator.get_fulltime_goals(m)))
        goals_1st_half.append(Counter(goals_aggregator.get_1st_half_goals(m)))
        goals_2nd_half.append(Counter(goals_aggregator.get_2nd_half_goals(m)))

        # team goals
        team_goals_both_teams.append(Counter(team_goals_aggregator.get_both_team_goals(m)))
        team_goals_home_team.append(Counter(team_goals_aggregator.get_team_goals_from_home_team(m)))
        team_goals_away_team.append(Counter(team_goals_aggregator.get_team_goals_from_away_team(m)))

        # mixed games
        mixed.append(Counter(mixed_games_aggregator.aggregate_mixed(m)))
        exact_result.append(Counter(mixed_games_aggregator.get_exact_result(m)))
        halftime_more_goals.append(Counter(mixed_games_aggregator.get_halftime_more_goals(m)))

    # match outcomes
    fulltime_outcomes_final = {'matchOutcomeFullTime': dict(combine_aggregations(fulltime_outcomes))}
    double_chance_final = {'doubleChanceFullTime': dict(combine_aggregations(double_chance_outcome))}
    outcomes_1st_half_final = {'1stHalfOutcomes': dict(combine_aggregations(outcomes_1st_half))}
    outcomes_2nd_half_final = {'2ndHalfOutcomes': dict(combine_aggregations(outcomes_2nd_half))}
    from1st_half_to_2nd_half_final = {'1stHalfTo2ndHalf': dict(combine_aggregations(from1st_half_to_2nd_half))}
    double_chance_both_halves_final = {'doubleChanceBothHalves': dict(combine_aggregations(double_chance_both_halves))}

    # total goals
    goals_fulltime_final = {'totalGoalsFulltime': dict(combine_aggregations(goals_fulltime))}
    goals_1st_half_final = {'totalGoals1stHalf': dict(combine_aggregations(goals_1st_half))}
    goals_2nd_half_final = {'totalGoals2ndHalf': dict(combine_aggregations(goals_2nd_half))}

    # team goals
    team_goals_both_teams_final = {'teamGoalsBothTeams': dict(combine_aggregations(team_goals_both_teams))}
    team_goals_home_team_final = {'teamGoalsHomeTeam': dict(combine_aggregations(team_goals_home_team))}
    team_goals_away_team_final = {'teamGoalsAwayTeam': dict(combine_aggregations(team_goals_away_team))}

    # mixed games
    mixed_final = {'mixedGames': dict(combine_aggregations(mixed))}
    exact_result_final = {'exactResult': dict(combine_aggregations(exact_result))}
    halftime_more_goals_final = {'halfTimeMoreGoals': dict(combine_aggregations(halftime_more_goals))}

    final_aggregation = OrderedDict()
    final_aggregation.update({'matchCount': match_count})

    # match outcomes
    final_aggregation.update(fulltime_outcomes_final)
    final_aggregation.update(outcomes_1st_half_final)
    final_aggregation.update(outcomes_2nd_half_final)
    final_aggregation.update(double_chance_final)
    final_aggregation.update(double_chance_both_halves_final)
    final_aggregation.update(from1st_half_to_2nd_half_final)

    # total goals
    final_aggregation.update(goals_fulltime_final)
    final_aggregation.update(goals_1st_half_final)
    final_aggregation.update(goals_2nd_half_final)

    # team goals
    final_aggregation.update(team_goals_both_teams_final)
    final_aggregation.update(team_goals_home_team_final)
    final_aggregation.update(team_goals_away_team_final)

    # mixed games
    final_aggregation.update(exact_result_final)
    final_aggregation.update(halftime_more_goals_final)
    final_aggregation.update(mixed_final)

    print(final_aggregation)

    return final_aggregation
