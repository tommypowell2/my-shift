package com.powell.controller;

import com.powell.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tpowell on 9/24/16.
 * -_-
 */
@RestController
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
//        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password))
//            return "Please provide valid login credentials";
//        boolean validated = loginService.validateUser(userName, password);
//        if(!validated){
//            return "Please provide valid login credentials";
//        }
        return "true";
    }
}
