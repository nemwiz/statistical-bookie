import {Component, OnInit} from "@angular/core";
import {FixturesService} from "../../../services/fixtures.service";
import {Fixture} from "../../../interfaces/fixture";
import {groupBy, sortBy} from "lodash";

@Component({
  selector: 'home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  fixtures: Fixture[] = [];
  groupedFixtures: object = {};
  isLoading: boolean = true;

  constructor(private fixturesService: FixturesService) {
  }

  ngOnInit() {

    this.fixturesService.getUpcomingFixtures()
      .subscribe(fixtures => {
        this.fixtures = fixtures;
        this.groupedFixtures = groupBy(sortBy(this.fixtures, ['leagueId', 'homeTeam']), 'leagueName');
        this.isLoading = false;
      });

  }

}
