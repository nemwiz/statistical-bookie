import {AboutComponent} from '../about/about.component';
import {LandingPageComponent} from '../landing-page/landing-page.component';
import {Routes} from '@angular/router';

export class AppRoutes {

  public static routes: Routes = [
  { path: '', component: LandingPageComponent},
  { path: 'home', component: LandingPageComponent },
  { path: 'about', component: AboutComponent }
];

}
