from aggregators.goals import sum_aggregation, range_aggregation
from helpers.goals import compare_sums, is_sum_in_range
from helpers.team_goals import have_both_teams_scored
from models.match import Match


def get_both_team_goals(match: Match) -> dict:
    aggregation = {}

    # both teams
    aggregation['gg'] = have_both_teams_scored(match.homeTeamGoals, match.awayTeamGoals)
    aggregation['ng'] = not have_both_teams_scored(match.homeTeamGoals, match.awayTeamGoals)

    goals_sum_fulltime = match.homeTeamGoals + match.awayTeamGoals

    aggregation['gg3+'] = have_both_teams_scored(match.homeTeamGoals, match.awayTeamGoals) and compare_sums(
        goals_sum_fulltime, 3)
    aggregation['gg4+'] = have_both_teams_scored(match.homeTeamGoals, match.awayTeamGoals) and compare_sums(
        goals_sum_fulltime, 4)

    aggregation['1gg'] = have_both_teams_scored(match.homeTeam1stHalfGoals, match.awayTeam1stHalfGoals)
    aggregation['2gg'] = have_both_teams_scored(match.homeTeam2ndHalfGoals, match.awayTeam2ndHalfGoals)

    aggregation['1ng'] = not have_both_teams_scored(match.homeTeam1stHalfGoals, match.awayTeam1stHalfGoals)
    aggregation['2ng'] = not have_both_teams_scored(match.homeTeam2ndHalfGoals, match.awayTeam2ndHalfGoals)

    aggregation['t1&t2-2+'] = compare_sums(match.homeTeamGoals, 2) and compare_sums(match.awayTeamGoals, 2)

    return aggregation


def get_team_goals_from_home_team(match: Match) -> dict:
    aggregation = {}

    aggregation.update(sum_aggregation(match.homeTeamGoals, '{}', 0, 3, is_exact=True))
    aggregation.update(sum_aggregation(match.homeTeamGoals, '{}+', 1, 4))

    aggregation.update(range_aggregation(match.homeTeamGoals, '0-{}', 0, 3))
    aggregation.update(range_aggregation(match.homeTeamGoals, '1-{}', 1, 4))
    aggregation.update(range_aggregation(match.homeTeamGoals, '2-{}', 2, 4))

    aggregation['12gg'] = have_both_teams_scored(match.homeTeam1stHalfGoals, match.homeTeam2ndHalfGoals)
    aggregation['12ng'] = not have_both_teams_scored(match.homeTeam1stHalfGoals, match.homeTeam2ndHalfGoals)

    # home team goals on halftimes

    aggregation.update(aggregate_halftime_for_team(match.homeTeam1stHalfGoals, match.homeTeam2ndHalfGoals))

    return aggregation


def get_team_goals_from_away_team(match: Match) -> dict:
    aggregation = {}

    aggregation.update(sum_aggregation(match.awayTeamGoals, '{}', 0, 3, is_exact=True))
    aggregation.update(sum_aggregation(match.awayTeamGoals, '{}+', 1, 4))

    aggregation.update(range_aggregation(match.awayTeamGoals, '0-{}', 0, 3))
    aggregation.update(range_aggregation(match.awayTeamGoals, '1-{}', 1, 4))
    aggregation.update(range_aggregation(match.awayTeamGoals, '2-{}', 2, 4))

    aggregation['12gg'] = have_both_teams_scored(match.awayTeam1stHalfGoals, match.awayTeam2ndHalfGoals)
    aggregation['12ng'] = not have_both_teams_scored(match.awayTeam1stHalfGoals, match.awayTeam2ndHalfGoals)

    # away team goals on halftimes

    aggregation.update(aggregate_halftime_for_team(match.awayTeam1stHalfGoals, match.awayTeam2ndHalfGoals))

    return aggregation


def aggregate_halftime_for_team(first_half_goals: int, second_half_goals: int) -> dict:
    aggregation = {}

    aggregation['1stHalf-0'] = compare_sums(first_half_goals, 0, is_exact=True)
    aggregation.update(sum_aggregation(first_half_goals, '1stHalf-{}+', 1, 4))
    aggregation['1stHalf-0-1'] = is_sum_in_range(first_half_goals, 0, 1)
    aggregation['1stHalf-1-2'] = is_sum_in_range(first_half_goals, 1, 2)

    aggregation['2ndHalf-0'] = compare_sums(second_half_goals, 0, is_exact=True)
    aggregation.update(sum_aggregation(second_half_goals, '2ndHalf-{}+', 1, 4))
    aggregation['2ndHalf-0-1'] = is_sum_in_range(second_half_goals, 0, 1)
    aggregation['2ndHalf-1-2'] = is_sum_in_range(second_half_goals, 1, 2)

    return aggregation
