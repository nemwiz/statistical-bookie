from dao.fixtures import find_upcoming_fixtures
from datetime import date, timedelta


def get_upcoming_fixtures(league_id: int) -> list:
    return find_upcoming_fixtures(league_id, dates_till_end_of_the_week(date.today()))


def dates_till_end_of_the_week(todays_date: date) -> list:
    if todays_date.weekday() == 6:
        return [parse_to_db_date_format(todays_date)]
    else:
        return [parse_to_db_date_format(todays_date + timedelta(days=day))
                for day in range(0, 7 - todays_date.weekday())]


def parse_to_db_date_format(date: date) -> str:
    return '{}/{}/{}'.format(date.strftime('%d'), date.strftime('%m'), str(date.year)[2:4:])
