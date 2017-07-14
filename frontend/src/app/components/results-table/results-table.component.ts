import { Component, OnInit } from '@angular/core';

declare var jQuery: any;

@Component({
  selector: 'results-table',
  templateUrl: './results-table.component.html',
  styleUrls: ['./results-table.component.scss'],
  inputs: ['results']
})
export class ResultsTableComponent implements OnInit {

  activeTab: number = 0;
  tabKeys: string[] = [];
  results: object;

  constructor() { }

  ngOnInit() {
    this.tabKeys = Object.keys(this.results);

    jQuery('.menu .item')
      .tab();
  }

  setActiveTab(tabNumber: number) {
    this.activeTab = tabNumber;
  }

}
