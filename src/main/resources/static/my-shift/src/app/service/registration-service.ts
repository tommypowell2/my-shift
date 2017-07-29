/**
 * Created by tpowell on 11/12/16.
 */

import {Injectable} from '@angular/core';
import {Http, Headers, Response} from '@angular/http';
import {Employee} from '../domain/employee';
import {Administrator} from "../domain/administrator";


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

    registerAdmin(administrator:Administrator){
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        const response =  this.http
            .post(
                '/registerAdmin',
                JSON.stringify({administrator}),
                {headers}
            );
        return response;
    }

    getCompanyID(username:string){
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        const response =  this.http
            .post(
                '/getCompanyID',
                JSON.stringify({username}),
                {headers}
            );
        return response;

    }
    
}