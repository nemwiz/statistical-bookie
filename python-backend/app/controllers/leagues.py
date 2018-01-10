from dao.leagues import find_leagues


def get_all_leagues():
    return find_leagues()


def get_league_table(league_code: str) -> list:
    ## TODO - scrape table from livescore and add to db
    return []
