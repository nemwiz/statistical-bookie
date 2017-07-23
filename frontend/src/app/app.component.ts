import {Component} from '@angular/core';
import {Location} from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private location: Location) {
  }

  navigateBack(): void {
    this.location.back();
  }

}
