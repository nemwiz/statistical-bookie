import { Component, OnInit } from '@angular/core';
import {MenuItem} from '../../../interfaces/top-navbar-menu-item';
declare var $:any;

@Component({
  selector: 'top-navbar',
  templateUrl: './top-navbar.component.html',
  styleUrls: ['./top-navbar.component.scss']
})
export class TopNavbarComponent implements OnInit {

  menuItems:MenuItem[] = [
    {name: 'Home', route:'/home'},
    {name: 'Leagues', route:'/leagues'}
  ];

  constructor() {
  }

  ngOnInit() {
    $(".button-collapse").sideNav();
  }
}
