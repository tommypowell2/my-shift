/**
 * Created by tpowell on 9/10/16.
 */
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminHomePageComponent} from "./login/admin.home.page.component";
import {Registration} from "./registration/registration.component";
import {Login} from "./login/login.component";
import {LoggedInGuard} from "./login/loggedin.guard.component";
import {AdminRegistration} from "./registration/registration.admin.component";
import {EmployeeScheduleComponent} from "./scheduler/employee.schedule";
import {EmployeeHomePageComponent} from "./login/employee.home.page.component";


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
        path: 'admin/:username',
        component: AdminHomePageComponent,
        canActivate: [LoggedInGuard]
    },
    {
        path: 'employee/:username',
        component: EmployeeHomePageComponent,
        canActivate: [LoggedInGuard]
    },
    {
        path: 'employeeRegistration',
        component: Registration,
        canActivate: [LoggedInGuard]
    },
    {
        path: 'employeeSchedule',
        component: EmployeeScheduleComponent,
        canActivate: [LoggedInGuard]
    }
];

export const appRoutingProviders: any[] = [

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
