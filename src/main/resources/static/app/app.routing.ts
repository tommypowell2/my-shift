/**
 * Created by tpowell on 9/10/16.
 */
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomePageComponent} from "./login/home.page.component";
import {Login} from "./login/login.component";
import {LoggedInGuard} from "./login/loggedin.guard.component";


const appRoutes: Routes = [
    {
        path: '',
        redirectTo: '/#login',
        pathMatch: 'full'
    },
    {
        path: '#login',
        component: Login
    },
    {
        path: '#success',
        component: HomePageComponent,
        canActivate: [LoggedInGuard]
    }
];

export const appRoutingProviders: any[] = [

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
