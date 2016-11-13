/**
 * Created by tpowell on 11/12/16.
 */

import {Injectable} from '@angular/core';
import {Http, Headers, Response} from '@angular/http';
import {Employee} from '../domain/employee';


@Injectable()
export class RegistrationService{

    constructor(private http:Http) {}

    registerEmployee(employee:Employee){
        console.log(employee);
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        const response =  this.http
            .post(
                '/registerEmployee',
                JSON.stringify({employee}),
                {headers}
            );
        return response;
    }
    
}