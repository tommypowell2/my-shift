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
var core_1 = require('@angular/core');
var platform_browser_1 = require('@angular/platform-browser');
var forms_1 = require('@angular/forms');
var app_component_1 = require('./app.component');
var login_component_1 = require('./login/login.component');
var logger_service_1 = require("./util/logger.service");
var http_1 = require('@angular/http');
var app_routing_1 = require('./app.routing');
var home_page_component_1 = require('./login/home.page.component');
var loggedin_guard_component_1 = require("./login/loggedin.guard.component");
var login_service_1 = require("./service/login-service");
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [
                platform_browser_1.BrowserModule,
                forms_1.FormsModule,
                http_1.HttpModule,
                app_routing_1.routing
            ],
            declarations: [
                app_component_1.AppComponent,
                login_component_1.Login,
                home_page_component_1.HomePageComponent
            ],
            providers: [
                logger_service_1.Logger,
                app_routing_1.appRoutingProviders,
                loggedin_guard_component_1.LoggedInGuard,
                login_service_1.LoginService
            ],
            //     UserService,
            //     { provide: APP_CONFIG, useValue: USER_DI_CONFIG }
            // ],
            bootstrap: [app_component_1.AppComponent]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map