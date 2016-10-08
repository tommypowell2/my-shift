/**
 * Created by tpowell on 9/4/16.
 */
import {Injectable} from '@angular/core';
import {Http, Headers, Response} from '@angular/http';
import {Logger} from "../util/logger.service";
import { Observable } from "rxjs/Rx";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
declare function  fetch(url:string);

@Injectable()
export class LoginService {

    private loggedIn = false;

    constructor(private logger:Logger, private http:Http) {
        this.logger = logger;
        this.loggedIn = !!localStorage.getItem('auth_token');
    }

    // validateUser(username:string, password:string) {
    //
    //             alert(res.success);
    //             this.loggedIn = true;
    //             return res.success;
    //         });
    // }

    validateUser(username:string, password:string) {
        this.loggedIn = false;
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        const response =  this.http
            .post(
                '/login2',
                JSON.stringify({username, password}),
                {headers}
            )
            .map((res:Response) => res.json());
        return response;
    }

    setLoggedIn(loggedIn:boolean){
        this.loggedIn = loggedIn;
    }

    isLoggedIn() {
        return this.loggedIn;
    }
}
