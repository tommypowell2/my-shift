import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { AppComponent }  from './app.component';
import { Login } from './login/login.component';
import { HomePageComponent } from './login/home.page.component';
import {Registration} from  './registration/registration.component';
import {Logger} from "./util/logger.service";
import { HttpModule }    from '@angular/http';
import { routing, appRoutingProviders } from './app.routing';

import {LoggedInGuard} from "./login/loggedin.guard.component";
import {LoginService} from "./service/login-service";
import {RegistrationService} from './service/registration-service';
import {AdminRegistration} from "./registration/registration.admin.component";
import {EmployeeScheduleComponent} from "./scheduler/employee.schedule";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing
    ],
    declarations: [
        AppComponent,
        Login,
        HomePageComponent,
        Registration,
        AdminRegistration,
        EmployeeScheduleComponent
    ],
    providers: [
        Logger,
        appRoutingProviders
        ,
        LoggedInGuard,
        LoginService,
        RegistrationService
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
