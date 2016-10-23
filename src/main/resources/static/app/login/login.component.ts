/**
 * Created by tpowell on 7/30/16.
 */
import {Component} from '@angular/core';
import {User} from  '../domain/user';
import {LoginService} from '../service/login-service';
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
    router;
    foundUser = false;
    submitted = false;
    reply;

    constructor(loginService:LoginService, router:Router) {
        this.loginService = loginService;
        this.router = router;
    }

    login() {
        this.submitted = true;
        const response = this.loginService.validateUser(this.user.username, this.user.password);
        response.subscribe(
            reply => {
                if (reply.status == 200) {
                    this.foundUser = true;
                    localStorage.setItem('auth_token', reply.auth_token);
                    this.loginService.setLoggedIn(true);
                    this.router.navigate(['success']);
                }
            },
            () => {
            },
            () => console.log("Processing complete")
        );
    }




    get diagnostic() {
        return JSON.stringify(this.user);
    }
}