/**
 * Created by tpowell on 9/10/16.
 */
import {Component} from '@angular/core';
import {Router} from '@angular/router';

import {User} from  '../domain/user';
import {LoginService} from '../service/login-service';

@Component({
    selector: 'home-page',
    // providers: [LoginService],
    templateUrl: 'app/login/home.page.component.html'
})
export class HomePageComponent {
    title = 'Welcome to the landing page';
    user;
    router;
    hideRegistrationForm = true;


    constructor(router:Router){
             this.router = router;
    }
    
    showRegistration(){
        // //employeeRegistration
        // this.router.navigate(['employeeRegistration']);
        this.hideRegistrationForm = !this.hideRegistrationForm;
    }
}