import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { LeagueLogoComponent } from './components/league-logo/league-logo.component';
import { TopNavbarComponent } from './components/shared/top-navbar/top-navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    LeagueLogoComponent,
    TopNavbarComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
