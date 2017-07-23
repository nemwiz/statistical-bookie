import {NgModule} from "@angular/core";

import {AppComponent} from "./app.component";
import {MatchModule} from "./modules/matches/match.module";
import {AppRoutingModule} from "./modules/app-routing.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    MatchModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
