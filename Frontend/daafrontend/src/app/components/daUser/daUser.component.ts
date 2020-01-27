import {Component, OnInit} from '@angular/core';
import {DaUserResponse} from '../../services/users/daUser/da-user-response';
import {DaUserService} from '../../services/users/daUser/da-user.service';

// Тут определена логика для странице пользователя, на данный момент есть методы с AngMat так же методы тспользования сервсиов

@Component({
  selector: 'app-user',
  templateUrl: './daUser.component.html',
  styleUrls: ['./daUser.component.css']
})
export class DaUserComponent implements OnInit {

  private step = 0;
  private daUserResponse: DaUserResponse;

  constructor(
    private daUserService: DaUserService
  ) { }

  ngOnInit() {
    this.daUserService.getUser().subscribe(
      data => {
        this.daUserResponse = data;
      }
    );
  }

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }

}
