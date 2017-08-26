import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.scss'],
  inputs: ['isLoading']
})
export class LoaderComponent implements OnInit {

  isLoading: boolean = false;
  loadingTranslationKey: string = 'loading';

  constructor() { }

  ngOnInit() {
  }

}
