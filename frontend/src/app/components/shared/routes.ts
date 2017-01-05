import {LandingPageComponent} from '../landing-page/landing-page.component';
import {Routes} from '@angular/router';
import {LeaguesPageComponent} from '../leagues-page/leagues-page.component';
import {FixturesPageComponent} from '../fixtures-page/fixtures-page.component';
import {NotFoundPageComponent} from './not-found-page/not-found-page.component';
import {AggregatedMatchComponent} from '../aggregated-match/aggregated-match.component';

export class AppRoutes {

  public static routes:Routes = [
    {path: 'home', component: LandingPageComponent},
    {path: 'fixtures', component: LeaguesPageComponent},
    {path: 'fixtures/:countryName/:leagueName', component: FixturesPageComponent},
    {path: 'match/:homeTeam/:awayTeam', component: AggregatedMatchComponent},
    {path: '', component: LandingPageComponent},
    {path: '**', component: NotFoundPageComponent}
  ];

}
