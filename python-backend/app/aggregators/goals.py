import helpers.goals as gh
from models.match import Match


def get_fulltime_goals(match: Match) -> dict:
    aggregation = {}

    fulltime_goals_sum = match.homeTeamGoals + match.awayTeamGoals

    # fulltime
    aggregation.update(sum_aggregation(fulltime_goals_sum, '{}+', 2, 8))
    aggregation.update(sum_aggregation(fulltime_goals_sum, '{}', 1, 6, is_exact=True))

    aggregation.update(range_aggregation(fulltime_goals_sum, '0-{}', 0, 5))
    aggregation.update(range_aggregation(fulltime_goals_sum, '1-{}', 1, 6))
    aggregation.update(range_aggregation(fulltime_goals_sum, '2-{}', 2, 7))
    aggregation.update(range_aggregation(fulltime_goals_sum, '3-{}', 3, 7))
    aggregation.update(range_aggregation(fulltime_goals_sum, '4-{}', 4, 7))

    return aggregation


def get_1st_half_goals(match: Match) -> dict:
    aggregation = {}

    first_half_goals_sum = match.homeTeam1stHalfGoals + match.awayTeam1stHalfGoals

    aggregation.update(sum_aggregation(first_half_goals_sum, '{}+', 1, 4))
    aggregation.update(sum_aggregation(first_half_goals_sum, '{}', 0, 3, is_exact=True))

    aggregation.update(range_aggregation(first_half_goals_sum, '0-{}', 0, 3))
    aggregation.update(range_aggregation(first_half_goals_sum, '1-{}', 1, 3))
    aggregation.update(range_aggregation(first_half_goals_sum, '2-{}', 2, 4))

    return aggregation


def get_2nd_half_goals(match: Match) -> dict:
    aggregation = {}

    second_half_goals_sum = match.homeTeam2ndHalfGoals + match.awayTeam2ndHalfGoals

    aggregation.update(sum_aggregation(second_half_goals_sum, '{}+', 1, 4))
    aggregation.update(sum_aggregation(second_half_goals_sum, '{}', 0, 3, is_exact=True))

    aggregation.update(range_aggregation(second_half_goals_sum, '0-{}', 0, 3))
    aggregation.update(range_aggregation(second_half_goals_sum, '1-{}', 1, 3))
    aggregation.update(range_aggregation(second_half_goals_sum, '2-{}', 2, 4))

    return aggregation


def sum_aggregation(actual_sum: int,
                    key_representation: str,
                    range_start: int,
                    range_end: int,
                    is_exact: bool = False) -> dict:
    return {key_representation.format(i): gh.compare_sums(actual_sum, i, is_exact)
            for i in range(range_start, range_end)}


def range_aggregation(actual_sum: int,
                      key_representation: str,
                      range_start: int,
                      range_end: int) -> dict:
    return {key_representation.format(i): gh.is_sum_in_range(actual_sum, range_start, i)
            for i in range(range_start + 1, range_end)}
