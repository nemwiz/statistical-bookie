from collections import Counter

import app.aggregators.goals as goals_aggregator
from app.helpers.aggregation import combine_aggregations
from app.models.match import Match
from tests.testing_helper import get_empty_match

match1 = Match(get_empty_match())
match2 = Match(get_empty_match())

"""Goals sum aggregation"""


def test_fulltime_aggregation():
    match1.homeTeamGoals = 3
    match1.awayTeamGoals = 1
    match2.homeTeamGoals = 4

    final_aggregation = compute_total_goals_fulltime_aggregation(match1, match2)

    assert final_aggregation['2+'] == 2
    assert final_aggregation['3+'] == 2


def test_fulltime_aggregation_exact():
    match1.homeTeamGoals = 3
    match1.awayTeamGoals = 1
    match2.homeTeamGoals = 3
    match2.awayTeamGoals = 0

    final_aggregation = compute_total_goals_fulltime_aggregation(match1, match2)

    assert final_aggregation['3'] == 1


def test_first_halftime_aggregation():
    match1.homeTeam1stHalfGoals = 3
    match1.awayTeam1stHalfGoals = 1
    match2.homeTeam1stHalfGoals = 4
    match2.awayTeam1stHalfGoals = 1

    final_aggregation = compute_total_goals_1sthalf_aggregation(match1, match2)

    assert final_aggregation['2+'] == 2


def test_first_halftime_aggregation_exact():
    match1.homeTeam1stHalfGoals = 1
    match1.awayTeam1stHalfGoals = 1
    match2.homeTeam1stHalfGoals = 4
    match2.awayTeam1stHalfGoals = 1

    final_aggregation = compute_total_goals_1sthalf_aggregation(match1, match2)

    assert final_aggregation['2'] == 1


def test_second_halftime_aggregation():
    match1.homeTeam2ndHalfGoals = 1
    match1.awayTeam2ndHalfGoals = 1
    match2.homeTeam2ndHalfGoals = 0
    match2.awayTeam2ndHalfGoals = 2

    final_aggregation = compute_total_goals_2ndhalf_aggregation(match1, match2)

    assert final_aggregation['2+'] == 2


def test_second_halftime_aggregation_exact():
    match1.homeTeam2ndHalfGoals = 1
    match1.awayTeam2ndHalfGoals = 1
    match2.homeTeam2ndHalfGoals = 0
    match2.awayTeam2ndHalfGoals = 0

    final_aggregation = compute_total_goals_2ndhalf_aggregation(match1, match2)

    assert final_aggregation['2'] == 1


"""Goals range aggregation"""


def test_fulltime_aggregation_range():
    match1.homeTeamGoals = 0
    match1.awayTeamGoals = 1
    match2.homeTeamGoals = 1
    match2.awayTeamGoals = 0

    final_aggregation = compute_total_goals_fulltime_aggregation(match1, match2)

    assert final_aggregation['0-1'] == 2
    assert final_aggregation['0-2'] == 2


def test_fulltime_aggregation_range_higher():
    match1.homeTeamGoals = 1
    match1.awayTeamGoals = 3
    match2.homeTeamGoals = 1
    match2.awayTeamGoals = 3

    final_aggregation = compute_total_goals_fulltime_aggregation(match1, match2)

    assert final_aggregation['1-4'] == 2
    assert final_aggregation['2-6'] == 2
    assert final_aggregation['3-4'] == 2
    assert final_aggregation['4-6'] == 2


def test_first_halftime_aggregation_range():
    match1.homeTeam1stHalfGoals = 0
    match1.awayTeam1stHalfGoals = 1
    match2.homeTeam1stHalfGoals = 1
    match2.awayTeam1stHalfGoals = 2

    final_aggregation = compute_total_goals_1sthalf_aggregation(match1, match2)

    assert final_aggregation['0-1'] == 1
    assert final_aggregation['1-2'] == 1
    assert final_aggregation['2-3'] == 1


def test_second_halftime_aggregation_range():
    match1.homeTeam2ndHalfGoals = 2
    match1.awayTeam2ndHalfGoals = 1
    match2.homeTeam2ndHalfGoals = 1
    match2.awayTeam2ndHalfGoals = 1

    final_aggregation = compute_total_goals_2ndhalf_aggregation(match1, match2)

    assert final_aggregation['0-1'] == 0
    assert final_aggregation['0-2'] == 1
    assert final_aggregation['1-2'] == 1
    assert final_aggregation['2-3'] == 2


"""helper function"""


def compute_total_goals_fulltime_aggregation(match1, match2) -> Counter:
    aggregation1 = Counter(goals_aggregator.get_fulltime_goals(match1))
    aggregation2 = Counter(goals_aggregator.get_fulltime_goals(match2))

    return combine_aggregations([aggregation1, aggregation2])


def compute_total_goals_1sthalf_aggregation(match1, match2) -> Counter:
    aggregation1 = Counter(goals_aggregator.get_1st_half_goals(match1))
    aggregation2 = Counter(goals_aggregator.get_1st_half_goals(match2))

    return combine_aggregations([aggregation1, aggregation2])


def compute_total_goals_2ndhalf_aggregation(match1, match2) -> Counter:
    aggregation1 = Counter(goals_aggregator.get_2nd_half_goals(match1))
    aggregation2 = Counter(goals_aggregator.get_2nd_half_goals(match2))

    return combine_aggregations([aggregation1, aggregation2])
