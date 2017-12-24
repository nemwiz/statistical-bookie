import {Component, NgZone, OnDestroy, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {MatchService} from "../../../services/match.service";
import {MatchObject} from "../../../interfaces/match/match-object";
import {Subscription} from "rxjs/Subscription";
import {UserMessageService} from "../../../services/user.message.service";
import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit, OnDestroy {

  fixtureId: number = 0;
  lastFiveMatches: MatchObject[] = [];
  lastTenMatches: MatchObject[] = [];

  routeParamsSubscription: Subscription;
  selectedAccordionIndex: number;
  matchCount: number = 0;

  matchesAggregation: any;

  constructor(private route: ActivatedRoute,
              private matchService: MatchService,
              private userMessageService: UserMessageService,
              private ngZone: NgZone) {

    this.routeParamsSubscription = route.params.subscribe(params => {
      this.ngZone.run(() => {
        this.fixtureId = params['fixtureId'];
      });
    });

  }


  ngOnInit() {

    this.userMessageService.showLoadingSpinner();

    this.matchService.getMatchesByTeams().toPromise()
      .then((matches) => {
        this.userMessageService.hideLoadingSpinner();

        if (Object.keys(matches).length === 0) {
          this.userMessageService.showErrorMessage('noDataAvailable');
          return;
        }
        this.matchesAggregation = matches;
        this.matchCount = this.matchesAggregation['matchCount'];
      });
  }

  openAccordion(accordionIndex: number): void {
    this.selectedAccordionIndex = accordionIndex;
    let panelElement = document.getElementById('panel-' + accordionIndex);

    panelElement.style.display === 'block' ? panelElement.style.display = 'none' : panelElement.style.display = 'block';
    panelElement.style.maxHeight ? panelElement.style.maxHeight = null : panelElement.style.maxHeight =  `${panelElement.scrollHeight}px`
  }

  isActiveAccordion(accordionIndex: number): boolean {
    return this.selectedAccordionIndex === accordionIndex;
  }

  ngOnDestroy(): void {
    if (this.routeParamsSubscription) {
      this.routeParamsSubscription.unsubscribe();
    }
  }

}
