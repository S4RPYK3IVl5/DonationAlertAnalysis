import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

// Тут определена логика для странице пользователя, на данный момент есть методы с AngMat так же методы тспользования сервсиов

@Component({
  selector: 'app-user',
  templateUrl: './daUser.component.html',
  styleUrls: ['./daUser.component.css']
})
export class DaUserComponent implements OnInit {

  private step = 0;
  private id: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {
    // this.id = this.route.snapshot.paramMap.get('id');
    // this.daUser.getUser(this.id);
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
