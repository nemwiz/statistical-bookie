import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {HomePageComponent} from "./home-page/home-page.component";
import {MatchComponent} from "./match/match.component";
import {KeysPipe} from "../../common/keys-pipe";
import {ResultsTableComponent} from "../../components/results-table/results-table.component";
import {MatchDetailComponent} from "./match-detail/match-detail.component";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {MatchRoutingModule} from "./match-routing.module";
import {MatchService} from "../../services/match.service";
import {FixturesService} from "../../services/fixtures.service";
import {TranslateModule} from '@ngx-translate/core';
import {LeaguesService} from "../../services/leagues.service";
import {LeagueMainPageComponent} from './league-main-page/league-main-page.component';
import {AggregationSectionComponent} from "../../components/aggregation-section/aggregation-section.component";
import {UserMessageService} from "../../services/user.message.service";
import {AggregationTableComponent} from "../../components/aggregation-table/aggregation-table.component";
import {BarChartComponent} from "../../components/bar-chart/bar-chart.component";

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
    MatchDetailComponent,
    LeagueMainPageComponent,
    AggregationSectionComponent,
    AggregationTableComponent,
    BarChartComponent
  ],
  providers: [MatchService, FixturesService, LeaguesService, UserMessageService],
})
export class MatchModule { }
