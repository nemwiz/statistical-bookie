import {Component, OnInit} from "@angular/core";
import {MatchObject} from "../../interfaces/match/match-object";

declare var jQuery: any;

@Component({
  selector: 'results-table',
  templateUrl: './results-table.component.html',
  styleUrls: ['./results-table.component.scss'],
  inputs: ['fiveMatches', 'tenMatches']
})
export class ResultsTableComponent implements OnInit {

  activeTab: number = 0;
  tabKeys: string[] = [];
  fiveMatches: MatchObject;
  tenMatches: MatchObject;

  constructor() { }

  ngOnInit() {
    this.tabKeys = Object.keys(this.fiveMatches);

    jQuery('.menu .item')
      .tab();
  }

  setActiveTab(tabNumber: number) {
    this.activeTab = tabNumber;
  }

}
