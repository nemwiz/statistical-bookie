package viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AggregatedMatchesMetaView {

    @JsonProperty
    NumberOfGoalsView numberOfGoalsView;
    @JsonProperty
    TeamGoalsView teamGoalsView;
    @JsonProperty
    MatchOutcomeView matchOutcomeView;
    @JsonProperty
    MatchDetailOutcomeView matchDetailOutcomeView;
    @JsonProperty
    NumberOfGoalsAndWinsView numberOfGoalsAndWinsView;
    @JsonProperty
    HalfTimeWithMoreGoalsView halfTimeWithMoreGoalsView;

    public AggregatedMatchesMetaView(NumberOfGoalsView numberOfGoalsView, TeamGoalsView teamGoalsView, MatchOutcomeView matchOutcomeView, MatchDetailOutcomeView matchDetailOutcomeView, NumberOfGoalsAndWinsView numberOfGoalsAndWinsView, HalfTimeWithMoreGoalsView halfTimeWithMoreGoalsView) {
        this.numberOfGoalsView = numberOfGoalsView;
        this.teamGoalsView = teamGoalsView;
        this.matchOutcomeView = matchOutcomeView;
        this.matchDetailOutcomeView = matchDetailOutcomeView;
        this.numberOfGoalsAndWinsView = numberOfGoalsAndWinsView;
        this.halfTimeWithMoreGoalsView = halfTimeWithMoreGoalsView;
    }
}
