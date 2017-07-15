import {Component, OnInit} from "@angular/core";
import {MatchObject} from "../../interfaces/match/match-object";

declare var jQuery: any;

@Component({
  selector: 'results-table',
  templateUrl: './results-table.component.html',
  styleUrls: ['./results-table.component.scss'],
  inputs: ['fiveMatches', 'tenMatches', 'structured']
})
export class ResultsTableComponent implements OnInit {

  activeTab: number = 0;
  tabKeys: string[] = [];
  fiveMatches: MatchObject;
  tenMatches: MatchObject;
  structured: boolean = true;

  constructor() { }

  ngOnInit() {

    this.constructTabKeys();

    jQuery('.menu .item')
      .tab();
  }

  private constructTabKeys() {
    let fiveMatchesKey: string[] = Object.keys(this.fiveMatches);
    let tenMatchesKey: string[] = Object.keys(this.tenMatches);

    let allKeys: string[] = [...fiveMatchesKey, ...tenMatchesKey];

    this.tabKeys = Array.from(new Set(allKeys));
  }

  setActiveTab(tabNumber: number) {
    this.activeTab = tabNumber;
  }

}
