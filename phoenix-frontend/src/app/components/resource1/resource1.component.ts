import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/User';

@Component({
  selector: 'app-resource1',
  templateUrl: './resource1.component.html',
  styleUrls: ['./resource1.component.css']
})
export class Resource1Component implements OnInit {

  private user: User = new User();

  constructor(private userService: UserService) { }

  ngOnInit() {
  	this.userService.getUserByCurrentSession().subscribe(
  		res => {
  			console.log(res.json());
  			this.user = res.json();
  		},
  		error => {
  			console.log(error);
  		}
  	);
  }

}
