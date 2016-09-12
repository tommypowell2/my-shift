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
 * Created by tpowell on 9/4/16.
 */
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
// import localStorage from 'localStorage';
var mock_users_1 = require('./mock-users');
var logger_service_1 = require("../util/logger.service");
var LoginService = (function () {
    function LoginService(logger, http) {
        this.logger = logger;
        this.http = http;
        this.loggedIn = false;
        this.logger = logger;
        this.loggedIn = !!localStorage.getItem('auth_token');
    }
    LoginService.prototype.validateUser = function (user) {
        this.loggedIn = false;
        for (var userKey in mock_users_1.USERS) {
            var userFromList = mock_users_1.USERS[userKey];
            if (user.username == userFromList.username && user.password == userFromList.password) {
                this.loggedIn = true;
                localStorage.setItem('auth_token', 'token');
                this.logger.log('Login attempt succeeded  for ' + user.username);
            }
        }
        if (!this.loggedIn) {
            this.logger.log('Login attempt failed for ' + user.username);
        }
        return this.loggedIn;
    };
    LoginService.prototype.isLoggedIn = function () {
        return this.loggedIn;
    };
    LoginService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [logger_service_1.Logger, http_1.Http])
    ], LoginService);
    return LoginService;
}());
exports.LoginService = LoginService;
//# sourceMappingURL=login-service.js.map