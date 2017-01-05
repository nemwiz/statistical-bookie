import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
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

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    TopNavbarComponent,
    FixturesPageComponent,
    LeaguesPageComponent,
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
