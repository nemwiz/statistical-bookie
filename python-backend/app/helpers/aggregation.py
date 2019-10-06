from functools import reduce
from collections import Counter


def combine_aggregations(aggregations_list: list) -> Counter:
    return reduce(lambda x, y: x + y, aggregations_list)
