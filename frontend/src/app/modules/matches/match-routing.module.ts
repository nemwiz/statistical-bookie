import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {HomePageComponent} from "./home-page/home-page.component";
import {MatchComponent} from "./match/match.component";
import {MatchDetailComponent} from "./match-detail/match-detail.component";
import {LeagueMainPageComponent} from "./league-main-page/league-main-page.component";

export const routes:Routes = [
  {path: 'home', component: HomePageComponent, data: {title: 'statsBookie'}},
  {path: 'leagues/:leagueId', component: LeagueMainPageComponent, data: {title: 'leagueTitle'}},
  {path: 'fixtures/:fixtureId', component: MatchComponent, data: {title: 'matchStats'}},
  {path: 'fixtures/:fixtureId/:details', component: MatchDetailComponent, data: {title: 'matchDetails'}}
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class MatchRoutingModule { }
