import {Goals} from "./goals";
import {TeamGoals} from "./team-goals";
import {MatchOutcome} from "./match-outcome";
import {HalfTimeFullTime} from "./match-detail-outcome";
import {GoalsAndWins} from "./goals-and-wins";
import {HalftimeWithMoreGoals} from "./halftime-with-more-goals";
import {Dictionary} from "../dictionary";

export interface MatchObject {
  homeTeam: Dictionary<TeamAggregatedData>;
  awayTeam: Dictionary<TeamAggregatedData>;
  bothTeams: Dictionary<TeamAggregatedData>;
  common: Dictionary<CommonAggregation>;
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

export interface TeamAggregatedData {
  numberOfGoalsFulltime: Goals;
  numberOfGoalsFirstHalf: Goals;
  numberOfGoalsSecondHalf: Goals;
  teamScored: TeamGoals;
  halfTimesAndMatchOutcome?: MatchOutcome;
  numberOfGoalsAndWin?: GoalsAndWins;
}

export interface CommonAggregation {
  drawOutcome: MatchOutcome;
  drawOutcomeAndGoalsScored: GoalsAndWins;
  halfTimeFullTime: HalfTimeFullTime;
  halfTimeWithMoreGoals: HalftimeWithMoreGoals;
  exactResults: Dictionary<number>;
}
