/**
 * Created by tpowell on 7/30/16.
 */
import {Component} from '@angular/core';
import {User} from  '../domain/user';
import {LoginService} from '../service/login-service';
import {RegistrationService} from '../service/registration-service';
import {Router} from '@angular/router';
import {logInfo} from "typings/dist/support/cli";


@Component({
    selector: 'login-page',
    templateUrl: 'app/login/login.component.html'
})
export class Login {
    title = 'Login';
    user = new User('', '');
    loginService;
    registrationService;
    router;
    foundUser = false;
    submitted = false;
    reply;

    constructor(loginService:LoginService, registrationService:RegistrationService, router:Router) {
        this.loginService = loginService;
        this.registrationService = registrationService;
        this.router = router;
    }

    login() {
        this.submitted = true;
        const response = this.loginService.validateUser(this.user.username, this.user.password);
        response.subscribe(
            reply => {
                if (reply.status == 200) {
                    this.foundUser = true;
                    localStorage.setItem('username', this.user.username);
                    localStorage.setItem('userrole', reply.json().roles);
                    console.log(reply.json().roles)
                    this.loginService.setLoggedIn(true);
                    this.router.navigate(['success/'+this.user.username]);
                }
            },
            () => {
            },
            () => console.log("Processing complete")
        );
    }

    showSchedule(){
        this.router.navigate(['employeeSchedule']);
    }

    registerAdmin(){
        this.router.navigate(['registerAdmin']);
    }




    get diagnostic() {
        return JSON.stringify(this.user);
    }
}