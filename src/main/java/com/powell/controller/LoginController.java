package com.powell.controller;

import com.powell.domain.User;
import com.powell.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    @RequestMapping("/login2")
    public String login(HttpServletRequest request) throws IOException {
        User user = getUser(request);
        String userName = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password))
            return new Gson().toJson("Please provide valid login credentials");
        boolean validated = loginService.validateUser(userName, password);
        if(!validated){
            return new Gson().toJson("Please provide valid login credentials");
        }
        return "true";
    }

    protected User getUser(HttpServletRequest request) throws IOException {
        return new Gson().fromJson(request.getReader(), User.class);
    }
}
