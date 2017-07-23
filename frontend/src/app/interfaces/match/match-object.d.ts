import {Goals} from "./goals";
import {TeamGoals} from "./team-goals";
import {MatchOutcome} from "./match-outcome";
import {MatchDetailOutcome} from "./match-detail-outcome";
import {GoalsAndWins} from "./goals-and-wins";
import {HalftimeWithMoreGoals} from "./halftime-with-more-goals";

export interface MatchObject {
  numberOfGoalsMetaView: NumberOfGoalsMetaView;
  teamGoalsView: TeamGoalsView;
  matchOutcomeView: MatchOutcomeView;
  matchDetailOutcomeView: MatchDetailOutcome;
  numberOfGoalsAndWinsView: NumberOfGoalsAndWinsView;
  halfTimeWithMoreGoalsView: HalftimeWithMoreGoals;
  exactResultView: object;
}

export interface NumberOfGoalsMetaView {
  homeTeam: Goals;
  awayTeam: Goals;
  bothTeams: Goals;
}

export interface TeamGoalsView {
  homeTeam: TeamGoals;
  awayTeam: TeamGoals;
  bothTeams: TeamGoals;
}

export interface MatchOutcomeView {
  homeTeam: MatchOutcome;
  awayTeam: MatchOutcome;
  draw: MatchOutcome;
}

export interface NumberOfGoalsAndWinsView {
  homeTeam: GoalsAndWins;
  awayTeam: GoalsAndWins;
  draw: GoalsAndWins;
}
