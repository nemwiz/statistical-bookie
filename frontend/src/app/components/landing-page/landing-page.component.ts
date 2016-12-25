import { Component, OnInit } from '@angular/core';
import {LeagueLogo} from '../../interfaces/league-logo';

@Component({
  selector: 'landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {

  leagueList: LeagueLogo[] = [
    { leagueName: 'Barclays premier league', logoUrl:'http://vignette1.wikia.nocookie.net/logopedia/images/6/6e/Premiership20072008.png/revision/latest?cb=20120314142415'},
    {leagueName: 'Spanish primera', logoUrl: 'http://1.bp.blogspot.com/-Ynu7L5DQjGM/VAM1Wg4zY7I/AAAAAAAAAGU/uzLV4C041_U/s1600/01%2B%2Baa%2BSpanish%2BLFP.png'},
    {leagueName: 'German Bundesliga', logoUrl: 'https://upload.wikimedia.org/wikipedia/en/thumb/1/15/Bundesliga_logo.svg/1161px-Bundesliga_logo.svg.png'}
  ];

  constructor() { }

  ngOnInit() {
  }

}
