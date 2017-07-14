import {Goals} from "./goals";
import {TeamGoals} from "./team-goals";
import {MatchOutcome} from "./match-outcome";
import {MatchDetailOutcome} from "./match-detail-outcome";
import {GoalsAndWins} from "./goals-and-wins";
import {HalftimeWithMoreGoals} from "./halftime-with-more-goals";

export interface MatchObject {
  numberOfGoalsMetaView: {
    homeTeam: Goals;
    awayTeam: Goals;
    bothTeams: Goals;
  };
  teamGoalsView: {
    homeTeam: TeamGoals;
    awayTeam: TeamGoals;
    bothTeams: TeamGoals;
  };
  matchOutcomeView: {
    homeTeam: MatchOutcome;
    awayTeam: MatchOutcome;
    draw: MatchOutcome;
  };
  matchDetailOutcomeView: MatchDetailOutcome;
  numberOfGoalsAndWinsView: {
    homeTeam: GoalsAndWins;
    awayTeam: GoalsAndWins;
    draw: GoalsAndWins;
  };
  halfTimeWithMoreGoalsView: HalftimeWithMoreGoals;
  exactResultView: object;
}
