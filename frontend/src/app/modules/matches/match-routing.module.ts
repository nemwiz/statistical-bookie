import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {HomePageComponent} from "./home-page/home-page.component";
import {MatchComponent} from "./match/match.component";
import {LeagueMainPageComponent} from "./league-main-page/league-main-page.component";

export const routes:Routes = [
  {path: 'home', component: HomePageComponent, data: {title: 'statsBookie'}},
  {path: 'leagues/:leagueId', component: LeagueMainPageComponent, data: {title: 'leagueTitle'}},
  {path: 'fixtures/:_id', component: MatchComponent, data: {title: 'matchStats'}}
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class MatchRoutingModule { }
