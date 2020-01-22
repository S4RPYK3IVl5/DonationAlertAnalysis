import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MaterialModule } from './any/material/material.module';
import {FlexLayoutModule} from '@angular/flex-layout';
import {RoutingModule} from './any/routing/routing.module';
import { DaUserComponent } from './components/daUser/daUser.component';
import { HttpClientModule } from '@angular/common/http';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatCardModule} from '@angular/material/card';
import { LoginComponent } from './components/authComponents/login/login.component';
import { RegisterComponent } from './components/authComponents/register/register.component';
import { MainComponent } from './components/main/main.component';
import { UserComponent } from './components/checkUserAuthotitiesComponents/user/user.component';
import { PmComponent } from './components/checkUserAuthotitiesComponents/pm/pm.component';
import { AdminComponent } from './components/checkUserAuthotitiesComponents/admin/admin.component';
import {FormsModule} from '@angular/forms';
import {httpInterceptorProviders} from './services/auth/auth-interceptor';
import {MatToolbarModule} from '@angular/material/toolbar';
import { HeaderComponent } from './components/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    DaUserComponent,
    LoginComponent,
    RegisterComponent,
    MainComponent,
    UserComponent,
    PmComponent,
    AdminComponent,
    HeaderComponent,

  ],
  imports: [
    BrowserModule,
    NoopAnimationsModule,
    MatMenuModule,
    MatButtonModule,
    MatSidenavModule,
    BrowserModule,
    MaterialModule,
    FlexLayoutModule,
    RoutingModule,
    MatExpansionModule,
    MatCardModule,
    HttpClientModule,
    FormsModule,
    MatToolbarModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
