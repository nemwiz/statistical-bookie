import {Component} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {


  constructor(private translationService: TranslateService) {

    this.translationService.setDefaultLang('en');

    // the lang to use, if the lang isn't available, it will use the current loader to get them
    this.translationService.use('rs');
  }

  changeLanguage(): void {
    if (this.translationService.currentLang === 'en') {
      this.translationService.use('rs');
    } else {
      this.translationService.use('en');
    }
  }

}
