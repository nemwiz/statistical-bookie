import {Component, EventEmitter, OnInit} from "@angular/core";
import {Bar, IChartistData} from "chartist";
import {chartColors} from "../../../common/chart-colors";

@Component({
  selector: 'bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.scss'],
  inputs: ['labels', 'series', 'index'],
  outputs: ['isLoading']
})
export class BarChartComponent implements OnInit {

  labels: string[];
  series: object;
  index: number;
  chartColors: string[] = chartColors;
  colorCounter: number = 0;
  isLoading: EventEmitter<boolean> = new EventEmitter();

  constructor() { }

  ngOnInit() {

    let responsiveOptions = [
      ['screen and (min-width: 641px) and (max-width: 1024px)', {
        seriesBarDistance: 10,
      }],
      ['screen and (max-width: 640px)', {
        seriesBarDistance: 5,
      }]
    ];

    setTimeout(() => {
      this.setUpBarChart({
        labels: this.labels,
        series: [this.series]
      }, responsiveOptions);

      this.isLoading.emit(false);
    }, 1000);

  }

  private setUpBarChart(data: IChartistData, responsiveOptions: object) {
    new Bar(`#bar-chart-${this.index}`, data, responsiveOptions).on('draw', (context) => {
      context.element.animate({
        y2: {
          begin: 0,
          dur: 1200,
          from: context.y1,
          to: context.y2
        }
      });

      if(context.type === 'bar') {
        context.element.attr({
          style: `stroke: ${this.chartColors[this.colorCounter]};`
        });
        this.colorCounter++;
      }
    });
  }

}
