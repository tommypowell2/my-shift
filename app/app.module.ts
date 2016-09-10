import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { AppComponent }  from './app.component';
import { Login } from './login/login.component';
import {Logger} from "./util/logger.service";
import { HttpModule }    from '@angular/http';
import { routing,
    appRoutingProviders } from './app.routing';
import { HomePageComponent } from './login/home.page.component'


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
        HomePageComponent
    ],
    providers: [
        Logger,
        appRoutingProviders
    ],
    //     UserService,
    //     { provide: APP_CONFIG, useValue: USER_DI_CONFIG }
    // ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
