import {Component, OnInit} from '@angular/core';
import {DaUserResponse} from '../../services/users/daUser/da-user-response';
import {DaUserService} from '../../services/users/daUser/da-user.service';
import {DonationService} from '../../services/daDonations/donation.service';
import {DonationsResponse} from '../../services/daDonations/donations-response';
import {TokenStorageService} from '../../services/auth/token-storage.service';
import {Router} from '@angular/router';
import {PageEvent} from '@angular/material/paginator';

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
  private donationDisplay: DonationsResponse[];
  private pageSizeOptions = [5, 10, 25, 50];
  private pageEvent = new PageEvent();
  private pageIndex = 0;
  private itemPerPage = 10;

  constructor(
    private daUserService: DaUserService,
    private donationService: DonationService,
    private token: TokenStorageService,
    private router: Router,
  ) { }

  ngOnInit() {
    console.log(this.token.getToken());
    if (this.token.getToken() == null) {
      this.router.navigate(['/auth/login']);
    }
    this.daUserService.getUser().subscribe(
      data => {
        this.daUserResponse = data;
      }
    );
    this.donationService.getDonations().subscribe(
      data => {
        this.donationResponse = data;
        this.writeDonates();
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

  changePage() {
    if (this.pageEvent.pageSize === undefined
      && this.pageIndex === this.pageEvent.pageIndex
      && this.itemPerPage === this.pageEvent.pageSize) {
      return;
    }
    this.pageIndex = this.pageEvent.pageIndex;
    if (this.itemPerPage !== this.pageEvent.pageSize) {
      this.itemPerPage = this.pageEvent.pageSize;
      this.pageIndex = 0;
      this.pageEvent.pageIndex = 0;
    }
    this.writeDonates();
  }

  writeDonates() {
    this.donationDisplay = [];
    for (let i = this.pageIndex * this.itemPerPage; i < (this.pageIndex + 1) * this.itemPerPage; i++) {
      if (i === this.donationResponse.length) {
        break;
      }
      this.donationDisplay.push(this.donationResponse[i]);
    }
  }

  onChange() {
    console.log('here');
  }
}
