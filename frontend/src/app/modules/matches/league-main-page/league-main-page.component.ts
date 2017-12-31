import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FixturesService} from "../../../services/fixtures.service";
import {Fixture} from "../../../interfaces/fixture";
import {sortBy} from "lodash";
import {MatchService} from "../../../services/match.service";
import {LeaguesService} from "../../../services/leagues.service";
import {LeagueTable} from "../../../interfaces/league-table";
import {Subscription} from "rxjs/Subscription";
import {UserMessageService} from "../../../services/user.message.service";
import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'league-main-page',
  templateUrl: './league-main-page.component.html',
  styleUrls: ['./league-main-page.component.scss']
})
export class LeagueMainPageComponent implements OnInit, OnDestroy {

  leagueFixtures: Fixture[] = [];
  leagueCode: string;
  isLoading: boolean = true;
  activeTab: number = 1;
  leagueTable: LeagueTable[] = [];

  routeParamsSubscription: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private fixtureService: FixturesService,
              private matchService: MatchService,
              private leagueService: LeaguesService,
              private userMessageService: UserMessageService) {
  }

  ngOnInit() {
    this.userMessageService.showLoadingSpinner();
    this.routeParamsSubscription = this.route.params.subscribe((routeParams) => {

      let leagueId = routeParams['leagueId'];

      this.fixtureService.getUpcomingLeagueFixtures(leagueId)
        .toPromise()
        .then(leagueFixtures => {
          this.userMessageService.hideLoadingSpinner();
          this.leagueFixtures = sortBy(leagueFixtures, ['homeTeam']);
          if (leagueFixtures && leagueFixtures.length === 0) {
            this.userMessageService.showErrorMessage('noDataAvailable');
            return;
          }
          this.leagueCode = this.leagueFixtures[0].leagueCode;

        }).catch(error => {
          this.userMessageService.showErrorMessage('serverError');
        }
      );
    });
  }

  navigateToFixture(homeTeamName: string, awayTeamName: string, fixtureId: number) {
    this.matchService.setCurrentTeams(homeTeamName, awayTeamName);
    this.router.navigate(['fixtures', fixtureId]);
  }

  setActiveTab(tabNumber: number) {
    this.activeTab = tabNumber;
    this.userMessageService.hideErrorMessage();

    if (this.activeTab === 2) {
      this.loadLeagueTable();
    }

  }

  private loadLeagueTable() {
    this.userMessageService.showLoadingSpinner();
    this.leagueService.getLeagueTable(this.leagueCode)
      .toPromise()
      .then(leagueTable => {

        this.userMessageService.hideLoadingSpinner();
        this.leagueTable = sortBy(leagueTable, ['pointsWon', 'wins', 'losses']).reverse();
        if (leagueTable && leagueTable.length === 0) {
          this.userMessageService.showErrorMessage('noDataAvailable');
          return;
        }

      }).catch(error => {
      this.userMessageService.showErrorMessage('serverError');
    });
  }

  ngOnDestroy(): void {
    if (this.routeParamsSubscription) {
      this.routeParamsSubscription.unsubscribe();
    }
  }
}
