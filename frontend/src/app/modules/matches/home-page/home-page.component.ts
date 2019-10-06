import {Component, NgZone, OnDestroy, OnInit} from "@angular/core";
import {LeaguesService} from "../../../services/leagues.service";
import {League} from "../../../interfaces/league";
import {Router} from "@angular/router";
import {UserMessageService} from "../../../services/user.message.service";
import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit, OnDestroy {

  leagues: League[] = [];

  onOnline = () => {
    this.fetchData();
  };

  constructor(private leaguesService: LeaguesService,
              private userMessageService: UserMessageService,
              private router: Router,
              private ngZone: NgZone) {
  }

  ngOnInit() {

    this.userMessageService.showLoadingSpinner();

    document.addEventListener('online', this.onOnline);

    if (navigator['connection'] && navigator['connection'].type === 'none') {
      this.userMessageService.showErrorMessage('noInternetConnection');
    } else {
      this.fetchData();
    }
  }

  private fetchData() {
    this.leaguesService.getAllLeagues()
      .toPromise()
      .then((leagues) => {
        this.leagues = leagues.sort((a, b) => {
          return a._id - b._id
        });
        if (leagues && leagues.length === 0) {
          this.userMessageService.showErrorMessage('noDataAvailable');
          return;
        }
        this.userMessageService.hideLoadingSpinner();
      }).catch(error => {
      this.userMessageService.showErrorMessage('serverError');
    });
  }

  navigateToLeague(leagueId: number) {
    this.ngZone.run(() => {
      this.router.navigate([`/leagues/${leagueId}`]);
    });
  }

  ngOnDestroy(): void {
    document.removeEventListener('online', this.onOnline);
  }

}
