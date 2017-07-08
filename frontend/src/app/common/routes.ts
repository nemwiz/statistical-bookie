import {Routes} from '@angular/router';
import {HomePageComponent} from "../modules/home-page/home-page.component";

export class AppRoutes {

  public static routes:Routes = [
    {path: '', component: HomePageComponent},
  ];

}
