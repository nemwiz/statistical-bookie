import {Component} from '@angular/core';
import {Location} from '@angular/common';
import {NavigationEnd, Router} from "@angular/router";
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  currentRoute: string = '';

  constructor(private location: Location,
              private router: Router,
              private translationService: TranslateService) {
    this.router.events.subscribe((route) => {
      if (route instanceof NavigationEnd) {
        this.currentRoute = (<NavigationEnd> route ).url;
      }
    });

    this.translationService.setDefaultLang('en');

    // the lang to use, if the lang isn't available, it will use the current loader to get them
    this.translationService.use('rs');
  }

  navigateBack(): void {
    this.location.back();
  }

  changeLanguage(): void {
    if (this.translationService.currentLang === 'en') {
      this.translationService.use('rs');
    } else {
      this.translationService.use('en');
    }
  }

}
