import {Component, OnInit} from '@angular/core';
import {League} from '../../interfaces/league';
import {LeaguesService} from '../../services/leagues.service';
import * as lodash from 'lodash';

@Component({
  selector: 'leagues-page',
  templateUrl: './leagues-page.component.html',
  styleUrls: ['./leagues-page.component.scss']
})
export class LeaguesPageComponent implements OnInit {

  flagsPath: string = '../../../assets/images/flags/';
  logosPath: string = '../../../assets/images/logos/';
  leagues: League[] = [];
  leaguesGroupedByCountry: any = [];

  constructor(private leagueService: LeaguesService) {
  }

  ngOnInit() {

    this.leagueService
      .getLeaguesWithCountries()
      .subscribe(leagues => {
        this.leagues = leagues;
        this.groupLeaguesByCountry(leagues);
      });

  }

  private groupLeaguesByCountry(leagues: League[]) {

    let groupByCountry = lodash.groupBy(leagues, 'countryName');

   for (let property in groupByCountry) {
     if (groupByCountry.hasOwnProperty(property)) {
       this.leaguesGroupedByCountry.push({countryName: property, leagues: groupByCountry[property]})
       console.log(property, groupByCountry[property]);
     }
   }

  }

}
