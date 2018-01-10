from flask import Flask
from flask_pymongo import PyMongo
from flask_cors import CORS
from flask_compress import Compress
from helpers.json_encoder import JSONEncoder
import os

app = Flask(__name__)
app.json_encoder = JSONEncoder
app.config["JSON_SORT_KEYS"] = False
CORS(app)
Compress(app)

app.config['MONGO_HOST'] = os.getenv('MONGO_HOST', 'localhost')
app.config['MONGO_PORT'] = os.getenv('MONGO_PORT', 28008)
app.config['MONGO_DBNAME'] = os.getenv('MONGO_DB_NAME', 'stats')
app.config['MONGO_USERNAME'] = os.getenv('MONGO_USER', None)
app.config['MONGO_PASSWORD'] = os.getenv('MONGO_PASSWORD', None)

mongo = PyMongo(app)

from routes.fixtures import fixtures_api
from routes.leagues import leagues_api
from routes.matches import matches_api

app.register_blueprint(fixtures_api)
app.register_blueprint(leagues_api)
app.register_blueprint(matches_api)

if __name__ == "__main__":
    if os.getenv('MONGO_HOST') is None:
        app.run(host='0.0.0.0', debug=True, port=5005)
    else:
        app.run(host='0.0.0.0')
