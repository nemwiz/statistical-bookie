from flask import Blueprint, jsonify
from flask_headers import headers

leagues_api = Blueprint('leagues', __name__)

from controllers.leagues import get_all_leagues, get_league_table


@leagues_api.route("/api/leagues")
@headers({'Cache-Control':'public, max-age=5184000'})
def all_leagues() -> list:
    return jsonify(get_all_leagues())


@leagues_api.route("/api/leagues/<string:league_code>/table")
def league_table(league_code: str) -> list:
    return jsonify(get_league_table(league_code))
