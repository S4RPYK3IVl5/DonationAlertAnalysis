import { Injectable } from '@angular/core';
import { BackendService } from '../backend-service/backend.service';

// Сервис для получения данных и хранения данных о пользователе DA

@Injectable({
  providedIn: 'root'
})
export class DaUserService {

  public name: string;
  public avatar: string;

  constructor(
    private backend: BackendService,
  ) { }

  getUser(id: string){
    this.backend.getUser(id).subscribe( (data: DaUserService) => {
      this.name = data.name;
      this.avatar = data.avatar;
    });
  }

}
