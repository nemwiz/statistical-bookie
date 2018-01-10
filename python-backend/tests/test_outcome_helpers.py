from tests.testing_helper import get_empty_match
from app.models.match import Match
from app.constants.outcome import Outcome
import app.helpers.outcome as oh

match = Match(get_empty_match())

"""Tests if expected outcome is equal to actual outcome"""


def test_if_outcome_comparison_is_correct():
    match.finalOutcome = Outcome.HOME_WIN
    assert oh.compare(match.finalOutcome, Outcome.HOME_WIN) == 1


def test_if_outcome_comparison_is_not_correct():
    match.finalOutcome = Outcome.HOME_WIN
    assert oh.compare(match.finalOutcome, Outcome.AWAY_WIN) == 0


"""Test if double chance matches the expected outcomes"""


def test_if_double_chance_comparison_is_correct():
    match.finalOutcome = Outcome.HOME_WIN
    assert oh.double_chance(match.finalOutcome, Outcome.HOME_WIN, Outcome.DRAW) == 1


def test_if_double_chance_comparison_is_not_correct():
    match.finalOutcome = Outcome.AWAY_WIN
    assert oh.double_chance(match.finalOutcome, Outcome.HOME_WIN, Outcome.DRAW) == 0


"Test if halftime-fulltime outcomes match"


def test_if_halftime_fulltime_comparison_is_correct():
    match.outcome1stHalf = Outcome.DRAW
    match.finalOutcome = Outcome.HOME_WIN
    assert oh.halftime_fulltime_outcome(match.finalOutcome,
                                        match.outcome1stHalf,
                                        Outcome.HOME_WIN,
                                        Outcome.DRAW) == 1


def test_if_halftime_fulltime_comparison_is_not_correct():
    match.outcome1stHalf = Outcome.DRAW
    match.finalOutcome = Outcome.AWAY_WIN
    assert oh.halftime_fulltime_outcome(match.finalOutcome,
                                            match.outcome1stHalf,
                                            Outcome.DRAW,
                                            Outcome.HOME_WIN) == 0
