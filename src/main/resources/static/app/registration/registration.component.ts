/**
 * Created by tpowell on 11/5/16.
 */
import {Component} from '@angular/core';
import {Employee} from '../domain/employee'

@Component({
    selector: 'employee-registration-page',
    // providers: [EmployeeRegistrationService],
    templateUrl: 'app/registration/registration.component.html'
})
export class Registration {
    title = 'Please register an employee';
    employee;

    register(){

        console.log("register")

    }
}