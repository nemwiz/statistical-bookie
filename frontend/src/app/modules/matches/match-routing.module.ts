import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {HomePageComponent} from "./home-page/home-page.component";
import {MatchComponent} from "./match/match.component";
import {MatchDetailComponent} from "./match-detail/match-detail.component";

export const routes:Routes = [
  {path: 'home', component: HomePageComponent},
  {path: 'fixtures/:fixtureId', component: MatchComponent},
  {path: 'fixtures/:fixtureId/:details', component: MatchDetailComponent}
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class MatchRoutingModule { }
