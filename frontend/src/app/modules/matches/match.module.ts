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
import {FixturesService} from "../../services/fixtures.service";
import {BarChartComponent} from "../../components/charts/bar-chart/bar-chart.component";
import {TranslateModule} from '@ngx-translate/core';
import {LeaguesService} from "../../services/leagues.service";
import {LeagueMainPageComponent} from './league-main-page/league-main-page.component';
import {AggregationSectionComponent} from "../../components/aggregation-section/aggregation-section.component";
import {UserMessageService} from "../../services/user.message.service";
import {AggregationTableComponent} from "../../components/aggregation-table/aggregation-table.component";

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    HttpModule,
    MatchRoutingModule,
    TranslateModule
  ],
  declarations: [
    HomePageComponent,
    MatchComponent,
    KeysPipe,
    ResultsTableComponent,
    PieChartComponent,
    BarChartComponent,
    MatchDetailComponent,
    LeagueMainPageComponent,
    AggregationSectionComponent,
    AggregationTableComponent
  ],
  providers: [MatchService, FixturesService, LeaguesService, UserMessageService],
})
export class MatchModule { }
