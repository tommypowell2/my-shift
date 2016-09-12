/**
 * Created by tpowell on 9/4/16.
 */
import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
// import localStorage from 'localStorage';
import { USERS }     from './mock-users';
import {User} from "../domain/user";
import {Logger} from "../util/logger.service";

@Injectable()
export class LoginService {

    private loggedIn = false;
    constructor(private logger: Logger, private http: Http){
        this.logger = logger;
        this.loggedIn = !!localStorage.getItem('auth_token');
    }

    validateUser(user: User) {
        this.loggedIn = false;
        for(var userKey in USERS){
            var userFromList = USERS[userKey];
            if(user.username == userFromList.username && user.password == userFromList.password){
                this.loggedIn= true;
                localStorage.setItem('auth_token', 'token');
                this.logger.log('Login attempt succeeded  for '+user.username);
            }
        }
        if(!this.loggedIn){
            this.logger.log('Login attempt failed for '+user.username);
        }
        return this.loggedIn;
    }

    isLoggedIn() {
        return this.loggedIn;
    }
}
