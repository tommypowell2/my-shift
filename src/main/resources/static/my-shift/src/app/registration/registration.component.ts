/**
 * Created by tpowell on 11/5/16.
 */
import {Component} from '@angular/core';
import {Employee} from '../domain/employee'

@Component({
    selector: 'employee-registration-page',
    // providers: [EmployeeRegistrationService],
    templateUrl: './registration.component.html'
})
export class Registration {
    title = 'Please registerAdmin an employee';
    employee;

    register(){

        console.log("register")

    }
}
