import { Component, OnInit } from '@angular/core';
import {AppConst} from '../../app-const';
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";
import {UserService} from "../../services/user.service";
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private serverPath = AppConst.serverPath;
  private loggedIn = false;
  private issued_token;

  constructor (private loginService: LoginService, private userService: UserService, private router: Router){
  }

  onLogout() {
    this.loginService.logout().subscribe(
      res => {
        this.loggedIn=false;
        this.router.navigate(['/login']);
      },
      error => {
        this.loggedIn=true;
      }
    );
  }

  onClient1SSO(){
    this.userService.client1SSo().subscribe(
      res => {
        this.issued_token=res.text();
        console.log(this.issued_token);
      },
      error => {
        this.loggedIn=true;
      }
    );
  }

  ngOnInit() {
  }



}
