import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {MatchService} from "../../../services/match.service";
import {MatchObject} from "../../../interfaces/match/match-object";

@Component({
  selector: 'match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit {

  isLoading: boolean = true;

  fixtureId: number = 0;
  lastFiveMatches: MatchObject[] = [];
  lastTenMatches: MatchObject[] = [];

  shouldShowErrorMessage: boolean = false;
  errorMessage: string;

  constructor(private route: ActivatedRoute,
              private matchService: MatchService) {

    route.params.subscribe(params => this.fixtureId = params['fixtureId']);

    this.matchService.getMatchesByTeams()
      .subscribe((matches) => {
        this.lastFiveMatches = matches[0];
        this.lastTenMatches = matches[1];
        if (matches.length === 0) {
          this.isLoading = false;
          this.errorMessage = 'noDataAvailable';
          return;
        }
        this.isLoading = false;
      }, error => {
        this.shouldShowErrorMessage = true;
        this.errorMessage = 'serverError';
        this.isLoading = false;
      });
  }

  ngOnInit() {
  }

}
