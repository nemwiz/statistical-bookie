from datetime import date
# below import is needed although not used and the ordering is important
from app.main import mongo
from app.controllers.fixtures import dates_till_end_of_the_week


def test_number_of_dates_should_be_one_if_today_is_sunday():
    sunday = date(2017, 12, 17)
    list_of_dates = dates_till_end_of_the_week(sunday)
    assert len(list_of_dates) == 1
    assert list_of_dates[0] == '17/12/17'


def test_dates_should_be_all_dates_between_given_date_and_sunday():
    thursday = date(2017, 12, 14)
    list_of_dates = dates_till_end_of_the_week(thursday)
    assert len(list_of_dates) == 4
