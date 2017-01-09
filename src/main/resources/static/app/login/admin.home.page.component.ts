/**
 * Created by tpowell on 9/10/16.
 */
import {Component} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {Employee} from  '../domain/employee';
import {RegistrationService} from '../service/registration-service'

@Component({
    selector: 'home-page',
    templateUrl: 'app/login/home.page.component.html'
})
export class AdminHomePageComponent {
    title;
    employee = new Employee('','','','');
    router;
    registrationService;
    hideRegistrationForm = true;
    username;
    companyID = 0;
    registeredEmployees;//this will be a list of employees to manage;

    positions = [
        {id: 1, name: "Software Engineer"},
        {id: 2, name: "Technical Lead"},
        {id: 3, name: "QA Engineer"},
        {id: 4, name: "Automation Engineer"},
        {id: 5, name: "Business Analyst"}
    ];
    selectedValue = null;


    constructor(router:Router, registrationService:RegistrationService, ac:ActivatedRoute) {
        this.router = router;
        this.registrationService = registrationService;
        this.username = ac.snapshot.params['username'];
        this.title = 'Hello ' + this.username + ', Welcome to the admin landing page';
    }

    showRegistration() {
        if(this.companyID == 0){
            const response = this.registrationService.getCompanyID(this.username);
            response.subscribe(
                reply => {
                    this.companyID = reply.json().companyID;
                }
            )
        }
        // //employeeRegistration
        // this.router.navigate(['employeeRegistration']);
        this.hideRegistrationForm = !this.hideRegistrationForm;
    }

    register() {
        this.employee.companyID = this.companyID;
        const response =  this.registrationService.registerEmployee(this.employee);
        response.subscribe(
            reply => {
                //need to add code here to handle the reply
                console.log(reply);
                if(reply.json().messageType == 'success'){

                } else {

                }
            }
        );

    }
}