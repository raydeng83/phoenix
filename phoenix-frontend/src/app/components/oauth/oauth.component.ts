import { Component, OnInit } from '@angular/core';
import { OauthService } from '../../services/oauth.service';
import { LoginService } from '../../services/login.service';


@Component({
  selector: 'app-oauth',
  templateUrl: './oauth.component.html',
  styleUrls: ['./oauth.component.css']
})
export class OauthComponent implements OnInit {
  private info={};

  constructor(private oauthService:OauthService, private loginService:LoginService) { }

  getCurrentUrlParameter(){
  	let currentUrl = window.location.href;
    let url = new URL(currentUrl);
    let scope = url.searchParams.get("scope");
    let code = url.searchParams.get("code");
    let iss = url.searchParams.get("iss");
    let client_id = url.searchParams.get("client_id");

    console.log(url);
    console.log(scope);
    console.log(code);
    console.log(iss);
    console.log(client_id);

    this.oauthService.getOauthAccessToken(code).subscribe(
    	res => {
    		let accessToken = res.json().access_token;

    		console.log(accessToken);

    		this.loginService.oauthSsoClient1(accessToken).subscribe(
    			res => {
    				this.info=res.json();
    				console.log(this.info);
    			},
    			error => {
    				console.log(error);
    			}
    		);
    	}, 
    	error => {
    		console.log(error);
    	}
    );
  }

  ngOnInit() {
  	this.getCurrentUrlParameter();
  }

}
