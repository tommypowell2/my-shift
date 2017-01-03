/**
 * Created by tpowell on 12/25/16.
 */
import {Component} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {Employee} from  '../domain/employee';


@Component({
    selector: 'employee-schedule',
    templateUrl: 'app/scheduler/employe.schedule.html'
})
export class EmployeeScheduleComponent {
    title = 'Employees Schedule';
    router;
    username;
    selectedSchedule;
    workSchedule = {
        years: [
            {
                id: 2016, months: [
                {
                    month: 'August', daysOfMonth: [
                    {id: 1, dayOfWeek: 'Monday', startTime: "7:00", endTime: "9:00", selected: false},
                    {id: 2, dayOfWeek: 'Tuesday', startTime: "7:00", endTime: "9:00", selected: false},
                    {id: 3, dayOfWeek: 'Wednesday', startTime: "7:00", endTime: "9:00", selected: false},
                    {id: 4, dayOfWeek: 'Thursday', startTime: "7:00", endTime: "9:00", selected: false},
                    {id: 5, dayOfWeek: 'Friday', startTime: "7:00", endTime: "9:00", selected: false}
                ]
                }
            ]
            }
        ]
    };

    years = this.workSchedule.years;
    daysOfMonth;

    weekDays = [
        {name: 'Mon'},
        {name: 'Tue'},
        {name: 'Wed'},
        {name: 'Thur'},
        {name: 'Fri'},
        {name: 'Sat'},
        {name: 'Sun'}
    ];


    selectedValue = null;


    constructor(router: Router, ac: ActivatedRoute) {
        this.router = router;
        this.username = ac.snapshot.params['username'];
        for (let year of this.years) {
            if (year.id == 2016) {
                for (let month of year.months) {
                    if (month.month == 'August') {
                        this.daysOfMonth = month.daysOfMonth;
                    }
                }

            }
        }
    }

    testClick(schedule) {
        schedule.selected = true;
        for (let day of this.daysOfMonth) {
            if (day != schedule) {
                day.selected = false;
            }
        }
        //this.selectedSchedule = schedule;
        console.log(schedule)
    }
}