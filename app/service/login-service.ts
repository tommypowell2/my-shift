/**
 * Created by tpowell on 9/4/16.
 */
import { Injectable } from '@angular/core';
import { USERS }     from './mock-users';
import {User} from "../domain/user";
import {Logger} from "../util/logger.service";

@Injectable()
export class LoginService {
    constructor(private logger: Logger){
        this.logger = logger;
    }

    validateUser(user:User) {
        var foundUSer = false;
        for(var userKey in USERS){
            var userFromList = USERS[userKey];
            if(user.username == userFromList.username && user.password == userFromList.password){
                foundUSer = true;
                this.logger.log('Login attempt succeeded  for '+user.username);
            }
        }
        if(!foundUSer){
            this.logger.log('Login attempt failed for '+user.username);
        }
        return foundUSer;
    }
}
