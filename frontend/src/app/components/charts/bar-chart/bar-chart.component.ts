import {Component, OnInit} from "@angular/core";
import {pickBy, values} from "lodash";
import {Bar, IChartistData} from "chartist";

@Component({
  selector: 'bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.scss'],
  inputs: ['data', 'index']
})
export class BarChartComponent implements OnInit {

  data: object;
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
        labels: sortedLabels,
        series: [sortedSeries]
      }, responsiveOptions);
    }, 1000);

  }

  private setUpBarChart(data: IChartistData, responsiveOptions: object) {
    new Bar(`#bar-chart-${this.index}`, data, responsiveOptions).on('draw', (bars) => {
      bars.element.animate({
        y2: {
          begin: 0,
          dur: 1200,
          from: bars.y1,
          to: bars.y2
        }
      })
    });
  }

}
