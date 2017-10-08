import {ChangeDetectorRef, Component, OnDestroy, OnInit} from "@angular/core";
import {LeaguesService} from "../../../services/leagues.service";
import {League} from "../../../interfaces/league";

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

  onOnline = () => {
    this.fetchData();
  };

  constructor(private leaguesService: LeaguesService,
              private changeDetector: ChangeDetectorRef) {
  }

  ngOnInit() {

    document.addEventListener('online', this.onOnline);

    if (navigator['connection'].type === 'none') {
      this.errorMessage = 'noInternetConnection';
      this.shouldShowErrorMessage = false;
      this.isLoading =  false;
    } else {
      this.fetchData();
    }
  }

  private fetchData() {
    this.leaguesService.getAllLeagues()
      .subscribe((leagues) => {
        this.leagues = leagues.sort((a, b) => {
          return a.id - b.id
        });
        if (this.leagues.length === 0) {
          this.errorMessage = 'noDataAvailable'
        }
        this.isLoading = false;
        this.changeDetector.detectChanges();
      }, error => {
        this.shouldShowErrorMessage = true;
        this.errorMessage = 'serverError';
        this.isLoading = false;
      });
  }

  ngOnDestroy(): void {
    document.removeEventListener('online', this.onOnline);
  }

}
