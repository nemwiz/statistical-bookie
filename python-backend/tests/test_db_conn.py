def test_db(mongodb):
    assert 'fixtures' in mongodb.collection_names()
    fixture = mongodb.fixtures.find_one({'leagueCode' : 'E0'})
    assert fixture['homeTeam'] == 'West Ham United'