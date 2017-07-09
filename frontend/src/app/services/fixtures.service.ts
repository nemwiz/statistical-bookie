import {Injectable} from '@angular/core';
import {BaseService} from './base.service';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {Fixture} from '../interfaces/fixture';
import {ReplaySubject} from "rxjs/ReplaySubject";

@Injectable()
export class FixturesService extends BaseService {

  private BASE_ENDPOINT: string = 'fixtures';
  private LEAGUES_ENDPOINT: string = 'leagues/';

  fixturesSubject: ReplaySubject<Fixture>;

  constructor(http: Http) {
    super(http);
    this.fixturesSubject = new ReplaySubject(1);
  }

  getUpcomingFixtures(leagueId: string): Observable<Fixture[]> {
    let endpoint = `${this.LEAGUES_ENDPOINT}${leagueId}/${this.BASE_ENDPOINT}/upcoming`;
    return this.get(endpoint);
  }

  pushFixture(fixture: Fixture) {
    this.fixturesSubject.next(fixture);
  }

}
