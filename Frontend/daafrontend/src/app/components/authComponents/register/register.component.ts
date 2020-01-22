import {Component, OnInit} from '@angular/core';
import {SignupInfo} from '../../../services/auth/signup-info';
import {AuthService} from '../../../services/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {};
  signupInfo: SignupInfo;
  isSignedUp = false;
  isSignedUpFailed = false;
  errorMessage = '';

  constructor( private authService: AuthService) { }

  ngOnInit() {
  }

  onSubmit() {

    this.signupInfo = new SignupInfo(
      this.form.name,
      this.form.username,
      this.form.email,
      this.form.password
    );

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        this.isSignedUp = true;
        this.isSignedUpFailed = false;
      },
      error => {
        this.errorMessage = error.error.message;
        this.isSignedUpFailed = true;
      }
    );

  }

}
