import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {HomePageComponent} from "./home-page/home-page.component";
import {MatchComponent} from "./match/match.component";
import {KeysPipe} from "../../common/keys-pipe";
import {ResultsTableComponent} from "../../components/results-table/results-table.component";
import {PieChartComponent} from "../../components/charts/pie-chart/pie-chart.component";
import {MatchDetailComponent} from "./match-detail/match-detail.component";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {MatchRoutingModule} from "./match-routing.module";
import {MatchService} from "../../services/match.service";
import {LeaguesService} from "../../services/leagues.service";
import {FixturesService} from "../../services/fixtures.service";
import {BarChartComponent} from "../../components/charts/bar-chart/bar-chart.component";

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    HttpModule,
    MatchRoutingModule
  ],
  declarations: [
    HomePageComponent,
    MatchComponent,
    KeysPipe,
    ResultsTableComponent,
    PieChartComponent,
    BarChartComponent,
    MatchDetailComponent,
  ],
  providers: [MatchService, FixturesService, LeaguesService],
})
export class MatchModule { }
