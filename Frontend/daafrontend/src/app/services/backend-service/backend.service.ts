import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DaUserService} from '../users/da-user-service/da-user.service';

// Сервис для послания запросов на бэк

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private daUser: DaUserService;

  constructor(
    private httpClient: HttpClient
  ) { }

  getUser(id: string) {
    return this.httpClient.get('http://localhost:8080/api/user?id=' + id);
  }

}
