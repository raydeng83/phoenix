import { Component, OnInit } from '@angular/core';
import {OauthService} from '../../services/oauth.service';

@Component({
  selector: 'app-proxy',
  templateUrl: './proxy.component.html',
  styleUrls: ['./proxy.component.css']
})
export class ProxyComponent implements OnInit {

  constructor(
    private oauthService: OauthService
    ) { }

  getCurrentUrlParameter() {
  	let currentUrl = window.location.href;
  	let url = new URL(currentUrl);
	  let state = url.searchParams.get("state");
	  let code = url.searchParams.get("code");
	  let session_state = url.searchParams.get("session_state");
    let authId = localStorage.getItem("authId");
	  console.log(state);
	  console.log(code);
	  console.log(session_state);
    console.log(authId);

    this.oauthService.getSession(authId, code, session_state, state).subscribe(
      res => {
        console.log(res);
      },
      error => {
        console.log(error);
      }
    );


	// let newUrl = "http://openam.example.com:18080/openam/XUI/?realm=/phoenix-dev&service=GoogleSocialAuthenticationService&=&authIndexType=service&authIndexValue=GoogleSocialAuthenticationService&state="+state+"&code="+code+"&authuser=0&session_state="+session_state+"&prompt=none&goto=http://localhost:4200";

 //  let newUrl = "http://phoenix-dev.example.com:18080/openam/?service=GoogleSocialAuthenticationService&=&authIndexType=service&authIndexValue=GoogleSocialAuthenticationService&state="+state+"&code="+code+"&authuser=0&session_state="+session_state+"&prompt=none";

	// console.log(newUrl);

	// window.location.href = newUrl;
  }

  ngOnInit() {
  	this.getCurrentUrlParameter();
  }

}
