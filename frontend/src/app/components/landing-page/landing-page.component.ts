import {Component, OnInit} from '@angular/core';
import {MatchService} from '../../services/match.service';

@Component({
  selector: 'landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {
  

  constructor(private matchService: MatchService) {
  }

  ngOnInit() {
  }

}
