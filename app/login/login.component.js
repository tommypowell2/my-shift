"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
/**
 * Created by tpowell on 7/30/16.
 */
var core_1 = require('@angular/core');
var user_1 = require('../domain/user');
var login_service_1 = require('../service/login-service');
var router_1 = require('@angular/router');
var Login = (function () {
    function Login(loginService, router) {
        this.title = 'Login';
        //user = User;
        this.user = new user_1.User('', '');
        this.foundUser = false;
        this.submitted = false;
        this.loginService = loginService;
        this.router = router;
    }
    Login.prototype.onSubmit = function () {
        this.foundUser = this.loginService.validateUser(this.user);
        this.submitted = true;
        if (this.foundUser) {
            this.router.navigate(['success']);
        }
    };
    Object.defineProperty(Login.prototype, "diagnostic", {
        get: function () {
            return JSON.stringify(this.user);
        },
        enumerable: true,
        configurable: true
    });
    Login = __decorate([
        core_1.Component({
            selector: 'login-page',
            templateUrl: 'app/login/login.component.html'
        }), 
        __metadata('design:paramtypes', [login_service_1.LoginService, router_1.Router])
    ], Login);
    return Login;
}());
exports.Login = Login;
//# sourceMappingURL=login.component.js.map