import {Component, OnInit} from '@angular/core';
import {Dictionary} from "../../interfaces/dictionary";
import {CommonAggregation, TeamAggregatedData} from "../../interfaces/match/match-object";
import {MatchService} from "../../services/match.service";
import {Data} from "@angular/router";
import {ResultsTableData} from "../../interfaces/match/data-stats";

@Component({
  selector: 'aggregation-section',
  templateUrl: './aggregation-section.component.html',
  styleUrls: ['./aggregation-section.component.scss'],
  inputs: ['aggregationFiveMatches', 'aggregationTenMatches', 'fixtureId']
})
export class AggregationSectionComponent implements OnInit {

  aggregationFiveMatches: Dictionary<TeamAggregatedData | CommonAggregation> = {};
  aggregationTenMatches: Dictionary<TeamAggregatedData | CommonAggregation> = {};
  fixtureId: number;

  constructor(private matchService: MatchService) {
  }

  ngOnInit() {
  }

  pushMatchData(fiveMatches: Data, tenMatches: Data) {

    let results: ResultsTableData = {
      fiveMatches: fiveMatches,
      tenMatches: tenMatches,
    };

    this.matchService.pushMatchData(results);
  }

}
