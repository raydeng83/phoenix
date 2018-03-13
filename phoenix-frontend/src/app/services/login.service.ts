import { Injectable } from '@angular/core';
import {AppConst} from '../app-const';
import {Http, Headers} from '@angular/http';
import {Router} from "@angular/router";


@Injectable()
export class LoginService {
  private serverPath:string = AppConst.serverPath;

  constructor (private http: Http, private router:Router) {}

  // sendCredential(username: string, password: string) {
  //   let url = this.serverPath+"/authenticate";
  //   let userInfo = {
  //   	"username" : username,
  //   	"password" : password
  //   };
  //   let headers = new Headers(
  //     {
  //       'Content-Type': 'application/json',

  //     });
  //   return this.http.post(url, userInfo, {headers: headers});
  // }

  // sendCredential(username: string, password: string) {
  //   let url = this.serverPath+"/login";
  //   let encodedCredentials = btoa(username+":"+password);
  //   let basicHeader = "Basic "+ encodedCredentials;
  //   let headers = new Headers(
  //     {
  //       'Content-Type': 'application/x-www-form-urlencoded',
  //       'Authorization' : basicHeader

  //       // 'Access-Control-Allow-Credentials' : true
  //     });
  //   return this.http.get(url, {headers: headers});
  // }

  sendCredential(username: string, password: string) {
    let url = this.serverPath+"/login";
    
    let headers = new Headers(
      {
        'Content-Type': 'application/json',

        // 'Access-Control-Allow-Credentials' : true
      });

    let data = {"username" : username, "password" : password};

    return this.http.post(url, JSON.stringify(data), {headers: headers});
  }

  checkSession() {
    let url = this.serverPath+"/checkSession";
    let headers = new Headers(
      {
        'Accept': 'application/json',
      });
    
    return this.http.get(url, {headers : headers});
  }

  logout() {
    let url = this.serverPath+"/user/logout";
    let tokenHeader = new Headers ({
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.post(url,'', {headers : tokenHeader});
  }

}
