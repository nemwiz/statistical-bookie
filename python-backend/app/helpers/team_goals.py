def have_both_teams_scored(home_team_goals: int, away_team_goals: int) -> int:
    return int(home_team_goals != 0 and away_team_goals != 0)
