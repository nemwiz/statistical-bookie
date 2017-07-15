import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {BaseService} from './base.service';
import {MatchObject} from "../interfaces/match/match-object";

@Injectable()
export class MatchService extends BaseService {

  private BASE_ENDPOINT: string = 'matches';

  constructor(http: Http) {
    super(http);
  }

  getMatchesByTeams(homeTeamName: string, awayTeamName: string): Observable<MatchObject[]> {
    let endpoint = `${this.BASE_ENDPOINT}/aggregate/?homeTeam=${homeTeamName}&awayTeam=${awayTeamName}`;
    return this.get<any>(endpoint);
  }

}
