import {Injectable} from '@angular/core';
import {BaseService} from './base.service';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {Fixture} from '../interfaces/fixture';

@Injectable()
export class FixturesService extends BaseService {

  private BASE_ENDPOINT: string = 'fixtures';
  private LEAGUES_ENDPOINT: string = 'leagues/';

  constructor(http: Http) {
    super(http);
  }

  getUpcomingFixtures(leagueId: string): Observable<Fixture[]> {
    let endpoint = `${this.LEAGUES_ENDPOINT}${leagueId}/${this.BASE_ENDPOINT}/upcoming`;
    return this.get(endpoint);
  }

}
