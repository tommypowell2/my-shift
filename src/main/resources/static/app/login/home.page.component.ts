/**
 * Created by tpowell on 9/10/16.
 */
import {Component} from '@angular/core';
import {Router} from '@angular/router';

import {Employee} from  '../domain/employee';
import {RegistrationService} from '../service/registration-service'

@Component({
    selector: 'home-page',
    templateUrl: 'app/login/home.page.component.html'
})
export class HomePageComponent {
    title = 'Welcome to the landing page';
    employee = new Employee('','','','');
    router;
    registrationService;
    hideRegistrationForm = true;
    positions = [
        {id: 1, name: "Software Engineer"},
        {id: 2, name: "Technical Lead"},
        {id: 3, name: "QA Engineer"},
        {id: 4, name: "Automation Engineer"},
        {id: 5, name: "Business Analyst"}
    ];
    selectedValue = null;


    constructor(router:Router, registrationService:RegistrationService) {
        this.router = router;
        this.registrationService = registrationService;
    }

    showRegistration() {
        // //employeeRegistration
        // this.router.navigate(['employeeRegistration']);
        this.hideRegistrationForm = !this.hideRegistrationForm;
    }

    register() {
        const response =  this.registrationService.registerEmployee(this.employee);
        response.subscribe(
            reply => {
                //need to add code here to handle the reply
                console.log(reply);
            }
        );

    }
}