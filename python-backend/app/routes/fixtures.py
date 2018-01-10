from flask import Blueprint, jsonify
from flask_headers import headers

fixtures_api = Blueprint('fixtures', __name__)

from controllers.fixtures import get_upcoming_fixtures


@fixtures_api.route("/api/fixtures/<int:league_id>/upcoming")
@headers({'Cache-Control':'public, max-age=14400'})
def upcoming_fixtures(league_id: int) -> list:
    return jsonify(get_upcoming_fixtures(league_id))
