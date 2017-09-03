import {Component, OnInit} from "@angular/core";
import {LeaguesService} from "../../../services/leagues.service";
import {League} from "../../../interfaces/league";

@Component({
  selector: 'home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  isLoading: boolean = true;
  leagues: League[] = [];

  shouldShowErrorMessage: boolean = false;
  errorMessage: string;

  constructor(private leaguesService: LeaguesService) {
  }

  ngOnInit() {

    this.leaguesService.getAllLeagues()
      .subscribe((leagues) => {
        this.leagues = leagues.sort((a, b) => { return a.id - b.id});
        if (this.leagues.length === 0) {
          this.errorMessage = 'noDataAvailable'
        }
        this.isLoading = false;
      }, error => {
        this.shouldShowErrorMessage = true;
        this.errorMessage = 'serverError';
        this.isLoading = false;
      });
  }

}
