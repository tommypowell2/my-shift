package com.powell.controller;

import com.google.gson.JsonObject;
import com.powell.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by tpowell on 9/24/16.
 * -_-
 */
@RestController
public class AdminLandingPageController {

    private AdminService adminService;

    @Autowired
    public AdminLandingPageController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/success", produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(HttpServletRequest request) throws IOException {

        JsonObject jsonObject = new JsonObject();
        String key = "message";
        String value = "true";
        jsonObject.addProperty(key, value);
        return jsonObject.toString();
    }
}