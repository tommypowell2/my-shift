/**
 * Created by tpowell on 7/30/16.
 */
import {Component} from '@angular/core';

@Component({
    selector: 'my-app',
    template: `<h1>{{title}}</h1>
                <router-outlet></router-outlet>`
    // ,    directives: [Login]
})
export class AppComponent {
    title = 'Time Shift';
}
