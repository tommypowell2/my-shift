/**
 * Created by tpowell on 7/30/16.
 */
import {Component} from '@angular/core';
import {User} from  '../domain/user';
import {LoginService} from '../service/login-service';

@Component({
    selector: 'login-page',
    providers: [LoginService],
    templateUrl: 'app/login/login.component.html'
})
export class Login {
    title = 'Login';
    //user = User;
    user = new User('', '');
    loginService;
    foundUser = false;
    submitted = false;
    constructor(loginService: LoginService){
        this.loginService = loginService;
    }
    onSubmit() {
        //this.user.userName = 'sadsds';
        this.foundUser = this.loginService.validateUser(this.user);
        this.submitted = true;
    }

    get diagnostic() {
        return JSON.stringify(this.user);
    }
}