import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { routing }  from './app.routing';
import {BusyModule} from 'angular2-busy';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { ForgetpasswordComponent } from './components/forgetpassword/forgetpassword.component';
import { OtpComponent } from './components/otp/otp.component';
import { CodeComponent } from './components/code/code.component';
import { ProxyComponent } from './components/proxy/proxy.component';
import { Resource1Component } from './components/resource1/resource1.component';

import { LoginService } from './services/login.service';
import { UserService } from './services/user.service';
import { RegistrationService } from './services/registration.service';
import { LoginGuardService } from './services/login-guard.service';
import { OauthService } from './services/oauth.service';
import { ResourceGuardService } from './services/resource-guard.service'

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegistrationComponent,
    ForgetpasswordComponent,
    OtpComponent,
    CodeComponent,
    ProxyComponent,
    Resource1Component
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    routing,
    BusyModule
  ],
  providers: [
  	LoginService,
    UserService,
    RegistrationService,
    LoginGuardService,
    OauthService,
    ResourceGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
