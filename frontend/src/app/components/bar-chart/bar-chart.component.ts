import {Component, OnChanges, OnInit, SimpleChange, SimpleChanges} from '@angular/core';
import {chartColors} from "../../common/chart-colors";
import * as d3 from 'd3';
import {random, orderBy} from 'lodash';
import {ChartData} from "../../interfaces/chart-data";

const MARGIN_TOP = 25;
const SIDE_MARGINS = 50;

@Component({
  selector: 'bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.scss'],
  inputs: ['chartData']
})
export class BarChartComponent implements OnInit, OnChanges {
  chartData: ChartData[];

  private section;
  private chartWidth;
  private chartHeight;

  constructor() {
  }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    let data: SimpleChange = changes.chartData;
    this.chartData = data.currentValue;

    if (!this.chartData) {
      return;
    }

    if (!this.chartWidth) {
      this.setupChartContainer();
    }

    let barOffset = 10;
    let barWidth = Math.round((this.chartWidth - this.chartData.length * barOffset) / this.chartData.length);

    this.chartData = orderBy(this.chartData, ['value'], ['desc']);

    let maxValue = d3.max(this.chartData.map(data => {return data.value}));

    let yScale = d3.scaleLinear()
      .domain([0, maxValue])
      .range([0, this.chartHeight]);


    this.section
      .selectAll('rect')
      .data(this.chartData)
      .enter()
      .append('rect')
      .attr('fill', () => {
            return chartColors[random(0, chartColors.length - 1)]
          })
      .attr('width', barWidth)
      .attr('height', (d) => {return yScale(d.value)})
      .attr('x', (d, i) => { return i * (barWidth + barOffset)})
      .attr('y', (d) => { return (this.chartHeight + MARGIN_TOP) - yScale(d.value)});

    let labels = this.chartData.map(data => data.label);

    this.section
      .selectAll('text')
      .data(this.chartData)
      .enter()
      .append('text')
      .text((d, i) => {return labels[i]})
      .attr('width', barWidth)
      .attr('height', barWidth)
      .attr('x', (d, i) => {return (this.chartHeight + MARGIN_TOP) - yScale(d.value) + 10})
      .attr('y', (d, i) => {return -(i * (barWidth + barOffset) + 6)})
      .attr("transform", "rotate(90)")

  }

  setupChartContainer(): void {
    this.chartHeight = window.innerHeight / 3;
    this.chartWidth = window.innerWidth - SIDE_MARGINS;

    this.section = d3.select('.bar-chart')
      .attr('width', this.chartWidth)
      .attr('height', this.chartHeight + MARGIN_TOP);
  }

}
