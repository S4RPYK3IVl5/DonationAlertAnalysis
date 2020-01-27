import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { DaUserComponent } from '../../components/daUser/daUser.component';
import {MainComponent} from '../../components/main/main.component';
import {UserComponent} from '../../components/checkUserAuthotitiesComponents/user/user.component';
import {PmComponent} from '../../components/checkUserAuthotitiesComponents/pm/pm.component';
import {AdminComponent} from '../../components/checkUserAuthotitiesComponents/admin/admin.component';
import {LoginComponent} from '../../components/authComponents/login/login.component';
import {RegisterComponent} from '../../components/authComponents/register/register.component';
import {CodeConsumptionComponent} from '../../components/code-consumtion/code-consumption.component';

// Тут добавил роут на daUser с прараметром в пути в виде id пользователя в da
const routes: Routes = [
  { path: 'dauser', component: DaUserComponent },
  {path: 'code', component: CodeConsumptionComponent},
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
