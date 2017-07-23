import {MatchDetailOutcome} from "./match-detail-outcome";
import {MatchOutcomeView, NumberOfGoalsAndWinsView, NumberOfGoalsMetaView, TeamGoalsView} from "./match-object";
import {HalftimeWithMoreGoals} from "./halftime-with-more-goals";

export interface ResultsTableData {
  fiveMatches: Data;
  tenMatches: Data;
  isStructured?: boolean;
}

type Data = NumberOfGoalsMetaView |
  TeamGoalsView |
  MatchOutcomeView |
  MatchDetailOutcome |
  NumberOfGoalsAndWinsView |
  HalftimeWithMoreGoals |
  object;
