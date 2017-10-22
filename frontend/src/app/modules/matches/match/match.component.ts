import {ChangeDetectorRef, Component, NgZone, OnDestroy, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {MatchService} from "../../../services/match.service";
import {MatchObject} from "../../../interfaces/match/match-object";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit, OnDestroy {

  isLoading: boolean = true;

  fixtureId: number = 0;
  lastFiveMatches: MatchObject[] = [];
  lastTenMatches: MatchObject[] = [];

  shouldShowErrorMessage: boolean = false;
  errorMessage: string;

  matchServiceSubscription: Subscription;
  routeParamsSubscription: Subscription;

  constructor(private route: ActivatedRoute,
              private matchService: MatchService,
              private changeDetector: ChangeDetectorRef,
              private ngZone: NgZone) {

    this.routeParamsSubscription = route.params.subscribe(params => {
      this.ngZone.run(() => {
        this.fixtureId = params['fixtureId'];
      });
    });
  }

  ngOnInit() {
    this.matchServiceSubscription = this.matchService.getMatchesByTeams()
      .subscribe((matches) => {

      this.ngZone.run(() => {
        this.lastFiveMatches = matches[0];
        this.lastTenMatches = matches[1];
        if (matches.length === 0) {
          this.errorMessage = 'noDataAvailable';
        }
        this.isLoading = false;
      });

      }, error => {
        this.shouldShowErrorMessage = true;
        this.errorMessage = 'serverError';
        this.isLoading = false;
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
