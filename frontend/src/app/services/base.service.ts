import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import {Observable} from 'rxjs/Observable';
// Operators
import 'rxjs/add/operator/map';

let BACKEND_API: string = 'http://localhost:5000/';

@Injectable()
export abstract class BaseService {

  constructor(private http: Http) {

  }

  get<T>(resource: string): Observable<T> {
    let query = BACKEND_API + resource;
    //noinspection TypeScriptValidateTypes
    return this.http.get(query)
      .map((response: Response) => {
        return <T> response.json();
      });
  }


}
