import {NgModule} from "@angular/core";

import {AppComponent} from "./app.component";
import {MatchModule} from "./modules/matches/match.module";
import {AppRoutingModule} from "./modules/app-routing.module";
import {BrowserModule} from "@angular/platform-browser";
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {Http} from "@angular/http";
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import { HeaderBarComponent } from './components/header-bar/header-bar.component';
import {UserMessageComponent} from "./components/user-message/user-message.component";

export function createTranslateLoader(http: Http) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderBarComponent,
    UserMessageComponent
  ],
  imports: [
    BrowserModule,
    MatchModule,
    AppRoutingModule,
    TranslateModule.forRoot(
      {
        loader: {
          provide: TranslateLoader,
          useFactory: (createTranslateLoader),
          deps: [Http]
        }
      }
    ),
    TranslateModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
