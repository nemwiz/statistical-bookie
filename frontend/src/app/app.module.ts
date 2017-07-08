import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomePageComponent } from './modules/home-page/home-page.component';
import {AppRoutes} from "./common/routes";
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(AppRoutes.routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
