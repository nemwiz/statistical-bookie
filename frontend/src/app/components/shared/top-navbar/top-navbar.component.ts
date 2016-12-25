import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'top-navbar',
  templateUrl: './top-navbar.component.html',
  styleUrls: ['./top-navbar.component.scss']
})
export class TopNavbarComponent implements OnInit {

  menuItems: string[] = [
    'About',
    'Services',
    'Contact'
  ];
  
  constructor() { }

  ngOnInit() {
  }

}
