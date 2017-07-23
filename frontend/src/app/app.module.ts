import {NgModule} from "@angular/core";

import {AppComponent} from "./app.component";
import {MatchModule} from "./modules/matches/match.module";
import {AppRoutingModule} from "./modules/app-routing.module";
import {BrowserModule} from "@angular/platform-browser";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    MatchModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
