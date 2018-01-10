from app.helpers.team_goals import have_both_teams_scored


def test_is_scored_in_both_halves_is_correct():
    first_half_goals = 3
    second_half_goals = 2
    assert have_both_teams_scored(first_half_goals, second_half_goals) == 1


def test_is_scored_in_both_halves_is_not_correct():
    first_half_goals = 3
    second_half_goals = 0
    assert have_both_teams_scored(first_half_goals, second_half_goals) == 0
