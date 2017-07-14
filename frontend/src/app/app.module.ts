import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomePageComponent } from './modules/home-page/home-page.component';
import {AppRoutes} from "./common/routes";
import {RouterModule} from "@angular/router";
import {FixturesService} from "./services/fixtures.service";
import {LeaguesService} from "./services/leagues.service";
import {MatchService} from "./services/match.service";
import {HttpModule} from "@angular/http";
import { MatchComponent } from './modules/match/match.component';
import { KeysPipe } from './common/keys-pipe';
import { ResultsTableComponent } from './components/results-table/results-table.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    MatchComponent,
    KeysPipe,
    ResultsTableComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(AppRoutes.routes)
  ],
  providers: [FixturesService, LeaguesService, MatchService],
  bootstrap: [AppComponent]
})
export class AppModule { }
