import {Injectable} from "@angular/core";
import {BaseService} from "./base.service";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Fixture} from "../interfaces/fixture";

@Injectable()
export class FixturesService extends BaseService {

  private BASE_ENDPOINT: string = 'fixtures';

  constructor(http: Http) {
    super(http);
  }

  getUpcomingFixtures(): Observable<Fixture[]> {
    let endpoint = `${this.BASE_ENDPOINT}`;
    return this.get(endpoint);
  }

  getUpcomingLeagueFixtures(leagueId: number): Observable<Fixture[]> {
    let endpoint = `${this.BASE_ENDPOINT}/${leagueId}/upcoming`;
    return this.get(endpoint);
  }

}
