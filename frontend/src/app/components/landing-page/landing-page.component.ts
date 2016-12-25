import {Component, OnInit} from '@angular/core';
import {LeagueLogo} from '../../interfaces/league-logo';
import {Paths} from '../../common/paths';

@Component({
  selector: 'landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {

  leagueList:LeagueLogo[] = [
    {leagueName: 'Barclays premier league', logoUrl: this.getLogoPathFromBase('barclays-logo.png')},
    {leagueName: 'Spanish primera', logoUrl: this.getLogoPathFromBase('la-liga-logo.png')},
    {leagueName: 'German Bundesliga', logoUrl: this.getLogoPathFromBase('bundesliga-logo.png')}
  ];

  constructor() {
  }

  ngOnInit() {}

  private getLogoPathFromBase(imageName:string):string {
    return Paths.logosBasePath + imageName;
  }
}
