import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { DaUserComponent } from '../daUser/daUser.component';
import {MainComponent} from '../main/main.component';
import {UserComponent} from '../user/user.component';
import {PmComponent} from '../pm/pm.component';
import {AdminComponent} from '../admin/admin.component';
import {LoginComponent} from '../login/login.component';
import {RegisterComponent} from '../register/register.component';

// Тут добавил роут на daUser с прараметром в пути в виде id пользователя в da
const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'user/:id', component: DaUserComponent },
  {
    path: 'main',
    component: MainComponent
  },
  {
    path: 'user',
    component: UserComponent
  },
  {
    path: 'pm',
    component: PmComponent
  },
  {
    path: 'admin',
    component: AdminComponent
  },
  {
    path: 'auth/login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: RegisterComponent
  },
  {
    path: '',
    redirectTo: 'main',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class RoutingModule { }
