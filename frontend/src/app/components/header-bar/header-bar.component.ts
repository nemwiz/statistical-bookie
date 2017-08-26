import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'header-bar',
  templateUrl: './header-bar.component.html',
  styleUrls: ['./header-bar.component.scss']
})
export class HeaderBarComponent implements OnInit {

  currentRoute: string = '';

  constructor(private router: Router,
              private location: Location) {
    this.router.events.subscribe((route) => {
      if (route instanceof NavigationEnd) {
        this.currentRoute = (<NavigationEnd> route ).url;
      }
    });
  }

  ngOnInit() {
  }

  navigateBack(): void {
    this.location.back();
  }

}
