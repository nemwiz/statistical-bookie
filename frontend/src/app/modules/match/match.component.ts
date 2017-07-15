import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {FixturesService} from "../../services/fixtures.service";
import {Subscription} from "rxjs/Subscription";
import {MatchService} from "../../services/match.service";
import {MatchObject} from "../../interfaces/match/match-object";
import {Fixture} from "../../interfaces/fixture";
import {pick, omit} from 'lodash';

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
  structuredMatches: object[] = [];
  specificStructureMatches: object[] = [];

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
            this.mapMatchObject(matches);
          });
      });

  }

  ngOnInit() {
    jQuery('.ui.accordion').accordion();
  }

  private mapMatchObject(matches: MatchObject[]) {

    matches.forEach(matchObject => {
      this.structuredMatches.push(omit(matchObject, ['matchDetailOutcomeView', 'halfTimeWithMoreGoalsView', 'exactResultView']));
      this.specificStructureMatches.push(pick(matchObject, ['matchDetailOutcomeView', 'halfTimeWithMoreGoalsView', 'exactResultView']));
    });

  }

  toggleContent(event) {
    this.toggle = !this.toggle;
  }

  ngOnDestroy() {
    this.fixtureSubcription.unsubscribe();
  }

}
