from tests.testing_helper import get_empty_match
from app.models.match import Match
import app.helpers.goals as gh

actual_match = Match(get_empty_match())

"""Goals sum"""


def test_goal_sum_is_equal_or_greater_than_expected_sum():
    actual_match.homeTeamGoals = 2
    actual_match.awayTeamGoals = 1
    match_goals_sum = actual_match.homeTeamGoals + actual_match.awayTeamGoals
    assert gh.compare_sums(match_goals_sum, 3) == 1


def test_goal_sum_is_not_equal_or_greater_than_expected_sum():
    actual_match.homeTeamGoals = 0
    actual_match.awayTeamGoals = 1
    match_goals_sum = actual_match.homeTeamGoals + actual_match.awayTeamGoals
    assert gh.compare_sums(match_goals_sum, 2) == 0


"""Goals sum exact"""


def test_goal_sum_is_exact_the_expected_sum():
    actual_match.homeTeamGoals = 2
    actual_match.awayTeamGoals = 1
    match_goals_sum = actual_match.homeTeamGoals + actual_match.awayTeamGoals
    assert gh.compare_sums(match_goals_sum, 3, is_exact=True) == 1


def test_goal_sum_is_not_exact_the_expected_sum():
    actual_match.homeTeamGoals = 2
    actual_match.awayTeamGoals = 1
    match_goals_sum = actual_match.homeTeamGoals + actual_match.awayTeamGoals
    assert gh.compare_sums(match_goals_sum, 2, is_exact=True) == 0


"""Goals range"""


def test_if_goal_sum_is_in_range():
    actual_match.homeTeamGoals = 2
    actual_match.awayTeamGoals = 2
    match_goals_sum = actual_match.homeTeamGoals + actual_match.awayTeamGoals
    assert gh.is_sum_in_range(match_goals_sum, 2, 4) == 1


def test_if_goal_sum_is_not_in_range():
    actual_match.homeTeamGoals = 5
    actual_match.awayTeamGoals = 2
    match_goals_sum = actual_match.homeTeamGoals + actual_match.awayTeamGoals
    assert gh.is_sum_in_range(match_goals_sum, 2, 6) == 0
