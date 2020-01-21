import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MaterialModule } from './material/material.module';
import { LayoutComponent } from './layout/layout.component';
import {FlexLayoutModule} from '@angular/flex-layout';
import { HomeComponent } from './home/home.component';
import {RoutingModule} from './routing/routing.module';
import { HeaderComponent } from './navigation/header/header.component';
import { DaUserComponent } from './daUser/daUser.component';
import { HttpClientModule } from '@angular/common/http';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatCardModule} from '@angular/material/card';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MainComponent } from './main/main.component';
import { UserComponent } from './user/user.component';
import { PmComponent } from './pm/pm.component';
import { AdminComponent } from './admin/admin.component';

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    HomeComponent,
    HeaderComponent,
    DaUserComponent,
    LoginComponent,
    RegisterComponent,
    MainComponent,
    UserComponent,
    PmComponent,
    AdminComponent,

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
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
