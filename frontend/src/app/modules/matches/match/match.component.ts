import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Data} from "@angular/router";
import {FixturesService} from "../../../services/fixtures.service";
import {Subscription} from "rxjs/Subscription";
import {MatchService} from "../../../services/match.service";
import {MatchObject} from "../../../interfaces/match/match-object";
import {Fixture} from "../../../interfaces/fixture";
import {omit, pick} from "lodash";
import {ResultsTableData} from "../../../interfaces/match/data-stats";

@Component({
  selector: 'match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit {

  fixtureId: string;
  fixture: Fixture;
  fixtureSubcription: Subscription;

  structuredMatches: Data[] = [];
  specificStructureMatches: Data[] = [];

  constructor(private route: ActivatedRoute,
              private fixturesService: FixturesService,
              private matchService: MatchService) {

    route.params.subscribe(params => {
      this.fixtureId = params['fixtureId'];
    });

    this.fixtureSubcription = this.fixturesService.fixturesObservable.subscribe(
      fixture => {
        this.fixture = fixture;
        this.matchService.getMatchesByTeams(fixture.homeTeam, fixture.awayTeam)
          .subscribe((matches) => {
            this.mapMatchObject(matches);
          });
      });

  }

  ngOnInit() {
  }

  private mapMatchObject(matches: MatchObject[]) {

    matches.forEach(matchObject => {
      this.structuredMatches.push(omit(matchObject, ['matchDetailOutcomeView', 'halfTimeWithMoreGoalsView', 'exactResultView']));
      this.specificStructureMatches.push(pick(matchObject, ['matchDetailOutcomeView', 'halfTimeWithMoreGoalsView', 'exactResultView']));
    });

  }

  pushMatchData(fiveMatches: Data, tenMatches: Data, isStructured: boolean) {

    let results: ResultsTableData = {
      fiveMatches: fiveMatches,
      tenMatches: tenMatches,
      isStructured: isStructured
    };

    this.matchService.pushMatchData(results);
  }

  ngOnDestroy() {
    this.fixtureSubcription.unsubscribe();
  }

}
