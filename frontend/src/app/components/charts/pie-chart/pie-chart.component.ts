import {Component, OnInit} from "@angular/core";
import {Pie, Svg} from "chartist";

@Component({
  selector: 'pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.scss'],
  inputs: ['labels', 'series', 'index', 'chartColors']
})
export class PieChartComponent implements OnInit {

  labels: string[];
  series: object;
  index: number;
  chartColors: string[];
  colorCounter: number = 0;

  constructor() { }

  ngOnInit() {
    setTimeout(() => {
      this.setUpPieChart({
        labels: this.labels,
        series: this.series
      });
    }, 1000);

  }

  private setUpPieChart(data: any) {
    new Pie(`#pie-chart-${this.index}`, data, {donut: true, donutWidth: 80})
      .on('draw', (context) => {
      if(context.type === 'slice') {
        let pathLength = context.element._node.getTotalLength();

        context.element.attr({
          'stroke-dasharray': pathLength + 'px ' + pathLength + 'px'
        });

        context.element.attr({
          style: `stroke: ${this.chartColors[this.colorCounter]};`
        });

        this.colorCounter++;

        let animationDefinition: any = {
          'stroke-dashoffset': {
            id: 'anim' + context.index,
            dur: 600,
            from: -pathLength + 'px',
            to:  '0px',
            easing: Svg.Easing.easeOutQuint,
            fill: 'freeze'
          }
        };

        if(context.index !== 0) {
          animationDefinition['stroke-dashoffset'].begin = 'anim' + (context.index - 1) + '.end';
        }

        context.element.attr({
          'stroke-dashoffset': -pathLength + 'px'
        });

        context.element.animate(animationDefinition, false);
      }
    });
  }

}
