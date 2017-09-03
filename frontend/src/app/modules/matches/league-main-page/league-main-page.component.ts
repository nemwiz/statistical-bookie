import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FixturesService} from "../../../services/fixtures.service";
import {Fixture} from "../../../interfaces/fixture";
import {sortBy} from "lodash";
import {MatchService} from "../../../services/match.service";
import {LeaguesService} from "../../../services/leagues.service";
import {LeagueTable} from "../../../interfaces/league-table";

@Component({
  selector: 'league-main-page',
  templateUrl: './league-main-page.component.html',
  styleUrls: ['./league-main-page.component.scss']
})
export class LeagueMainPageComponent implements OnInit {

  leagueFixtures: Fixture[] = [];
  leagueCode: string;
  isLoading: boolean = true;
  activeTab: number = 1;
  leagueTable: LeagueTable[] = [];

  shouldShowErrorMessage: boolean = false;
  errorMessage: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private fixtureService: FixturesService,
              private matchService: MatchService,
              private leagueService: LeaguesService) {

    this.route.params.subscribe((routeParams) => {

      let leagueId = routeParams['leagueId'];

      this.fixtureService.getUpcomingLeagueFixtures(leagueId)
        .subscribe(leagueFixtures => {
          this.leagueFixtures = sortBy(leagueFixtures, ['homeTeam']);
          if (this.leagueFixtures.length === 0) {
            this.isLoading = false;
            this.errorMessage = 'noDataAvailable';
            return;
          }
          this.leagueCode = this.leagueFixtures[0].leagueCode;
          this.isLoading = false;
        }, error => {
          this.shouldShowErrorMessage = true;
          this.errorMessage = 'serverError';
          this.isLoading = false;
        });
    });
  }

  ngOnInit() {
  }

  navigateToFixture(homeTeamName: string, awayTeamName: string, fixtureId: number) {
    this.matchService.setCurrentTeams(homeTeamName, awayTeamName);
    this.router.navigate(['fixtures', fixtureId]);
  }

  setActiveTab(tabNumber: number) {
    this.activeTab = tabNumber;

    if (this.activeTab === 2) {
      this.loadLeagueTable();
    }
  }

  private loadLeagueTable() {
    this.leagueService.getLeagueTable(this.leagueCode)
      .subscribe(leagueTable => {
        this.isLoading = true;
        this.leagueTable = sortBy(leagueTable, ['pointsWon', 'wins', 'losses']).reverse();
        if (this.leagueTable.length === 0) {
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
}
