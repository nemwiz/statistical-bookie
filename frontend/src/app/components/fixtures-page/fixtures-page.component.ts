import { Component, OnInit } from '@angular/core';
import {FixturesService} from '../../services/fixtures.service';
import {Fixture} from '../../interfaces/fixture';

@Component({
  selector: 'fixtures-page',
  templateUrl: './fixtures-page.component.html',
  styleUrls: ['./fixtures-page.component.scss']
})
export class FixturesPageComponent implements OnInit {

  fixtures: Fixture[];
  
  constructor(private fixturesService: FixturesService) { }

  ngOnInit() {
    
    this.fixturesService
      .getFixtures()
      .subscribe(fixtures => {
        this.fixtures = fixtures;
      });
  }

}
