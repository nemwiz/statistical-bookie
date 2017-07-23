import {Component} from '@angular/core';
import {Location} from '@angular/common';
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  currentRoute: string = '';

  constructor(private location: Location, private router: Router) {
    this.router.events.subscribe((route) => {
      if (route instanceof NavigationEnd) {
        this.currentRoute = (<NavigationEnd> route ).url;
      }
    });
  }

  navigateBack(): void {
    this.location.back();
  }

}
