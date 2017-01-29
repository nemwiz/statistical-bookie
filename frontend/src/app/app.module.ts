import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AppComponent} from './app.component';
import {LandingPageComponent} from './components/landing-page/landing-page.component';
import {TopNavbarComponent} from './components/shared/top-navbar/top-navbar.component';
import {MatchService} from './services/match.service';
import {RouterModule} from '@angular/router';
import {AppRoutes} from './components/shared/routes';
import {FixturesPageComponent} from './components/fixtures-page/fixtures-page.component';
import {FixturesService} from './services/fixtures.service';
import { LeaguesPageComponent } from './components/leagues-page/leagues-page.component';
import { NotFoundPageComponent } from './components/shared/not-found-page/not-found-page.component';
import { AggregatedMatchComponent } from './components/aggregated-match/aggregated-match.component';
import {LeaguesService} from './services/leagues.service';
import { PieChartComponent } from './components/pie-chart/pie-chart.component';
import { ChartsModule } from 'ng2-charts/ng2-charts';

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    TopNavbarComponent,
    FixturesPageComponent,
    LeaguesPageComponent,
    NotFoundPageComponent,
    AggregatedMatchComponent,
    PieChartComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ChartsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(AppRoutes.routes)
  ],
  providers: [MatchService, FixturesService, LeaguesService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
