import {Component, OnInit} from "@angular/core";
import {IChartistData, Pie, Svg} from "chartist";
import {Goals} from "../../../interfaces/match/goals";
import {pickBy, values} from "lodash";

@Component({
  selector: 'pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.scss'],
  inputs: ['data', 'index']
})
export class PieChartComponent implements OnInit {

  data: Goals;
  index: number;

  constructor() { }

  ngOnInit() {

    let dataWithoutNullValues = pickBy(this.data, (value) => {
      return value !== 0
    });

    let sortedLabels = Object.keys(dataWithoutNullValues)
      .sort((a, b) => {return dataWithoutNullValues[a] - dataWithoutNullValues[b]})
      .reverse()
      .splice(0, 5);

    let sortedSeries = values(dataWithoutNullValues).sort().reverse().splice(0, 5);
    let chartData: IChartistData = {
      labels: sortedLabels,
      series: sortedSeries
    };

    setTimeout(() => {
      this.setUpPieChart(chartData);
    }, 1000);

  }

  private setUpPieChart(data: IChartistData) {
    new Pie(`#pie-chart-${this.index}`, data, {donut: true, donutWidth: 80})
      .on('draw', (data) => {
      if(data.type === 'slice') {
        let pathLength = data.element._node.getTotalLength();

        data.element.attr({
          'stroke-dasharray': pathLength + 'px ' + pathLength + 'px'
        });

        let animationDefinition: any = {
          'stroke-dashoffset': {
            id: 'anim' + data.index,
            dur: 600,
            from: -pathLength + 'px',
            to:  '0px',
            easing: Svg.Easing.easeOutQuint,
            fill: 'freeze'
          }
        };

        if(data.index !== 0) {
          animationDefinition['stroke-dashoffset'].begin = 'anim' + (data.index - 1) + '.end';
        }

        data.element.attr({
          'stroke-dashoffset': -pathLength + 'px'
        });

        data.element.animate(animationDefinition, false);
      }
    });
  }

}
