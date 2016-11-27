package viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AggregatedMatchesMetaView {

    @JsonProperty
    NumberOfGoalsMetaView numberOfGoalsMetaView;
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

    public AggregatedMatchesMetaView(NumberOfGoalsMetaView numberOfGoalsMetaView, TeamGoalsView teamGoalsView, MatchOutcomeView matchOutcomeView, MatchDetailOutcomeView matchDetailOutcomeView, NumberOfGoalsAndWinsView numberOfGoalsAndWinsView, HalfTimeWithMoreGoalsView halfTimeWithMoreGoalsView) {
        this.numberOfGoalsMetaView = numberOfGoalsMetaView;
        this.teamGoalsView = teamGoalsView;
        this.matchOutcomeView = matchOutcomeView;
        this.matchDetailOutcomeView = matchDetailOutcomeView;
        this.numberOfGoalsAndWinsView = numberOfGoalsAndWinsView;
        this.halfTimeWithMoreGoalsView = halfTimeWithMoreGoalsView;
    }
}
