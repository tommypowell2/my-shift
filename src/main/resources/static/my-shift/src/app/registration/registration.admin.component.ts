import {Component} from "@angular/core";
import {Administrator} from "../domain/administrator";
import {Router} from "@angular/router";
import {RegistrationService} from "../service/registration-service";
import {map} from "rxjs/operator/map";

/**
 * Created by tpowell on 12/17/16.
 */
@Component({
    selector: 'register-admin-page',
    templateUrl: './registration.admin.component.html'
})

export class AdminRegistration {
    title = 'Please registerAdmin';
    administrator = new Administrator('', '', '');
    router;
    registrationService;
    showError = false;
    message;

    constructor(router: Router, registrationService: RegistrationService) {
        this.router = router;
        this.registrationService = registrationService;
    }

    registerAdmin() {
        const response = this.registrationService.registerAdmin(this.administrator);
        response.subscribe(
            reply => {
                if (reply.status == 200) {
                    if (reply.json().messageType != 'error')
                        this.router.navigate(['login']);
                    else {
                        this.displayError(reply.json().message);
                    }
                }
            }
        );
    }

    private displayError(message: string) {
        this.message = message;
        this.showError = true;

    }


    cancel() {
        this.router.navigate(['login']);
    }
}
