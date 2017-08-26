import {BaseService} from "./base.service";
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {League} from "../interfaces/league";

@Injectable()
export class LeaguesService extends BaseService {

  private BASE_ENDPOINT: string = 'leagues';

  constructor(http: Http) {
    super(http);
  }

  getAllLeagues(): Observable<League[]> {
    let endpoint = `${this.BASE_ENDPOINT}`;
    return this.get(endpoint);
  }
}
