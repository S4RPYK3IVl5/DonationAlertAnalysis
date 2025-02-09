import {Component, OnInit} from '@angular/core';
import {AuthLoginInfo} from '../../../services/auth/login-info';
import {AuthService} from '../../../services/auth/auth.service';
import {TokenStorageService} from '../../../services/auth/token-storage.service';
import {Router} from '@angular/router';
import {delay} from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  hide = true;

  form: any = {};
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.reloadPage();
    }
  }

  onSubmit() {

    this.loginInfo = new AuthLoginInfo(
      this.form.username,
      this.form.password
    );

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isLoginFailed = false;
        this.roles = this.tokenStorage.getAuthorities();
        this.reloadPage();
      },
      error => {
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      });
  }

  private reloadPage() {
    this.router.navigate(['/']).then(async () => {
      window.location.reload();
    });
  }
}
