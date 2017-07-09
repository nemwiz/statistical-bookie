import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {FixturesService} from "../../services/fixtures.service";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit {

  fixtureId: string;
  fixtureSubcription: Subscription;

  constructor(private route: ActivatedRoute,
              private fixturesService: FixturesService) {
    route.params.subscribe(params => { this.fixtureId = params['fixtureId']; });

    this.fixtureSubcription = this.fixturesService.fixturesSubject.subscribe(
      fixture => {
        console.log('Fixture received', fixture);
      }
    );
  }

  ngOnInit() {
  }

  ngOnDestroy() {
    this.fixtureSubcription.unsubscribe();
  }

}
