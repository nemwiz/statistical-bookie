import {Component, OnDestroy, OnInit} from "@angular/core";
import {MatchService} from "../../../services/match.service";
import "rxjs/add/operator/takeLast";
import {Subscription} from "rxjs/Subscription";
import {ResultsTableData} from "../../../interfaces/match/data-stats";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'match-detail',
  templateUrl: './match-detail.component.html',
  styleUrls: ['./match-detail.component.scss'],
})
export class MatchDetailComponent implements OnInit, OnDestroy {
  matchDataSubscription: Subscription;

  activeTab: number = 1;
  matchStats: ResultsTableData;
  detailsParam: string;

  constructor(private route: ActivatedRoute,
              private matchService: MatchService) {
    route.params.subscribe(params => {
      this.detailsParam = params['details'];
    });
  }

  ngOnInit() {

    this.matchDataSubscription = this.matchService.matchDataObservable
      .subscribe(matchStats => this.matchStats = matchStats);

  }

  setActiveTab(tab: number) {
    this.activeTab = tab;
  }

  ngOnDestroy(): void {
    this.matchDataSubscription.unsubscribe();
  }

}
