/**
 * Created by tpowell on 7/30/16.
 */
import {Component} from '@angular/core';
// import {Login} from './login/login.component';

@Component({
    selector: 'my-app',
    template: `<h1>{{title}}</h1>
                <login-page></login-page>`
    // ,    directives: [Login]
})
export class AppComponent {
    title = 'My Shift';
}
