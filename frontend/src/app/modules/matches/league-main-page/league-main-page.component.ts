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
    this.routeParamsSubscription = this.route.params.subscribe(async (routeParams) => {

      let leagueId = routeParams['leagueId'];
      let leagueFixtures: Fixture[];

      try {
        leagueFixtures = await this.fixtureService.getUpcomingLeagueFixtures(leagueId).toPromise();
        this.userMessageService.hideLoadingSpinner();
      } catch (error) {
        this.userMessageService.showErrorMessage('serverError');
      }

      if (leagueFixtures && leagueFixtures.length === 0) {
        this.userMessageService.showErrorMessage('noDataAvailable');
        return;
      }

      this.leagueFixtures = sortBy(leagueFixtures, ['homeTeam']);
      this.leagueCode = this.leagueFixtures[0].leagueCode;
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

  private async loadLeagueTable() {
    this.userMessageService.showLoadingSpinner();

    let leagueTable: LeagueTable[];

    try {
      leagueTable = await this.leagueService.getLeagueTable(this.leagueCode).toPromise();
      this.userMessageService.hideLoadingSpinner();
    } catch (error) {
      this.userMessageService.showErrorMessage('serverError');
    }

    if (leagueTable && leagueTable.length === 0) {
      this.userMessageService.showErrorMessage('noDataAvailable');
      return;
    }

    this.leagueTable = sortBy(leagueTable, ['pointsWon', 'wins', 'losses']).reverse();
  }

  ngOnDestroy(): void {
    if (this.routeParamsSubscription) {
      this.routeParamsSubscription.unsubscribe();
    }
  }
}
