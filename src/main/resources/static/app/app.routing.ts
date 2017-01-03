/**
 * Created by tpowell on 9/10/16.
 */
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomePageComponent} from "./login/home.page.component";
import {Registration} from "./registration/registration.component";
import {Login} from "./login/login.component";
import {LoggedInGuard} from "./login/loggedin.guard.component";
import {AdminRegistration} from "./registration/registration.admin.component";
import {EmployeeScheduleComponent} from "./scheduler/employee.schedule";


const appRoutes: Routes = [
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        component: Login
    },
    {
        path: 'registerAdmin',
        component: AdminRegistration
    },
    {
        path: 'success/:username',
        component: HomePageComponent,
        canActivate: [LoggedInGuard]
    },
    {
        path: 'employeeRegistration',
        component: Registration,
        canActivate: [LoggedInGuard]
    },
    {
        path: 'employeeSchedule',
        component: EmployeeScheduleComponent
    }
];

export const appRoutingProviders: any[] = [

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
