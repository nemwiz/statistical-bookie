from constants.outcome import Outcome
from helpers.outcome import compare
from models.match import Match


def get_exact_result(match: Match) -> dict:
    return {'{}:{}'.format(match.homeTeamGoals, match.awayTeamGoals): 1}


def get_halftime_more_goals(match: Match) -> dict:
    aggregation = {}

    first_half_goals = match.homeTeam1stHalfGoals + match.awayTeam1stHalfGoals
    second_half_goals = match.homeTeam2ndHalfGoals + match.awayTeam2ndHalfGoals

    aggregation['1'] = first_half_goals > second_half_goals
    aggregation['X'] = first_half_goals < second_half_goals
    aggregation['2'] = first_half_goals == second_half_goals

    return aggregation


def aggregate_mixed(match: Match) -> dict:
    aggregation = {}

    aggregation['double-win-t1'] = compare(match.outcome1stHalf, Outcome.HOME_WIN) and compare(match.outcome2ndHalf,
                                                                                               Outcome.HOME_WIN)
    aggregation['double-win-t2'] = compare(match.outcome1stHalf, Outcome.AWAY_WIN) and compare(match.outcome2ndHalf,
                                                                                               Outcome.AWAY_WIN)

    aggregation['safe-victory-t1'] = compare(match.finalOutcome, Outcome.HOME_WIN) and match.awayTeamGoals == 0
    aggregation['safe-victory-t2'] = compare(match.finalOutcome, Outcome.AWAY_WIN) and match.homeTeamGoals == 0

    aggregation['handicap-win-t1'] = compare(match.finalOutcome,
                                             Outcome.HOME_WIN) and match.homeTeamGoals - match.awayTeamGoals > 1
    aggregation['handicap-win-t2'] = compare(match.finalOutcome,
                                             Outcome.AWAY_WIN) and match.awayTeamGoals - match.homeTeamGoals > 1

    return aggregation
