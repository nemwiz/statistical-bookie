from constants.outcome import Outcome


class Match:

    def __init__(self, dto: dict):
        self._id = dto['_id']
        self.leagueCode = dto['leagueCode']
        self.leagueName = dto['leagueName']
        self.countryName = dto['countryName']
        self.currentRound = dto.get('currentRound', 0)
        self.date = dto.get('date', '00/00/00')
        self.seasonYear = dto.get('seasonYear', '00/00')
        self.homeTeam = dto['homeTeam']
        self.awayTeam = dto['awayTeam']
        self.homeTeamGoals = dto['homeTeamGoals']
        self.awayTeamGoals = dto['awayTeamGoals']
        self.homeTeam1stHalfGoals = dto['homeTeamHalftimeGoals']
        self.awayTeam1stHalfGoals = dto['awayTeamHalftimeGoals']
        self.homeTeam2ndHalfGoals = self.homeTeamGoals - self.homeTeam1stHalfGoals
        self.awayTeam2ndHalfGoals = self.awayTeamGoals - self.awayTeam1stHalfGoals
        self.finalOutcome = dto['finalOutcome']
        self.outcome1stHalf = dto['halfTimeOutcome']
        self.outcome2ndHalf = self.compute_2nd_half_outcome()

    def compute_2nd_half_outcome(self) -> str:
        return Outcome.DRAW if self.homeTeam2ndHalfGoals == self.awayTeam2ndHalfGoals \
            else Outcome.AWAY_WIN if self.awayTeam2ndHalfGoals > self.homeTeam2ndHalfGoals else Outcome.HOME_WIN
