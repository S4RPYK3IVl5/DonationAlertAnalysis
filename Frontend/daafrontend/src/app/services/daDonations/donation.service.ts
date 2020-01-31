import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DonationsResponse} from './donations-response';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DonationService {

  url = 'http://localhost:8080/api/donations';

  constructor(private httpClient: HttpClient) { }

  getDonations(): Observable<DonationsResponse[]> {
    return this.httpClient.get<DonationsResponse[]>(this.url);
  }

}
