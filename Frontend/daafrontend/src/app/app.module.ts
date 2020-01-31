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
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDividerModule} from '@angular/material/divider';
import { CodeConsumptionComponent } from './components/code-consumtion/code-consumption.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatListModule} from '@angular/material/list';
import {MatPaginatorModule} from '@angular/material/paginator';

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
    CodeConsumptionComponent
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
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    MatDividerModule,
    MatGridListModule,
    MatListModule,
    MatPaginatorModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
