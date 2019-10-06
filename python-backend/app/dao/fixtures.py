from main import mongo

COLLECTION_NAME = 'fixtures'


def find_upcoming_fixtures(league_id: int, days_of_current_week: list) -> list:
    return list(mongo.db[COLLECTION_NAME]
                .find({"leagueId": league_id, "date": {"$in": days_of_current_week}}))
