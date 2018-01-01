import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'aggregation-table',
  templateUrl: './aggregation-table.component.html',
  styleUrls: ['./aggregation-table.component.scss'],
  inputs: ['aggregation', 'matchCount']
})
export class AggregationTableComponent implements OnInit {

  aggregation: any;
  matchCount: number;

  constructor() { }

  ngOnInit() {
  }

  colorRowBasedOnPercentage(aggregationValue: number): string {
    let percentage = this.calculatePercentage(aggregationValue);
    return percentage >= 80 ? 'green-row' : percentage >= 60 ? 'yellow-row' : '';
  }

  calculatePercentage(aggregationValue: number) : number {
    return aggregationValue / this.matchCount * 100;
  }

}
