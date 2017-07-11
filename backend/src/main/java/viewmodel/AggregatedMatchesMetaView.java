package viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class AggregatedMatchesMetaView {

    @JsonProperty
    NumberOfGoalsModel numberOfGoalsMetaView;
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
    @JsonProperty
    Map<String, Long> exactResultView;

    public AggregatedMatchesMetaView(NumberOfGoalsModel numberOfGoalsMetaView, TeamGoalsView teamGoalsView, MatchOutcomeView matchOutcomeView, MatchDetailOutcomeView matchDetailOutcomeView, NumberOfGoalsAndWinsView numberOfGoalsAndWinsView, HalfTimeWithMoreGoalsView halfTimeWithMoreGoalsView, Map<String, Long> exactResultView) {
        this.numberOfGoalsMetaView = numberOfGoalsMetaView;
        this.teamGoalsView = teamGoalsView;
        this.matchOutcomeView = matchOutcomeView;
        this.matchDetailOutcomeView = matchDetailOutcomeView;
        this.numberOfGoalsAndWinsView = numberOfGoalsAndWinsView;
        this.halfTimeWithMoreGoalsView = halfTimeWithMoreGoalsView;
        this.exactResultView = exactResultView;
    }
}
