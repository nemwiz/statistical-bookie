import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'leagues-page',
  templateUrl: './leagues-page.component.html',
  styleUrls: ['./leagues-page.component.scss']
})
export class LeaguesPageComponent implements OnInit {

  leagues = [
    {
      countryName: 'United Kingdom',
      countryFlagUrl: '',
      leagues: [
        'Premier league',
        'Championship',
        'League One'
      ]
    },
    {
      countryName: 'Spain',
      countryFlagUrl: '',
      leagues: [
        'La Liga',
        'La Liga 2'
      ]
    }
  ];

  constructor() {
  }

  ngOnInit() {
  }

}
