import app.aggregators.outcome as outcome_aggregator
from app.helpers.aggregation import combine_aggregations
from app.models.match import Match
from collections import Counter
from tests.testing_helper import get_empty_match

match1 = Match(get_empty_match())
match2 = Match(get_empty_match())
match3 = Match(get_empty_match())
match4 = Match(get_empty_match())


def test_fulltime_outcome():
    match1.finalOutcome = 'H'
    match2.finalOutcome = 'H'
    match3.finalOutcome = 'A'
    match4.finalOutcome = 'D'

    aggregation1 = Counter(outcome_aggregator.get_fulltime_outcome(match1))
    aggregation2 = Counter(outcome_aggregator.get_fulltime_outcome(match2))
    aggregation3 = Counter(outcome_aggregator.get_fulltime_outcome(match3))
    aggregation4 = Counter(outcome_aggregator.get_fulltime_outcome(match4))

    final_aggregation = combine_aggregations([aggregation1, aggregation2, aggregation3, aggregation4])

    assert final_aggregation['1'] == 2
    assert final_aggregation['X'] == 1
    assert final_aggregation['2'] == 1


def test_fulltime_double_chance():
    match1.finalOutcome = 'H'
    match2.finalOutcome = 'H'
    match3.finalOutcome = 'A'
    match4.finalOutcome = 'D'

    aggregation1 = Counter(outcome_aggregator.get_double_chance_fulltime(match1))
    aggregation2 = Counter(outcome_aggregator.get_double_chance_fulltime(match2))
    aggregation3 = Counter(outcome_aggregator.get_double_chance_fulltime(match3))
    aggregation4 = Counter(outcome_aggregator.get_double_chance_fulltime(match4))

    final_aggregation = combine_aggregations([aggregation1, aggregation2, aggregation3, aggregation4])

    assert final_aggregation['1X'] == 3
    assert final_aggregation['12'] == 3
    assert final_aggregation['X2'] == 2


def test_first_halftime_outcome():
    match1.outcome1stHalf = 'A'
    match2.outcome1stHalf = 'H'
    match3.outcome1stHalf = 'D'
    match4.outcome1stHalf = 'D'

    aggregation1 = Counter(outcome_aggregator.get_1st_half_outcome(match1))
    aggregation2 = Counter(outcome_aggregator.get_1st_half_outcome(match2))
    aggregation3 = Counter(outcome_aggregator.get_1st_half_outcome(match3))
    aggregation4 = Counter(outcome_aggregator.get_1st_half_outcome(match4))

    final_aggregation = combine_aggregations([aggregation1, aggregation2, aggregation3, aggregation4])

    assert final_aggregation['1'] == 1
    assert final_aggregation['X'] == 2
    assert final_aggregation['2'] == 1


def test_second_halftime_outcome():
    match1.outcome2ndHalf = 'H'
    match2.outcome2ndHalf = 'H'
    match3.outcome2ndHalf = 'A'
    match4.outcome2ndHalf = 'D'

    aggregation1 = Counter(outcome_aggregator.get_2nd_half_outcome(match1))
    aggregation2 = Counter(outcome_aggregator.get_2nd_half_outcome(match2))
    aggregation3 = Counter(outcome_aggregator.get_2nd_half_outcome(match3))
    aggregation4 = Counter(outcome_aggregator.get_2nd_half_outcome(match4))

    final_aggregation = combine_aggregations([aggregation1, aggregation2, aggregation3, aggregation4])

    assert final_aggregation['1'] == 2
    assert final_aggregation['X'] == 1
    assert final_aggregation['2'] == 1


def test_halftime_fulltime_outcome():
    match1.outcome1stHalf = 'A'
    match1.finalOutcome = 'A'

    match2.outcome1stHalf = 'D'
    match2.finalOutcome = 'H'

    match3.outcome1stHalf = 'H'
    match3.finalOutcome = 'D'

    match4.outcome1stHalf = 'D'
    match4.finalOutcome = 'A'

    aggregation1 = Counter(outcome_aggregator.get_1st_half_2nd_half(match1))
    aggregation2 = Counter(outcome_aggregator.get_1st_half_2nd_half(match2))
    aggregation3 = Counter(outcome_aggregator.get_1st_half_2nd_half(match3))
    aggregation4 = Counter(outcome_aggregator.get_1st_half_2nd_half(match4))

    final_aggregation = combine_aggregations([aggregation1, aggregation2, aggregation3, aggregation4])

    assert final_aggregation['1-1'] == 0
    assert final_aggregation['1-X'] == 1
    assert final_aggregation['1-2'] == 0

    assert final_aggregation['X-1'] == 1
    assert final_aggregation['X-X'] == 0
    assert final_aggregation['X-2'] == 1

    assert final_aggregation['2-1'] == 0
    assert final_aggregation['2-X'] == 0
    assert final_aggregation['2-2'] == 1


def test_halftime_fulltime_double_chance():
    match1.outcome1stHalf = 'A'
    match1.finalOutcome = 'A'

    match2.outcome1stHalf = 'D'
    match2.finalOutcome = 'H'

    match3.outcome1stHalf = 'H'
    match3.finalOutcome = 'D'

    match4.outcome1stHalf = 'D'
    match4.finalOutcome = 'A'

    aggregation1 = Counter(outcome_aggregator.get_double_chance_1st_half_2nd_half(match1))
    aggregation2 = Counter(outcome_aggregator.get_double_chance_1st_half_2nd_half(match2))
    aggregation3 = Counter(outcome_aggregator.get_double_chance_1st_half_2nd_half(match3))
    aggregation4 = Counter(outcome_aggregator.get_double_chance_1st_half_2nd_half(match4))

    final_aggregation = combine_aggregations([aggregation1, aggregation2, aggregation3, aggregation4])

    assert final_aggregation['12-12'] == 1
    assert final_aggregation['12-1X'] == 1
    assert final_aggregation['12-X2'] == 2
