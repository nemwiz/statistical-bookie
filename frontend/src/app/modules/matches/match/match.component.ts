import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Data} from "@angular/router";
import {MatchService} from "../../../services/match.service";
import {MatchObject} from "../../../interfaces/match/match-object";
import {omit, pick, capitalize} from "lodash";
import {ResultsTableData} from "../../../interfaces/match/data-stats";

@Component({
  selector: 'match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit {

  homeTeam: string = '';
  awayTeam: string = '';

  isLoading: boolean = true;

  structuredMatches: Data[] = [];
  specificStructureMatches: Data[] = [];

  constructor(private route: ActivatedRoute,
              private matchService: MatchService) {

    route.params.subscribe(params => {
      let objectKeys = Object.keys(params);
      let paramValue: string = params[objectKeys[0]];

      let dashIndex = paramValue.indexOf('-', 0);

      this.homeTeam = paramValue.substring(0, dashIndex);
      this.awayTeam = paramValue.substring(dashIndex + 1, paramValue.length);
    });

    this.matchService.getMatchesByTeams(capitalize(this.homeTeam), capitalize(this.awayTeam))
      .subscribe((matches) => {
        this.mapMatchObject(matches);
        this.isLoading = false;
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

}
