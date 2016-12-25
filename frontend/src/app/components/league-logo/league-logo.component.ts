import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'league-logo',
  templateUrl: './league-logo.component.html',
  styleUrls: ['./league-logo.component.scss'],
  inputs: ['leagueName', 'logoUrl']
})
export class LeagueLogoComponent implements OnInit {

  leagueName: string;
  logoUrl: string;
  
  constructor() { }

  ngOnInit() {
  }

}
