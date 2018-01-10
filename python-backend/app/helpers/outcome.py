def compare(actual_outcome: str, expected_outcome: str) -> int:
    return int(actual_outcome == expected_outcome)


"""Dupla šansa"""


def double_chance(actual_outcome: str, first_outcome: str, second_outcome: str) -> int:
    return compare(actual_outcome, first_outcome) \
           or \
           compare(actual_outcome, second_outcome)


def halftime_fulltime_outcome(actual_halftime_outcome: str,
                              actual_fulltime_outcome: str,
                              halftime_outcome: str,
                              fulltime_outcome: str) -> int:
    return compare(actual_halftime_outcome, halftime_outcome) \
           and \
           compare(actual_fulltime_outcome, fulltime_outcome)
