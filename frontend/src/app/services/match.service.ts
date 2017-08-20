import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {BaseService} from "./base.service";
import {MatchObject} from "../interfaces/match/match-object";
import {Subject} from "rxjs/Subject";
import {ReplaySubject} from "rxjs/ReplaySubject";
import {ResultsTableData} from "../interfaces/match/data-stats";

@Injectable()
export class MatchService extends BaseService {

  private BASE_ENDPOINT: string = 'matches';

  private matchDataSubject: Subject<ResultsTableData>;
  matchDataObservable: Observable<ResultsTableData>;

  constructor(http: Http) {
    super(http);
    this.matchDataSubject = new ReplaySubject(1);
    this.matchDataObservable = this.matchDataSubject.asObservable();
  }

  getMatchesByTeams(homeTeamName: string, awayTeamName: string): Observable<MatchObject[]> {
    let endpoint = `${this.BASE_ENDPOINT}/aggregate/?homeTeam=${encodeURIComponent(homeTeamName)}&awayTeam=${encodeURIComponent(awayTeamName)}`;
    return this.get<any>(endpoint);
  }

  pushMatchData(data: ResultsTableData) {
    this.matchDataSubject.next(data);
  }

}
