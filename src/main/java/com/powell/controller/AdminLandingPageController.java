package com.powell.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.powell.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

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

    @RequestMapping(value = "/getCompanyID")
    public String getCompanyID(HttpServletRequest request) throws IOException, SQLException {
        String userName = new Gson().fromJson(request.getReader(), JsonObject.class).get("username").getAsString();
        int companyID = adminService.getCompanyID(userName);
        if(companyID <= 0){
            return "{\"error\":\"Could not find companyID\"}";
        }
        return "{\"companyID\":\""+companyID+"\"}";
    }
}
