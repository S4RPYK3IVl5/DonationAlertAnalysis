import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DaUserResponse} from './da-user-response';

@Injectable({
  providedIn: 'root'
})
export class DaUserService {

  private url = 'http://localhost:8080/api/user';

  constructor(private httpClient: HttpClient) { }

  getUser(): Observable<DaUserResponse> {
    return this.httpClient.get<DaUserResponse>(this.url);
  }

  updateDaUser(): Observable<DaUserResponse> {
    return this.httpClient.get<DaUserResponse>(this.url + '/update');
  }

}
