from collections import Counter

import app.aggregators.mixed as mixed_games_aggregator
from app.constants.outcome import Outcome
from app.helpers.aggregation import combine_aggregations
from app.models.match import Match
from tests.testing_helper import get_empty_match

match1 = Match(get_empty_match())
match2 = Match(get_empty_match())
match3 = Match(get_empty_match())
match4 = Match(get_empty_match())
match5 = Match(get_empty_match())
match6 = Match(get_empty_match())


def test_double_win():
    match1.outcome1stHalf = Outcome.HOME_WIN
    match1.outcome2ndHalf = Outcome.HOME_WIN

    match2.outcome1stHalf = Outcome.AWAY_WIN
    match2.outcome2ndHalf = Outcome.AWAY_WIN

    match3.outcome1stHalf = Outcome.AWAY_WIN
    match3.outcome2ndHalf = Outcome.AWAY_WIN

    final_aggregation = compute_aggregation(match1, match2, match3)

    assert final_aggregation['double-win-t1'] == 1
    assert final_aggregation['double-win-t2'] == 2


def test_safe_victory():
    match1.finalOutcome = Outcome.HOME_WIN
    match1.awayTeamGoals = 0

    match2.finalOutcome = Outcome.AWAY_WIN
    match2.homeTeamGoals = 0

    match3.finalOutcome = Outcome.HOME_WIN
    match3.awayTeamGoals = 0

    final_aggregation = compute_aggregation(match1, match2, match3)

    assert final_aggregation['safe-victory-t1'] == 2
    assert final_aggregation['safe-victory-t2'] == 1


def test_handicap_win():
    match1.finalOutcome = Outcome.HOME_WIN
    match1.homeTeamGoals = 2
    match1.awayTeamGoals = 0

    match2.finalOutcome = Outcome.AWAY_WIN
    match2.awayTeamGoals = 4
    match2.homeTeamGoals = 1

    match3.finalOutcome = Outcome.HOME_WIN
    match3.homeTeamGoals = 3
    match3.awayTeamGoals = 1

    final_aggregation = compute_aggregation(match1, match2, match3)

    assert final_aggregation['handicap-win-t1'] == 2
    assert final_aggregation['handicap-win-t2'] == 1


def test_halftime_with_more_goals():
    match1.homeTeam1stHalfGoals = 0
    match1.awayTeam1stHalfGoals = 2

    match1.homeTeam2ndHalfGoals = 0
    match1.awayTeam2ndHalfGoals = 0

    match2.homeTeam1stHalfGoals = 2
    match2.awayTeam1stHalfGoals = 0

    match2.homeTeam2ndHalfGoals = 2
    match2.awayTeam2ndHalfGoals = 0

    match3.homeTeam1stHalfGoals = 2
    match3.awayTeam1stHalfGoals = 0

    match3.homeTeam2ndHalfGoals = 2
    match3.awayTeam2ndHalfGoals = 1

    final_aggregation = compute_halftime_more_goals(match1, match2, match3)

    assert final_aggregation['1'] == 1
    assert final_aggregation['X'] == 1
    assert final_aggregation['2'] == 1


def test_exact_result():
    match1.homeTeamGoals = 1
    match1.awayTeamGoals = 2

    match2.homeTeamGoals = 0
    match2.awayTeamGoals = 2

    match3.homeTeamGoals = 2
    match3.awayTeamGoals = 3

    match4.homeTeamGoals = 1
    match4.awayTeamGoals = 2

    match5.homeTeamGoals = 0
    match5.awayTeamGoals = 0

    match6.homeTeamGoals = 1
    match6.awayTeamGoals = 1

    final_aggregation = compute_exact_result(match1, match2, match3)
    final_aggregation.update(compute_exact_result(match4, match5, match6))

    assert final_aggregation['1:2'] == 2
    assert final_aggregation['0:2'] == 1
    assert final_aggregation['2:3'] == 1
    assert final_aggregation['0:0'] == 1
    assert final_aggregation['1:1'] == 1
    assert final_aggregation['4:1'] == 0


def compute_aggregation(m1, m2, m3) -> Counter:
    aggregation1 = Counter(mixed_games_aggregator.aggregate_mixed(m1))
    aggregation2 = Counter(mixed_games_aggregator.aggregate_mixed(m2))
    aggregation3 = Counter(mixed_games_aggregator.aggregate_mixed(m3))

    return combine_aggregations([aggregation1, aggregation2, aggregation3])


def compute_exact_result(m1, m2, m3) -> Counter:
    aggregation1 = Counter(mixed_games_aggregator.get_exact_result(m1))
    aggregation2 = Counter(mixed_games_aggregator.get_exact_result(m2))
    aggregation3 = Counter(mixed_games_aggregator.get_exact_result(m3))

    return combine_aggregations([aggregation1, aggregation2, aggregation3])


def compute_halftime_more_goals(m1, m2, m3) -> Counter:
    aggregation1 = Counter(mixed_games_aggregator.get_halftime_more_goals(m1))
    aggregation2 = Counter(mixed_games_aggregator.get_halftime_more_goals(m2))
    aggregation3 = Counter(mixed_games_aggregator.get_halftime_more_goals(m3))

    return combine_aggregations([aggregation1, aggregation2, aggregation3])