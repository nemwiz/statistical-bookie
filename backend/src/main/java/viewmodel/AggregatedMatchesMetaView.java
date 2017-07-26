package viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class AggregatedMatchesMetaView {

    @JsonProperty
    NumberOfGoalsModel numberOfGoalsFullTime;
    @JsonProperty
    NumberOfGoalsModel numberOfGoalsFirstHalf;
    @JsonProperty
    NumberOfGoalsModel numberOfGoalsSecondHalf;
    @JsonProperty
    TeamGoalsModel teamGoalsView;
    @JsonProperty
    MatchOutcomeModel matchOutcomeView;
    @JsonProperty
    MatchDetailOutcomeView matchDetailOutcomeView;
    @JsonProperty
    NumberOfGoalsAndWinsModel numberOfGoalsAndWinsView;
    @JsonProperty
    HalfTimeWithMoreGoalsView halfTimeWithMoreGoalsView;
    @JsonProperty
    Map<String, Long> exactResultView;

    public AggregatedMatchesMetaView(NumberOfGoalsModel numberOfGoalsFullTime, NumberOfGoalsModel numberOfGoalsFirstHalf, NumberOfGoalsModel numberOfGoalsSecondHalf, TeamGoalsModel teamGoalsView, MatchOutcomeModel matchOutcomeView, MatchDetailOutcomeView matchDetailOutcomeView, NumberOfGoalsAndWinsModel numberOfGoalsAndWinsView, HalfTimeWithMoreGoalsView halfTimeWithMoreGoalsView, Map<String, Long> exactResultView) {
        this.numberOfGoalsFullTime = numberOfGoalsFullTime;
        this.numberOfGoalsFirstHalf = numberOfGoalsFirstHalf;
        this.numberOfGoalsSecondHalf = numberOfGoalsSecondHalf;
        this.teamGoalsView = teamGoalsView;
        this.matchOutcomeView = matchOutcomeView;
        this.matchDetailOutcomeView = matchDetailOutcomeView;
        this.numberOfGoalsAndWinsView = numberOfGoalsAndWinsView;
        this.halfTimeWithMoreGoalsView = halfTimeWithMoreGoalsView;
        this.exactResultView = exactResultView;
    }
}
