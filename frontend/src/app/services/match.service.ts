import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {BaseService} from './base.service';

@Injectable()
export class MatchService extends BaseService {

  constructor(http: Http) {
    super(http);
  }

  getMatches(teamName: string): Observable<any> {
    let query = 'match?homeTeamName=' + teamName;
    return this.get<any>(query);
  }

}
