import { Injectable } from '@angular/core';
import {BaseService} from './base.service';
import { Http } from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {Fixture} from '../interfaces/fixture';

@Injectable()
export class FixturesService extends BaseService {

  private BASE_ENDPOINT: string = 'fixtures';

  constructor(http: Http) {
    super(http);
  }

  getFixtures(): Observable<Fixture[]> {
    let endpoint = 'fixtures';
    return this.get(this.BASE_ENDPOINT);
  }

}
