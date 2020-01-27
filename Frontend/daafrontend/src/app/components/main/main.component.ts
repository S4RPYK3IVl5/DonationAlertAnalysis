import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../../services/auth/token-storage.service';
import {Router} from '@angular/router';
import {DaTokenService} from '../../services/daToken/da-token.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  info: any;
  url = 'https://www.donationalerts.com/oauth/authorize?client_id=' + 341
    + '&redirect_uri=' + 'http://localhost:4200/code'
    + '&response_type=code&scope=' + 'oauth-user-show%20oauth-donation-subscribe%20oauth-donation-index';

  constructor(private token: TokenStorageService, private router: Router, private daTokenService: DaTokenService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities(),
      daToken: null
    };

    this.daTokenService.getToken().subscribe(
      data => {
        console.log(data);
        this.info.daToken = data;
        this.daTokenService.saveToken(this.info.daToken);
      },
      error => {
        console.log(error);
        this.info.daToken = error.error.text;
        this.daTokenService.saveToken(this.info.daToken);
      }
    );

    console.log(this.info.daToken);

    if (this.info.token == null) {
      this.router.navigate(['/auth/login']);
    }
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }

}
