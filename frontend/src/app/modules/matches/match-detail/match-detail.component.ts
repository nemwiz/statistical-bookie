import {Component, OnDestroy, OnInit} from "@angular/core";
import {MatchService} from "../../../services/match.service";
import "rxjs/add/operator/takeLast";
import {Subscription} from "rxjs/Subscription";
import {ResultsTableData} from "../../../interfaces/match/data-stats";
import {ActivatedRoute} from "@angular/router";
import {pickBy, values} from "lodash";
import {chartColors} from "../../../common/chart-colors";

@Component({
  selector: 'match-detail',
  templateUrl: './match-detail.component.html',
  styleUrls: ['./match-detail.component.scss'],
})
export class MatchDetailComponent implements OnInit, OnDestroy {
  matchDataSubscription: Subscription;

  matchStats: ResultsTableData;
  detailsParam: string;
  isLoading = true;
  chartColors: string[] = chartColors;

  constructor(private route: ActivatedRoute,
              private matchService: MatchService) {
    route.params.subscribe(params => {
      this.detailsParam = params['details'];
    });
  }

  ngOnInit() {
    this.matchDataSubscription = this.matchService.matchDataObservable
      .subscribe(matchStats =>  this.matchStats = matchStats);
    this.scrollToTop();
  }

  mapLabels(data: object): string[] {
    let dataWithoutNullValues = this.removeNullValues(data);

    return Object.keys(dataWithoutNullValues)
      .sort((a, b) => {return dataWithoutNullValues[a] - dataWithoutNullValues[b]})
      .reverse()
      .splice(0, 5);
  }

  mapSeries(data: object): object {
    let dataWithoutNullValues = this.removeNullValues(data);
    return values(dataWithoutNullValues).sort().reverse().splice(0, 5);
  }

  private removeNullValues(data: object): object {
    return pickBy(data, (value) => {return value !== 0});
  }

  shouldShowLoadingSpinner(event: boolean){
    this.isLoading = event;
  }

  scrollToTop() {
    document.body.scrollTop = 0;
  }

  ngOnDestroy(): void {
    this.matchDataSubscription.unsubscribe();
  }

}
