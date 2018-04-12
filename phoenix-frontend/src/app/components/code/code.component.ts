import { Component, OnInit } from '@angular/core';
import {AppConst} from '../../app-const';
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";
import {UserService} from "../../services/user.service";
import {OauthService} from '../../services/oauth.service';

@Component({
  selector: 'app-code',
  templateUrl: './code.component.html',
  styleUrls: ['./code.component.css']
})
export class CodeComponent implements OnInit {

  constructor (
    private loginService: LoginService, 
    private userService: UserService, 
    private router: Router,
    private oauthService: OauthService
    ){
  }

  onFetechInfo() {
  	let currentUrl = window.location.href;
  	let url = new URL(currentUrl);
	  let code = url.searchParams.get("code");
	  console.log(code);

    this.oauthService.getAccessToken(code).subscribe(
      res => {
        console.log(res.json());
        let tokenInfo = res.json();
        let idToken = tokenInfo["id_token"];
        let base64Url = idToken.split('.')[1];
        let base64 = base64Url.replace('-', '+').replace('_', '/');
        let userInfo = JSON.parse(window.atob(base64));
        console.log(userInfo);
      },
      error => {
        console.log(error);
      }
    );
	
  }

  ngOnInit() {
  	this.onFetechInfo();
  }

}
