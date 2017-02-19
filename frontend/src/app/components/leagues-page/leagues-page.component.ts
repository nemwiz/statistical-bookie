import {Component, OnInit} from '@angular/core';
import {League} from '../../interfaces/league';
import {LeaguesService} from '../../services/leagues.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import * as lodash from 'lodash';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'leagues-page',
  templateUrl: './leagues-page.component.html',
  styleUrls: ['./leagues-page.component.scss']
})
export class LeaguesPageComponent implements OnInit {

  leagues:Observable<League[]>;
  leaguesGroupedByCountry:any = [];
  visibleLeagues: any = [];

  leaguesSearchForm:FormGroup;
  leaguesSearchInput;

  constructor(private leagueService:LeaguesService, private formBuilder:FormBuilder) {
    this.leaguesSearchForm = formBuilder.group(
      {'leagueSearchInput': ['']}
    );

    this.leaguesSearchInput = this.leaguesSearchForm.controls['leagueSearchInput'];

  }

  ngOnInit() {

    this.leagues = this.leagueService
      .getLeaguesWithCountries();

    this.leagues.subscribe(data => {
      this.groupLeaguesByCountry(data);
    });

    this.leaguesSearchInput.valueChanges.subscribe((value) => {
      // console.log('Data', value.countryName);

      this.visibleLeagues = this.leaguesGroupedByCountry.filter(league => {
        return league.countryName.toLowerCase().indexOf(value.toLowerCase()) >= 0;
      });

    });


  }

  private groupLeaguesByCountry(leagues:League[]) {

    let groupByCountry = lodash.groupBy(leagues, 'countryName');

    for (let property in groupByCountry) {
      if (groupByCountry.hasOwnProperty(property)) {
        this.leaguesGroupedByCountry.push({countryName: property, leagues: groupByCountry[property]});
        this.visibleLeagues.push({countryName: property, leagues: groupByCountry[property]});
      }
    }

  }

}
