def is_sum_in_range(goals_sum: int, range_start: int, range_end: int) -> int:
    return int(range_start <= goals_sum <= range_end)


def compare_sums(goals_sum: int, expected_sum: int, is_exact: bool = False) -> int:
    return int(goals_sum == expected_sum) if is_exact else int(goals_sum >= expected_sum)
