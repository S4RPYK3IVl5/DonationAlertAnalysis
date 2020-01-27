import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../../services/auth/token-storage.service';
import {DaTokenService} from '../../services/daToken/da-token.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private title = 'Donation Alerts Analysis';
  private daToken = null;
  private roles: string[];
  private authority: string;

  constructor(private tokenStorage: TokenStorageService, private daTokenService: DaTokenService) {}

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every( role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'ROLE_PM') {
          this.authority = 'pm';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
    if (this.daTokenService.getSessuinToken() != null) {
      this.daToken = this.daTokenService.getSessuinToken();
    }
  }

}
