import { Component } from '@angular/core';
import { MenuItem } from './interfaces/top-navbar-menu-item';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  menuItems:MenuItem[] = [
    {name: 'Home', route:'/home'},
    {name: 'Leagues', route:'/leagues'}
  ];
}
