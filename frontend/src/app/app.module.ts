import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AppComponent} from './app.component';
import {LandingPageComponent} from './components/landing-page/landing-page.component';
import {LeagueLogoComponent} from './components/league-logo/league-logo.component';
import {TopNavbarComponent} from './components/shared/top-navbar/top-navbar.component';
import {AboutComponent} from './components/about/about.component';
import {MatchService} from './services/match.service';
import {RouterModule} from '@angular/router';
import {AppRoutes} from './components/shared/routes';
import { FixturesPageComponent } from './components/fixtures-page/fixtures-page.component'
import {FixturesService} from './services/fixtures.service';

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    LeagueLogoComponent,
    TopNavbarComponent,
    AboutComponent,
    FixturesPageComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(AppRoutes.routes)
  ],
  providers: [MatchService, FixturesService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
