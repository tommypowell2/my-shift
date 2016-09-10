import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { AppComponent }  from './app.component';
import { Login } from './login/login.component';
import {Logger} from "./util/logger.service";
import { HttpModule }    from '@angular/http';
// import { routing }              from './app.routing';


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
    ],
    declarations: [
        AppComponent,
        Login,
        
    ],
    providers: [
        Logger
    ],
    //     UserService,
    //     { provide: APP_CONFIG, useValue: USER_DI_CONFIG }
    // ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
