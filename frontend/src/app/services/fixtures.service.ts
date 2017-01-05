import {Injectable} from '@angular/core';
import {BaseService} from './base.service';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {Fixture} from '../interfaces/fixture';

@Injectable()
export class FixturesService extends BaseService {

  private BASE_ENDPOINT: string = 'fixtures';

  constructor(http: Http) {
    super(http);
  }

  getFixtures(countryName: string, leagueName: string): Observable<Fixture[]> {
    let endpoint = `${this.BASE_ENDPOINT}/${countryName}/${leagueName}`;
    return this.get(endpoint);
  }

}
