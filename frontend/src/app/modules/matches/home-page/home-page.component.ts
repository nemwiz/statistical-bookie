import {ChangeDetectorRef, Component, NgZone, OnDestroy, OnInit} from "@angular/core";
import {LeaguesService} from "../../../services/leagues.service";
import {League} from "../../../interfaces/league";
import {Subscription} from "rxjs/Subscription";
import {Router} from "@angular/router";

@Component({
  selector: 'home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit, OnDestroy {

  isLoading: boolean = true;
  leagues: League[] = [];

  shouldShowErrorMessage: boolean = false;
  errorMessage: string;

  leagueServiceSubscription: Subscription;

  onOnline = () => {
    this.fetchData();
  };

  constructor(private leaguesService: LeaguesService,
              private changeDetector: ChangeDetectorRef,
              private router: Router,
              private ngZone: NgZone) {
  }

  ngOnInit() {

    document.addEventListener('online', this.onOnline);

    if (navigator['connection'] && navigator['connection'].type === 'none') {
      this.errorMessage = 'noInternetConnection';
      this.shouldShowErrorMessage = false;
      this.isLoading = false;
    } else {
      this.fetchData();
    }
  }

  private fetchData() {
    this.leagueServiceSubscription = this.leaguesService.getAllLeagues()
      .subscribe((leagues) => {
        this.leagues = leagues.sort((a, b) => {
          return a.id - b.id
        });
        if (leagues && leagues.length === 0) {
          this.errorMessage = 'noDataAvailable'
        }
        this.isLoading = false;
        this.changeDetector.detectChanges();
      }, error => {
        this.shouldShowErrorMessage = true;
        this.errorMessage = 'serverError';
        this.isLoading = false;
        this.changeDetector.detectChanges();
      });
  }

  navigateToLeague(leagueId: number) {
    this.ngZone.run(() => {
      this.router.navigate([`/leagues/${leagueId}`]);
    });
  }

  ngOnDestroy(): void {
    document.removeEventListener('online', this.onOnline);

    if (this.leagueServiceSubscription) {
      this.leagueServiceSubscription.unsubscribe();
    }
  }

}
