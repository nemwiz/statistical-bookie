import { Injectable } from '@angular/core';
import {BaseService} from './base.service';
import {Observable} from 'rxjs/Observable';
import { Http } from '@angular/http';
import {League} from '../interfaces/league';

@Injectable()
export class LeaguesService extends BaseService{

  private BASE_ENDPOINT: string = 'leagues';

  constructor(http: Http) {
    super(http);
  }

  getLeaguesWithCountries(): Observable<League[]> {
    let endpoint = `${this.BASE_ENDPOINT}`;
    return this.get<League[]>(endpoint);
  }


}
