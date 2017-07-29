/**
 * Created by tpowell on 1/28/17.
 */
import {Component} from "@angular/core";
import {Employee} from "../domain/employee";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'employee-home-page',
    templateUrl: './employee.home.page.component.html'
})
export class EmployeeHomePageComponent {
    title;
    employee; // = new Employee('','','','');
    username;
    router;
    // companyID = 0;


    constructor(router:Router, ac:ActivatedRoute) {
        this.router = router;
        this.username = ac.snapshot.params['username'];
        console.log(this.username);
        this.title = 'Hello ' + this.username + ', Welcome to the employee landing page';
    }

    showSchedule() {
        this.router.navigate(['employeeSchedule']);
    }
}
