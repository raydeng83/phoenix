import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { UserService } from './user.service';
import {Router} from "@angular/router";

@Injectable()
export class ResourceGuardService implements CanActivate{
  private accessible: boolean = false;
  constructor(private userService: UserService, private router: Router) { }

  canActivate() {
  	let res = this.userService.accessResource1();
  	res.subscribe (
  		res => {
  			console.log(res)
  		},
  		error => {
  			this.router.navigate(['/home']);
  		}
  	);
  	return res;
  }
}
