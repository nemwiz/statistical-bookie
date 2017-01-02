import {Component, OnInit} from '@angular/core';
import {LeagueLogo} from '../../interfaces/league-logo';
import {Paths} from '../../common/paths';
import {MatchService} from '../../services/match.service';

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

  constructor(private matchService: MatchService) {
  }

  ngOnInit() {
    this.matchService.getMatches('Chelsea')
      .subscribe( data => {
        console.log(data);
      });
  }

  private getLogoPathFromBase(imageName:string):string {
    return Paths.logosBasePath + imageName;
  }
}
