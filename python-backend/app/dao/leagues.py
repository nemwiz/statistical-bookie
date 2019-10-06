from main import mongo
from pymongo import ASCENDING

COLLECTION_NAME = 'leagues'


def find_leagues() -> list:
    return list(mongo.db[COLLECTION_NAME].find({}).sort('_id', ASCENDING))
