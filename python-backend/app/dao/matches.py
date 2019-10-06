from main import mongo

COLLECTION_NAME = 'matches'


def find_all_matches_for_league(league_code: str, season_year: str) -> list:
    return list(mongo.db[COLLECTION_NAME].find({"leagueCode": league_code, "seasonYear": season_year}))


def find_all_previous_matches_from_teams(team1: str, team2: str) -> list:
    return list(mongo.db[COLLECTION_NAME].find({'$or': [{'$and': [{'homeTeam': team1, 'awayTeam': team2}]},
                                                        {'$and': [{'homeTeam': team2, 'awayTeam': team1}]}]}).limit(10))


def find_all_previous_matches_from_teams_in_this_setup(team1: str, team2: str) -> list:
    return list(mongo.db[COLLECTION_NAME].find({'$and': [{'homeTeam': team1, 'awayTeam': team2}]}).limit(10))

# def find_last_fixture(self, league_code: str):
#     return self.db[self._COLLECTION_NAME].find({"leagueCode": league_code}).sort("_id", pymongo.DESCENDING).limit(1)[0]
#
# def insert_fixtures(self, fixtures: list):
#     for fixture in fixtures:
#         filter = {key: value for key, value in fixture.__dict__.items() if key in self._KEYS_TO_FILTER}
#         self.db[self._COLLECTION_NAME].update_one(filter, {"$set": fixture.__dict__}, upsert=True)
