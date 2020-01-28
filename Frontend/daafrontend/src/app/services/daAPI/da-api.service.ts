import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DaAPIService {

  urlCode = 'http://localhost:8080/api/oauth/code?code=';

  constructor(private httpClient: HttpClient) { }

  codeConsumption(code: string) {
    return this.httpClient.post(this.urlCode, code);
  }

}
