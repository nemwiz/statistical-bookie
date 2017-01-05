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
  countryName: string;
  leagueName: string;

  constructor(private fixturesService:FixturesService,
              private route:ActivatedRoute) {
    route.params.subscribe(params => {
      this.countryName = params['countryName'];
      this.leagueName = params['leagueName'];
    });
  }

  ngOnInit() {

    this.fixturesService
      .getFixtures(this.countryName, this.leagueName)
      .subscribe(fixtures => {
        this.fixtures = fixtures;
      });

  }

}
