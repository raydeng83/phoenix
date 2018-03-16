import {ModuleWithProviders}  from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {LoginComponent} from './components/login/login.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {HomeComponent} from './components/home/home.component';
import {ForgetpasswordComponent} from './components/forgetpassword/forgetpassword.component';

import {LoginGuardService} from './services/login-guard.service';

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
  	path: 'home',
    component: HomeComponent,
    canActivate: [LoginGuardService]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
  	path: 'register',
  	component: RegistrationComponent
  },
  {
    path: 'forgetPassword',
    component: ForgetpasswordComponent
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);