import {Component, NgZone, OnDestroy, OnInit, ViewChild} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {MatchService} from "../../../services/match.service";
import {MatchObject} from "../../../interfaces/match/match-object";
import {Subscription} from "rxjs/Subscription";
import {UserMessageService} from "../../../services/user.message.service";
import 'rxjs/add/operator/toPromise';
import {sampleSize, shuffle} from 'lodash';
import {ChartData} from "../../../interfaces/chart-data";

const ACCORDION_INDEX_PREFIX = 'accordion';
const ACTIVE_CLASS = 'active';

@Component({
  selector: 'match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit, OnDestroy {

  @ViewChild('barchart') barChartContainer;
  fixtureId: number = 0;
  lastFiveMatches: MatchObject[] = [];
  lastTenMatches: MatchObject[] = [];

  routeParamsSubscription: Subscription;
  matchCount: number = 0;

  matchesAggregation: any;

  data: ChartData[];

  homeTeam: string;
  awayTeam: string;

  constructor(private route: ActivatedRoute,
              private matchService: MatchService,
              private userMessageService: UserMessageService,
              private ngZone: NgZone) {

    this.routeParamsSubscription = route.params.subscribe(params => {
      this.ngZone.run(() => {
        this.fixtureId = params['fixtureId'];
        this.homeTeam = this.matchService.getHomeTeam();
        this.awayTeam = this.matchService.getAwayTeam();
      });
    });

  }


  async ngOnInit() {

    this.userMessageService.showLoadingSpinner();

    this.matchesAggregation = await this.matchService.getMatchesByTeams().toPromise();

    this.userMessageService.hideLoadingSpinner();

    let aggregationKeys = Object.keys(this.matchesAggregation);

    if (aggregationKeys.length === 0) {
      this.userMessageService.showErrorMessage('noDataAvailable');
      return;
    }
    this.matchCount = this.matchesAggregation['matchCount'];
    this.data = shuffle(this.extractMostFrequentGamesForBarChart(aggregationKeys));
  }

  private extractMostFrequentGamesForBarChart(aggregationKeys: string[]) {
    let high = [];
    let middle = [];

    aggregationKeys.forEach(key => {
      Object.keys(this.matchesAggregation[key]).forEach(subkey => {
        let value = this.matchesAggregation[key][subkey] / this.matchCount * 100;

        if (value >= 80) {
          high.push({label: subkey, value: value})
        } else if (value >= 45) {
          middle.push({label: subkey, value: value})
        }

      })
    });

    let finalArray = [];
    let highSize = high.length;
    let middleSize = middle.length;

    if (highSize >= 5 && middleSize >= 5) {
      finalArray = sampleSize(high, 5).concat(sampleSize(middle, 5));
    } else if (highSize < 5 && highSize !== 0) {
      finalArray = sampleSize(high, highSize).concat(sampleSize(middle, 10 - highSize));
    } else {
      finalArray = sampleSize(middle, 10);
    }
    return finalArray;
  }

  openAccordion(accordionIndex: number): void {
    this.toogleActiveClassOnAccordion(accordionIndex);


    let panelElement = document.getElementById('panel-' + accordionIndex);

    panelElement.style.display === 'block' ? panelElement.style.display = 'none' : panelElement.style.display = 'block';
    panelElement.style.maxHeight ? panelElement.style.maxHeight = null : panelElement.style.maxHeight = `${panelElement.scrollHeight}px`
  }

  toogleActiveClassOnAccordion(accordionIndex: number) {
    let accordionElement = document.getElementById(`${ACCORDION_INDEX_PREFIX}-${accordionIndex}`);

    accordionElement.classList.contains(ACTIVE_CLASS) ?
      accordionElement.classList.remove(ACTIVE_CLASS) : accordionElement.classList.add(ACTIVE_CLASS);
  }

  ngOnDestroy(): void {
    if (this.routeParamsSubscription) {
      this.routeParamsSubscription.unsubscribe();
    }
  }

}
