import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {User} from '../models/User';
import {AppConst} from '../app-const';
import {Router} from "@angular/router";


@Injectable()
export class UserService {
  private serverPath:string = AppConst.serverPath;

  constructor(private http: Http, private router:Router) { }

  newUser(user: User) {
    let url = this.serverPath+"/user";
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
    });
    return this.http.post(url, user, {headers : tokenHeader});
  }

  client1SSo() {
  	let url = this.serverPath+"/client/client1SSO";
    
    return this.http.get(url, {withCredentials:true});
  }

  accessResource1() {
    
    let url = this.serverPath+"/resource/resource1";
    console.log(url);
    
    return this.http.get(url, {withCredentials:true});
  }

  forgetPassword(username: string) {
    let url = this.serverPath+"/user/forgetpassword";

    let userInfo = {
      "input": {
       "queryFilter": "uid eq "+username
     }
    };
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
    });
    return this.http.post(url, userInfo, {headers : tokenHeader});
  }

}
