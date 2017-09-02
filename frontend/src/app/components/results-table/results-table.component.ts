import {Component, OnInit} from "@angular/core";
import {Data} from "@angular/router";

@Component({
  selector: 'results-table',
  templateUrl: './results-table.component.html',
  styleUrls: ['./results-table.component.scss'],
  inputs: ['fiveMatches', 'tenMatches']
})
export class ResultsTableComponent implements OnInit {

  tabKeys: string[] = [];
  fiveMatches: Data;
  tenMatches: Data;

  constructor() { }

  ngOnInit() {
    this.constructTabKeys();
  }

  private constructTabKeys() {
    let fiveMatchesKey: string[] = Object.keys(this.fiveMatches);
    let tenMatchesKey: string[] = Object.keys(this.tenMatches);

    let allKeys: string[] = [...fiveMatchesKey, ...tenMatchesKey];

    this.tabKeys = Array.from(new Set(allKeys));
  }

}
