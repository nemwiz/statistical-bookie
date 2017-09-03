import { Component, OnInit } from '@angular/core';
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
          if (this.leagueFixtures.length !== 0) {
            this.leagueCode = this.leagueFixtures[0].leagueCode;
          }
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
        this.leagueTable = sortBy(leagueTable, ['pointsWon', 'wins', 'losses']).reverse();
      });
  }
}
