package com.powell.controller;

import com.google.gson.Gson;
import com.powell.domain.AdministratorRegistrationWrapper;
import com.powell.security.domain.User;
import com.powell.service.RegistrationService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by tpowell on 12/17/16.
 * -_-
 */
@RestController
public class AdminRegistrationController {
    private final Logger LOGGER = Logger.getLogger(AdminRegistrationController.class);
    private final RegistrationService registrationService;

    public AdminRegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "/registerAdmin")
    public String registerAdmin(HttpServletRequest request) throws IOException {
        User administrator = extractAdmin(request);
        String message = getValidationError(administrator);
        if (message == null) {
            try {
                registrationService.registerAdmin(administrator);
                message = "{\"messageType\":\"success\", \"message\": \"Registration successful\", \"companyID\":\""+ administrator.getCompany().getCompanyID() + "\"}";
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                message = "{\"messageType\":\"error\", \"message\": \"Can not save user to database, possible duplicate user name.\"}";
            }
        }
        return message;
    }

    private User extractAdmin(HttpServletRequest request) throws IOException {
        AdministratorRegistrationWrapper wrapper = new Gson().fromJson(request.getReader(), AdministratorRegistrationWrapper.class);
        return wrapper.getAdministrator();
    }

    private String getValidationError(User administrator) {
        String error = null;
        String baserError = "{\"messageType\":\"error\", \"message\": \"field is not valid.\"}";
        if (StringUtils.isBlank(administrator.getFirstName())) {
            error = baserError.replace("field", "First name");
        } else if (StringUtils.isBlank(administrator.getLastName())) {
            error = baserError.replace("field", "Last name");
        } else if (StringUtils.isBlank(administrator.getUserName())) {
            error = baserError.replace("field", "User name");
        } else if (StringUtils.isBlank(administrator.getPassword())) {
            error = baserError.replace("field", "Password");
        }
        return error;
    }
}
