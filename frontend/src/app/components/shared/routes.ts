import {LandingPageComponent} from '../landing-page/landing-page.component';
import {Routes} from '@angular/router';
import {LeaguesPageComponent} from '../leagues-page/leagues-page.component';
import {FixturesPageComponent} from '../fixtures-page/fixtures-page.component';

export class AppRoutes {

  public static routes: Routes = [
  { path: '', component: LandingPageComponent},
  { path: 'home', component: LandingPageComponent },
  { path: 'fixtures', component: LeaguesPageComponent },
  { path: 'fixtures/:countryName/:leagueName', component: FixturesPageComponent }
];

}
