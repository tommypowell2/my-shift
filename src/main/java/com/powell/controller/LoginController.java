package com.powell.controller;

import com.google.gson.JsonObject;
import com.powell.domain.User;
import com.powell.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/login2", produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(HttpServletRequest request) throws IOException {
        User user = getUser(request);
        String userName = user.getUsername();
        String password = user.getPassword();
        JsonObject jsonObject = new JsonObject();
        String key = "message";
        String value = "true";
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            value = "Please provide valid login credentials";
        }
        boolean validated = loginService.validateUser(userName, password);
        if (!validated) {
            value = "Please provide valid login credentials";
        }
        jsonObject.addProperty(key, value);
        return jsonObject.toString();
    }

    protected User getUser(HttpServletRequest request) throws IOException {
        return new Gson().fromJson(request.getReader(), User.class);
    }
}
