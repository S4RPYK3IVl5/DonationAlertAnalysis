import {Component, OnInit} from '@angular/core';
import {DaUserResponse} from '../../services/users/daUser/da-user-response';
import {DaUserService} from '../../services/users/daUser/da-user.service';
import {DonationService} from '../../services/daDonations/donation.service';
import {DonationsResponse} from '../../services/daDonations/donations-response';

// Тут определена логика для странице пользователя, на данный момент есть методы с AngMat так же методы тспользования сервсиов

@Component({
  selector: 'app-user',
  templateUrl: './daUser.component.html',
  styleUrls: ['./daUser.component.css']
})
export class DaUserComponent implements OnInit {

  private step = 0;
  private daUserResponse: DaUserResponse;
  private donationResponse: DonationsResponse[];
  private itemPerPage = 10;

  constructor(
    private daUserService: DaUserService,
    private donationService: DonationService
  ) { }

  ngOnInit() {
    this.daUserService.getUser().subscribe(
      data => {
        this.daUserResponse = data;
      }
    );
    this.donationService.getDonations().subscribe(
      data => {
        this.donationResponse = data;
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

  refreshInfo() {
    this.daUserService.updateDaUser().subscribe(
      data => {
        console.log(data);
        this.daUserResponse = data;
        window.location.reload();
      }
    );
  }

}
