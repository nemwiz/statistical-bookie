import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {MatchService} from '../../services/match.service';

@Component({
  selector: 'aggregated-match',
  templateUrl: './aggregated-match.component.html',
  styleUrls: ['./aggregated-match.component.scss']
})
export class AggregatedMatchComponent implements OnInit {

  homeTeam:string;
  awayTeam:string;
  aggregatedMatch: any;

  constructor(private route:ActivatedRoute,
              private matchService:MatchService) {

    route.params.subscribe(params => {
      this.homeTeam = params['homeTeam'];
      this.awayTeam = params['awayTeam'];
    });
  }

  ngOnInit() {

    this.matchService.getMatchesByTeams(this.homeTeam, this.awayTeam)
      .subscribe(aggregatedMatch => {
        this.aggregatedMatch = aggregatedMatch;
        console.log(this.aggregatedMatch);
      });
  }

}
