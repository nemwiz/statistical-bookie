import {Component} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {environment} from "../environments/environment";

declare var AdMob: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private translationService: TranslateService) {

    document.addEventListener('deviceready', () => {

      navigator['splashscreen'].hide();

      if (navigator && navigator['globalization']) {
        navigator['globalization'].getPreferredLanguage((language) => {
          this.setLanguageForTranslations(language);
        }, (error) => {
          console.log('Error loading system language');
        });
      }

      this.connectToAdMob();


    }, false);

  }

  private connectToAdMob() {
    let admobid = {banner: environment.adMobId};

    if (AdMob) {
      AdMob.createBanner({
        adId: admobid.banner,
        position: AdMob.AD_POSITION.BOTTOM_CENTER,
        isTesting: !environment.production
      });
    }
  }

  private setLanguageForTranslations(language) {
    let languageCode = language.value.substr(0, 2);
    this.translationService.setDefaultLang(languageCode);
    // the lang to use, if the lang isn't available, it will use the current loader to get them
    this.translationService.use('en');
  }

}
