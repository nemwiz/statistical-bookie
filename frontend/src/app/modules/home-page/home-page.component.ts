import { Component, OnInit } from '@angular/core';
declare var jQuery: any;

@Component({
  selector: 'home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    jQuery('.ui.search').search({
        source: [
          { title: 'Andorra' },
          { title: 'United Arab Emirates' },
          { title: 'Afghanistan' },
          { title: 'Antigua' },
          { title: 'Anguilla' },
          { title: 'Albania' },
          { title: 'Armenia' },
          { title: 'Netherlands Antilles' },
          { title: 'Angola' },
          { title: 'Argentina' },
          { title: 'American Samoa' },
          { title: 'Austria' },
          { title: 'Australia' },
          { title: 'Aruba' },
          { title: 'Aland Islands' },
          { title: 'Azerbaijan' },
          { title: 'Bosnia' },
          { title: 'Barbados' },
          { title: 'Bangladesh' },
          { title: 'Belgium' },
          { title: 'Burkina Faso' },
          { title: 'Bulgaria' },
          { title: 'Bahrain' },
          { title: 'Burundi' }
        ]
      })
    ;
  }

}
