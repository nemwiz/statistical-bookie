from flask import Blueprint, request, jsonify
from flask_headers import headers

matches_api = Blueprint('matches', __name__)

from controllers.matches import get_match_aggregation


@matches_api.route("/api/matches/aggregate/")
@headers({'Cache-Control':'public, max-age=259200'})
def aggregate_match() -> list:
    home_team = request.args.get('homeTeam')
    away_team = request.args.get('awayTeam')

    aggregation = get_match_aggregation(home_team, away_team)

    return jsonify(aggregation)
