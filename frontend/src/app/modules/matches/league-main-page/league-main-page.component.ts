import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FixturesService} from "../../../services/fixtures.service";
import {Fixture} from "../../../interfaces/fixture";
import {sortBy} from "lodash";
import {MatchService} from "../../../services/match.service";

@Component({
  selector: 'league-main-page',
  templateUrl: './league-main-page.component.html',
  styleUrls: ['./league-main-page.component.scss']
})
export class LeagueMainPageComponent implements OnInit {

  leagueFixtures: Fixture[] = [];
  isLoading: boolean = true;
  activeTab: number = 1;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private fixtureService: FixturesService,
              private matchService: MatchService) {

    this.route.params.subscribe((routeParams) => {

      let leagueId = routeParams['leagueId'];

      this.fixtureService.getUpcomingLeagueFixtures(leagueId)
        .subscribe(leagueFixtures => {
          this.leagueFixtures = sortBy(leagueFixtures, ['homeTeam']);
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
  }

}
