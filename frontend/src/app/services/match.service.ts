import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {BaseService} from "./base.service";
import {MatchObject} from "../interfaces/match/match-object";

@Injectable()
export class MatchService extends BaseService {

  private BASE_ENDPOINT: string = 'matches';

  currentHomeTeam: string;
  currentAwayTeam: string;

  constructor(http: Http) {
    super(http);
  }

  getMatchesByTeams(): Observable<[MatchObject[]]> {
    let endpoint = `${this.BASE_ENDPOINT}/aggregate/?homeTeam=${encodeURIComponent(this.currentHomeTeam)}&awayTeam=${encodeURIComponent(this.currentAwayTeam)}`;
    return this.get<any>(endpoint);
  }

  setCurrentTeams(homeTeamName: string, awayTeamName: string) {
    this.currentHomeTeam = homeTeamName;
    this.currentAwayTeam = awayTeamName;
  }

  getHomeTeam(): string {
    return this.currentHomeTeam;
  }

  getAwayTeam(): string {
    return this.currentAwayTeam;
  }

}
