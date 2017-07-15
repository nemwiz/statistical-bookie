import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {FixturesService} from "../../services/fixtures.service";
import {Subscription} from "rxjs/Subscription";
import {MatchService} from "../../services/match.service";
import {MatchObject} from "../../interfaces/match/match-object";
import {Fixture} from "../../interfaces/fixture";

declare var jQuery: any;

@Component({
  selector: 'match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit {

  fixtureId: string;
  fixture: Fixture;
  fixtureSubcription: Subscription;
  matches: MatchObject[] = [];

  toggle: boolean = true;

  constructor(private route: ActivatedRoute,
              private fixturesService: FixturesService,
              private matchService: MatchService) {
    route.params.subscribe(params => {
      this.fixtureId = params['fixtureId'];
    });

    this.fixtureSubcription = this.fixturesService.fixturesSubject.subscribe(
      fixture => {
        this.fixture = fixture;
        this.matchService.getMatchesByTeams(fixture.homeTeam, fixture.awayTeam)
          .subscribe((matches) => {
            this.matches = matches;
          });
      });

  }

  ngOnInit() {
    jQuery('.ui.accordion').accordion();
  }

  toggleContent(event) {
    this.toggle = !this.toggle;
  }

  ngOnDestroy() {
    this.fixtureSubcription.unsubscribe();
  }

}
