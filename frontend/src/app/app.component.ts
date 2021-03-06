import {Component} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';

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

    }, false);

  }

  private setLanguageForTranslations(language) {
    let languageCodeLen = language.value.length;
    let languageCode = language.value.substr(languageCodeLen - 2, languageCodeLen).toLowerCase();
    this.translationService.setDefaultLang('en');
    // the lang to use, if the lang isn't available, it will use the current loader to get them
    this.translationService.use(languageCode);
  }

}
