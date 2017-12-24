import {Component, OnInit} from '@angular/core';
import {UserMessageService} from "../../services/user.message.service";
import {UserMessages} from "../../common/user-messages";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'user-message',
  templateUrl: './user-message.component.html',
  styleUrls: ['./user-message.component.scss']
})
export class UserMessageComponent implements OnInit {

  showLoadingSpinner: boolean = true;
  showErrorMessage: boolean = false;
  errorMessage: string;
  loadingTranslationKey: string = 'loading';

  constructor(private userMessageService: UserMessageService,
              private router: Router) {
    this.userMessageService.loadingSpinnerObservable
      .subscribe(message => this.showLoadingSpinner = message === UserMessages.SHOW_LOADING)

    this.userMessageService.errorMessageObservable.subscribe((errorMessage) => {
      this.showErrorMessage = errorMessage.action === UserMessages.SHOW_ERROR;

      if (errorMessage.message !== '') {
        this.errorMessage = errorMessage.message;
      }

      if (this.showErrorMessage) {
        this.showLoadingSpinner = false;
      }

    });

    this.router.events
      .filter((event) => event instanceof NavigationEnd)
      .subscribe(() => {
      if (this.showErrorMessage) {
        this.showErrorMessage = false;
      }
      });
  }

  ngOnInit() {
  }

}
