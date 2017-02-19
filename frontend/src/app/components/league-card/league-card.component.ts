import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'league-card',
  templateUrl: './league-card.component.html',
  styleUrls: ['./league-card.component.scss'],
  inputs: ['leagueCode', 'leagueName', 'countryName', 'leagueId']
})
export class LeagueCardComponent implements OnInit {

  leagueCode: string;
  leagueName: string;
  countryName: string;
  leagueId: string;
  logosPath:string = '../../../assets/images/logos/';

  constructor() { }

  ngOnInit() {
  }

}
