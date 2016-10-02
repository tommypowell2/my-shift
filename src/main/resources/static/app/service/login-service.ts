/**
 * Created by tpowell on 9/4/16.
 */
import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {User} from "../domain/user";
import {Logger} from "../util/logger.service";
import { Observable } from "rxjs/Rx";
import 'rxjs/add/operator/map';

@Injectable()
export class LoginService {

    private loggedIn = false;

    constructor(private logger:Logger, private http:Http) {
        this.logger = logger;
        this.loggedIn = !!localStorage.getItem('auth_token');
    }

    validateUser(username:string, password:string) {
        this.loggedIn = false;
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http
            .post(
                '/login2',
                JSON.stringify({username, password}),
                {headers}
            )
            .map(res => res.json())
            .map((res) => {
                if (res.success) {
                    alert(res.success);

                    localStorage.setItem('auth_token', res.auth_token);
                    this.loggedIn = true;
                } else {
                    alert('fail')
                }

                alert(res.success);
                this.loggedIn = true;
                return res.success;
            });
    }

    isLoggedIn() {
        return this.loggedIn;
    }
}
