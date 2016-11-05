import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { AppComponent }  from './app.component';
import { Login } from './login/login.component';
import { HomePageComponent } from './login/home.page.component';
import {RegistrationComponent} from  './registration/registration.component';
import {Logger} from "./util/logger.service";
import { HttpModule }    from '@angular/http';
import { routing, appRoutingProviders } from './app.routing';

import {LoggedInGuard} from "./login/loggedin.guard.component";
import {LoginService} from "./service/login-service";


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
        RegistrationComponent
    ],
    providers: [
        Logger,
        appRoutingProviders
        ,
        LoggedInGuard,
        LoginService
    ],
    //     UserService,
    //     { provide: APP_CONFIG, useValue: USER_DI_CONFIG }
    // ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
