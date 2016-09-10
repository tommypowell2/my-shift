/**
 * Created by tpowell on 9/10/16.
 */
import {Component} from '@angular/core';
import {User} from  '../domain/user';
import {LoginService} from '../service/login-service';
// import { Injectable } from '@angular/core';


@Component({
    selector: 'home-page',
    // providers: [LoginService],
    templateUrl: 'app/login/home.page.component.html'
})
// @Injectable()
export class HomePageComponent {
    title = 'Welcome to the landing page';
    user;

    // constructor(user: User){
    //     this.user = user;
    //     this.title = 'Welcome ';
    // }
}