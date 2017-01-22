import {Component, OnInit} from '@angular/core';
import {FixturesService} from '../../services/fixtures.service';
import {ActivatedRoute} from '@angular/router';
import {Fixture} from '../../interfaces/fixture';

@Component({
  selector: 'fixtures-page',
  templateUrl: './fixtures-page.component.html',
  styleUrls: ['./fixtures-page.component.scss']
})
export class FixturesPageComponent implements OnInit {

  fixtures:Fixture[];
  leagueId: string;

  constructor(private fixturesService:FixturesService,
              private route:ActivatedRoute) {
    route.params.subscribe(params => {
      this.leagueId = params['leagueId'];
    });
  }

  ngOnInit() {

    this.fixturesService
      .getUpcomingFixtures(this.leagueId)
      .subscribe(fixtures => {
        this.fixtures = fixtures;
      });

  }

}
