/**
 * Created by tpowell on 7/30/16.
 */
import {Component} from '@angular/core';
import {User} from  '../domain/user';
import {LoginService} from '../service/login-service';
import { Router } from '@angular/router';

@Component({
    selector: 'login-page',
    templateUrl: 'app/login/login.component.html'
})
export class Login {
    title = 'Login';
    //user = User;
    user = new User('', '');
    loginService;
    router;
    foundUser = false;
    submitted = false;
    constructor(loginService: LoginService, router: Router){
        this.loginService = loginService;
        this.router = router;
    }
    onSubmit() {

        this.foundUser = this.loginService.validateUser(this.user);
        alert(this.foundUser);

        this.submitted = true;
        if(this.foundUser){
            this.router.navigate(['success']);
        }
    }

    get diagnostic() {
        return JSON.stringify(this.user);
    }
}