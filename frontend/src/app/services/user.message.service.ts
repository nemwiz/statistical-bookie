import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";
import {UserMessages} from "../common/user-messages";
import {ErrorMessage} from "../interfaces/error-message";

@Injectable()
export class UserMessageService {
  private loadingSpinnerSubject: Subject<UserMessages>;
  loadingSpinnerObservable: Observable<UserMessages>;

  private errorMessageSubject: Subject<ErrorMessage>;
  errorMessageObservable: Observable<ErrorMessage>;

  constructor() {
    this.loadingSpinnerSubject = new Subject();
    this.loadingSpinnerObservable = this.loadingSpinnerSubject.asObservable();

    this.errorMessageSubject = new Subject();
    this.errorMessageObservable = this.errorMessageSubject.asObservable();
  }


  showLoadingSpinner() {
    this.loadingSpinnerSubject.next(UserMessages.SHOW_LOADING)
  }

  hideLoadingSpinner() {
    this.loadingSpinnerSubject.next(UserMessages.HIDE_LOADING)
  }

  showErrorMessage(errorMessage: string) {
    let error = {action: UserMessages.SHOW_ERROR, message: errorMessage};
    this.errorMessageSubject.next(error)
  }

  hideErrorMessage() {
    let error = {action: UserMessages.HIDE_ERROR, message: ''};
    this.errorMessageSubject.next(error)
  }

}
