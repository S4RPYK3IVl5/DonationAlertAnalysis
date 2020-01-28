import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DaTokenService {

  private TokenKey = 'TokenAPIKey';

  private getTokenUrl = 'http://localhost:8080/api/token';

  constructor(private httpClient: HttpClient) { }

  getToken(): Observable<string> {
    return this.httpClient.get(this.getTokenUrl, {responseType: 'text'});
  }

  saveToken(token: string) {
    window.sessionStorage.setItem(this.TokenKey, token);
  }

  getSessionToken(): string {
    return window.sessionStorage.getItem(this.TokenKey);
  }

}
