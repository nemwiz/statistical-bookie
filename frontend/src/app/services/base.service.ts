import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import {Observable} from 'rxjs/Observable';
import { environment } from '../../environments/environment';

// Operators
import 'rxjs/add/operator/map';

let API: string = '/api/';

@Injectable()
export abstract class BaseService {

  constructor(private http: Http) {

  }

  get<T>(resource: string): Observable<T> {
    let query = `${environment.apiUrl}${API}${resource}`;
    //noinspection TypeScriptValidateTypes

    // TODO - Handle other error codes e.g. 500
    return this.http.get(query)
      .map((response: Response) => {
        return <T> response.json();
      });
  }


}
