import {ChangeDetectorRef, Component, NgZone, OnDestroy, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {MatchService} from "../../../services/match.service";
import {MatchObject} from "../../../interfaces/match/match-object";
import {Subscription} from "rxjs/Subscription";
import {UserMessageService} from "../../../services/user.message.service";

@Component({
  selector: 'match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit, OnDestroy {

  fixtureId: number = 0;
  lastFiveMatches: MatchObject[] = [];
  lastTenMatches: MatchObject[] = [];

  matchServiceSubscription: Subscription;
  routeParamsSubscription: Subscription;

  matchesAggregation: any;

  constructor(private route: ActivatedRoute,
              private matchService: MatchService,
              private userMessageService: UserMessageService,
              private changeDetector: ChangeDetectorRef,
              private ngZone: NgZone) {

    this.routeParamsSubscription = route.params.subscribe(params => {
      this.ngZone.run(() => {
        this.fixtureId = params['fixtureId'];
      });
    });

  }


  ngOnInit() {

    this.userMessageService.showLoadingSpinner();

    this.matchServiceSubscription = this.matchService.getMatchesByTeams()
      .subscribe((matches) => {

        this.userMessageService.hideLoadingSpinner();
        // if (Object.keys(matches).length === 0) {
        //   this.userMessageService.showErrorMessage('noDataAvailable')
        // }
        // this.matchesAggregation = matches;


        // this.ngZone.run(() => {
        //   this.lastFiveMatches = matches[0];
        //   this.lastTenMatches = matches[1];
        //   if (matches.length === 0) {
        //     this.errorMessage = 'noDataAvailable';
        //   }
        //   this.showLoadingSpinner = false;
        // });

      }, error => {
        this.userMessageService.showErrorMessage('serverError');
      });
  }

  ngOnDestroy(): void {
    if (this.matchServiceSubscription) {
      this.matchServiceSubscription.unsubscribe();
    }

    if (this.routeParamsSubscription) {
      this.routeParamsSubscription.unsubscribe();
    }
  }

}
