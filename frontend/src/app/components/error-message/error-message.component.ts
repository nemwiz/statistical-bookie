import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'error-message',
  templateUrl: './error-message.component.html',
  styleUrls: ['./error-message.component.scss'],
  inputs: ['isError', 'errorMessage']
})
export class ErrorMessageComponent implements OnInit {

  isError: boolean = false;
  errorMessage: string = '';

  constructor() { }

  ngOnInit() {
  }

}
