from constants.outcome import Outcome
from helpers.outcome import compare, double_chance, halftime_fulltime_outcome
from models.match import Match

HOME_WIN = 'H',
AWAY_WIN = 'A',
DRAW = 'D'


def get_fulltime_outcome(match: Match) -> dict:
    return {'1': compare(match.finalOutcome, Outcome.HOME_WIN),
            'X': compare(match.finalOutcome, Outcome.DRAW),
            '2': compare(match.finalOutcome, Outcome.AWAY_WIN)}


def get_double_chance_fulltime(match: Match) -> dict:
    return {'1X': double_chance(match.finalOutcome, Outcome.HOME_WIN, Outcome.DRAW),
            '12': double_chance(match.finalOutcome, Outcome.HOME_WIN, Outcome.AWAY_WIN),
            'X2': double_chance(match.finalOutcome, Outcome.DRAW, Outcome.AWAY_WIN)}


def get_1st_half_outcome(match: Match) -> dict:
    return {'1': compare(match.outcome1stHalf, Outcome.HOME_WIN),
            'X': compare(match.outcome1stHalf, Outcome.DRAW),
            '2': compare(match.outcome1stHalf, Outcome.AWAY_WIN)}


def get_2nd_half_outcome(match: Match) -> dict:
    return {'1': compare(match.outcome2ndHalf, Outcome.HOME_WIN),
            'X': compare(match.outcome2ndHalf, Outcome.DRAW),
            '2': compare(match.outcome2ndHalf, Outcome.AWAY_WIN)}


def get_1st_half_2nd_half(match: Match) -> dict:
    return {'1-1': halftime_fulltime(match, Outcome.HOME_WIN, Outcome.HOME_WIN),
            '1-X': halftime_fulltime(match, Outcome.HOME_WIN, Outcome.DRAW),
            '1-2': halftime_fulltime(match, Outcome.HOME_WIN, Outcome.AWAY_WIN),
            'X-1': halftime_fulltime(match, Outcome.DRAW, Outcome.HOME_WIN),
            'X-X': halftime_fulltime(match, Outcome.DRAW, Outcome.DRAW),
            'X-2': halftime_fulltime(match, Outcome.DRAW, Outcome.AWAY_WIN),
            '2-1': halftime_fulltime(match, Outcome.AWAY_WIN, Outcome.HOME_WIN),
            '2-X': halftime_fulltime(match, Outcome.AWAY_WIN, Outcome.DRAW),
            '2-2': halftime_fulltime(match, Outcome.AWAY_WIN, Outcome.AWAY_WIN)}


def get_double_chance_1st_half_2nd_half(match: Match) -> dict:
    return {'12-12': double_chance_aggregation(match, Outcome.HOME_WIN,
                                               Outcome.AWAY_WIN, Outcome.HOME_WIN,
                                               Outcome.AWAY_WIN),
            '12-1X': double_chance_aggregation(match, Outcome.HOME_WIN,
                                               Outcome.AWAY_WIN, Outcome.HOME_WIN,
                                               Outcome.DRAW),
            '12-X2': double_chance_aggregation(match, Outcome.HOME_WIN,
                                               Outcome.AWAY_WIN, Outcome.DRAW,
                                               Outcome.AWAY_WIN)}


def double_chance_aggregation(match,
                              halftime_first_outcome: str,
                              halftime_second_outcome: str,
                              fulltime_first_outcome: str,
                              fulltime_second_outcome: str) -> int:
    return double_chance(match.outcome1stHalf, halftime_first_outcome, halftime_second_outcome) \
           and \
           double_chance(match.finalOutcome, fulltime_first_outcome, fulltime_second_outcome)


def halftime_fulltime(match: Match,
                      expected_halftime_outcome: str,
                      expected_fulltime_outcome: str) -> bool:
    return halftime_fulltime_outcome(match.outcome1stHalf,
                                     match.finalOutcome,
                                     expected_halftime_outcome,
                                     expected_fulltime_outcome)
