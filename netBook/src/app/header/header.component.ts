import { Component, OnInit } from '@angular/core';
import {UserService} from "../_services/user.service";
import {User} from "../_models/User";
//import {AuthenticationService} from "../_services/authentication.service";
import {ActivatedRoute} from "@angular/router";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {



  returnUrl: string;
  user : User = new User();

  constructor(//private authenticationService: AuthenticationService,
              private userService: UserService,
              private route: ActivatedRoute,) { }

  ngOnInit() {
    // reset login status
   // this.authenticationService.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  register(event) {
    event.preventDefault();
    const target = event.target;
    const firstname = target.querySelector('#name').value;
    const secondname = target.querySelector('#lname').value;
    const username = target.querySelector('#username').value;
    const password = target.querySelector('#passwords').value;

    this.user.firstName = firstname;
    this.user.lastName = secondname;
    this.user.username = username;
    this.user.password = password;

    this.userService.create(this.user);

  }


  login(event){
    event.preventDefault();
    const target = event.target;
    const username = target.querySelector('#lnickname').value;
    const password = target.querySelector('#lpasswords').value;


    this.user.username = username;
    this.user.password = password;

    //this.authenticationService.login(this.user.username, this.user.password);
  }

  openForm() {
    document.getElementById('myForm').style.display = 'block';
    document.getElementById('myForm2').style.display = 'none';
  }

  openForm2() {
    document.getElementById('myForm2').style.display = 'block';
    document.getElementById('myForm').style.display = 'none';
  }

  closeForm2() {
    document.getElementById('myForm2').style.display = 'none';
  }

  closeForm() {
    document.getElementById('myForm').style.display = 'none';
  }

  openContent() {
    // заменить позже, когда будет взаимодействие с бэком
    document.getElementById('WrapperContent').style.display = 'grid';
    document.getElementById('Welcome').style.display = 'none';
    document.getElementById('buttons-grid').style.display = 'none';
    document.getElementById('auth').style.display = 'grid';
  }




}
