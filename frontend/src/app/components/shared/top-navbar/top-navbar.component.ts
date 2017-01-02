import {Component, OnInit} from '@angular/core';
import {AppRoutes} from '../routes';
import {MenuItem} from '../../../interfaces/top-navbar-menu-item';

@Component({
  selector: 'top-navbar',
  templateUrl: './top-navbar.component.html',
  styleUrls: ['./top-navbar.component.scss']
})
export class TopNavbarComponent implements OnInit {

  menuItems:MenuItem[] = [];

  constructor() {
    AppRoutes.routes.forEach(route => {

      if (route.path != '') {
        this.convertRoutesToMenuItemFormat(route.path);
      }

    });
  }

  ngOnInit() {
  }

  private convertRoutesToMenuItemFormat(routeName: string) {

    this.menuItems.push({
      name: this.capitalizeFirstLetter(routeName),
      route: '/' + routeName
    })

  }

  private capitalizeFirstLetter(route:string):string {
    return route[0].toUpperCase() + route.slice(1);
  }

}
