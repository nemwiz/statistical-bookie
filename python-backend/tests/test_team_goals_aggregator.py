import app.aggregators.team_goals as team_goals_aggregator
from app.helpers.aggregation import combine_aggregations
from app.models.match import Match
from collections import Counter
from tests.testing_helper import get_empty_match

match1 = Match(get_empty_match())
match2 = Match(get_empty_match())


def test_both_teams_score():
    match1.homeTeamGoals = 2
    match1.awayTeamGoals = 3
    match2.homeTeamGoals = 0
    match2.awayTeamGoals = 1

    final_aggregation = compute_both_teams_aggregation(match1, match2)

    assert final_aggregation['gg'] == 1


def test_one_team_did_not_score():
    match1.homeTeamGoals = 0
    match1.awayTeamGoals = 3
    match2.homeTeamGoals = 0
    match2.awayTeamGoals = 1

    final_aggregation = compute_both_teams_aggregation(match1, match2)

    assert final_aggregation['ng'] == 2


def test_both_teams_score_and_total_num_of_goals_is_more_than_three():
    match1.homeTeamGoals = 3
    match1.awayTeamGoals = 3
    match2.homeTeamGoals = 2
    match2.awayTeamGoals = 1

    final_aggregation = compute_both_teams_aggregation(match1, match2)

    assert final_aggregation['gg3+'] == 2
    assert final_aggregation['gg4+'] == 1


def test_both_teams_score_in_first_and_second_half():
    match1.homeTeam1stHalfGoals = 1
    match1.awayTeam1stHalfGoals = 2
    match2.homeTeam1stHalfGoals = 2
    match2.awayTeam1stHalfGoals = 3

    match1.homeTeam2ndHalfGoals = 1
    match1.awayTeam2ndHalfGoals = 1
    match2.homeTeam2ndHalfGoals = 2
    match2.awayTeam2ndHalfGoals = 0

    final_aggregation = compute_both_teams_aggregation(match1, match2)

    assert final_aggregation['1gg'] == 2
    assert final_aggregation['2gg'] == 1


def test_at_least_one_team_does_not_score_in_first_or_second_half():
    match1.homeTeamGoals = 2
    match1.awayTeamGoals = 2
    match2.homeTeamGoals = 1
    match2.awayTeamGoals = 2

    final_aggregation = compute_both_teams_aggregation(match1, match2)

    assert final_aggregation['t1&t2-2+'] == 1


def test_both_teams_score_at_least_two_goals():
    match1.homeTeam1stHalfGoals = 0
    match1.awayTeam1stHalfGoals = 2
    match2.homeTeam1stHalfGoals = 2
    match2.awayTeam1stHalfGoals = 0

    match1.homeTeam2ndHalfGoals = 1
    match1.awayTeam2ndHalfGoals = 1
    match2.homeTeam2ndHalfGoals = 2
    match2.awayTeam2ndHalfGoals = 0

    final_aggregation = compute_both_teams_aggregation(match1, match2)

    assert final_aggregation['1ng'] == 2
    assert final_aggregation['2ng'] == 1


def test_home_team_goal_aggregation():
    match1.homeTeam1stHalfGoals = 0
    match1.homeTeam2ndHalfGoals = 1
    match1.homeTeamGoals = 1

    match2.homeTeam1stHalfGoals = 2
    match2.homeTeam2ndHalfGoals = 0
    match2.homeTeamGoals = 2

    final_aggregation = compute_home_team_aggregation(match1, match2)

    assert final_aggregation['0'] == 0
    assert final_aggregation['1'] == 1
    assert final_aggregation['2'] == 1

    assert final_aggregation['1+'] == 2
    assert final_aggregation['2+'] == 1
    assert final_aggregation['3+'] == 0

    assert final_aggregation['0-1'] == 1
    assert final_aggregation['0-2'] == 2
    assert final_aggregation['1-2'] == 2
    assert final_aggregation['1-3'] == 2
    assert final_aggregation['2-3'] == 1

    assert final_aggregation['12gg'] == 0
    assert final_aggregation['12ng'] == 2


def test_home_team_goal_aggregation_in_both_halves():
    match1.homeTeam1stHalfGoals = 0
    match1.homeTeam2ndHalfGoals = 1

    match2.homeTeam1stHalfGoals = 2
    match2.homeTeam2ndHalfGoals = 0

    final_aggregation = compute_home_team_aggregation(match1, match2)

    assert final_aggregation['1stHalf-0'] == 1
    assert final_aggregation['1stHalf-1+'] == 1
    assert final_aggregation['1stHalf-2+'] == 1
    assert final_aggregation['1stHalf-3+'] == 0
    assert final_aggregation['1stHalf-0-1'] == 1
    assert final_aggregation['1stHalf-1-2'] == 1

    assert final_aggregation['2ndHalf-0'] == 1
    assert final_aggregation['2ndHalf-1+'] == 1
    assert final_aggregation['2ndHalf-2+'] == 0
    assert final_aggregation['2ndHalf-3+'] == 0
    assert final_aggregation['2ndHalf-0-1'] == 2
    assert final_aggregation['2ndHalf-1-2'] == 1


def test_away_team_goal_aggregation():
    match1.awayTeam1stHalfGoals = 0
    match1.awayTeam2ndHalfGoals = 1
    match1.awayTeamGoals = 1

    match2.awayTeam1stHalfGoals = 2
    match2.awayTeam2ndHalfGoals = 0
    match2.awayTeamGoals = 2

    final_aggregation = compute_away_team_aggregation(match1, match2)

    assert final_aggregation['0'] == 0
    assert final_aggregation['1'] == 1
    assert final_aggregation['2'] == 1

    assert final_aggregation['1+'] == 2
    assert final_aggregation['2+'] == 1
    assert final_aggregation['3+'] == 0

    assert final_aggregation['0-1'] == 1
    assert final_aggregation['0-2'] == 2
    assert final_aggregation['1-2'] == 2
    assert final_aggregation['1-3'] == 2
    assert final_aggregation['2-3'] == 1

    assert final_aggregation['12gg'] == 0
    assert final_aggregation['12ng'] == 2


def test_away_team_goal_aggregation_in_both_halves():
    match1.awayTeam1stHalfGoals = 1
    match1.awayTeam2ndHalfGoals = 1

    match2.awayTeam1stHalfGoals = 2
    match2.awayTeam2ndHalfGoals = 1

    final_aggregation = compute_away_team_aggregation(match1, match2)

    assert final_aggregation['1stHalf-0'] == 0
    assert final_aggregation['1stHalf-1+'] == 2
    assert final_aggregation['1stHalf-2+'] == 1
    assert final_aggregation['1stHalf-3+'] == 0
    assert final_aggregation['1stHalf-0-1'] == 1
    assert final_aggregation['1stHalf-1-2'] == 2

    assert final_aggregation['2ndHalf-0'] == 0
    assert final_aggregation['2ndHalf-1+'] == 2
    assert final_aggregation['2ndHalf-2+'] == 0
    assert final_aggregation['2ndHalf-3+'] == 0
    assert final_aggregation['2ndHalf-0-1'] == 2
    assert final_aggregation['2ndHalf-1-2'] == 2


def compute_both_teams_aggregation(m1, m2) -> Counter:
    aggregation1 = Counter(team_goals_aggregator.get_both_team_goals(m1))
    aggregation2 = Counter(team_goals_aggregator.get_both_team_goals(m2))

    return combine_aggregations([aggregation1, aggregation2])


def compute_home_team_aggregation(m1, m2) -> Counter:
    aggregation1 = Counter(team_goals_aggregator.get_team_goals_from_home_team(m1))
    aggregation2 = Counter(team_goals_aggregator.get_team_goals_from_home_team(m2))

    return combine_aggregations([aggregation1, aggregation2])


def compute_away_team_aggregation(m1, m2) -> Counter:
    aggregation1 = Counter(team_goals_aggregator.get_team_goals_from_away_team(m1))
    aggregation2 = Counter(team_goals_aggregator.get_team_goals_from_away_team(m2))

    return combine_aggregations([aggregation1, aggregation2])
