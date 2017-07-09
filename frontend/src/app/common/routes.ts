import {Routes} from '@angular/router';
import {HomePageComponent} from "../modules/home-page/home-page.component";
import {MatchComponent} from "../modules/match/match.component";

export class AppRoutes {

  public static routes:Routes = [
    {path: '', component: HomePageComponent},
    {path: 'fixtures/:fixtureId', component: MatchComponent}
  ];

}
