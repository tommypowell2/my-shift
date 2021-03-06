package com.powell.controller;

import com.google.gson.Gson;
import com.powell.domain.EmployeeWrapper;
import com.powell.domain.Employee;
import com.powell.security.domain.User;
import com.powell.service.RegistrationService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by tpowell on 11/13/16.
 * -_-
 */
@RestController
public class EmployeeRegistrationController {
    private final Logger LOGGER = Logger.getLogger(Employee.class);
    private final RegistrationService registrationService;

    @Autowired
    public EmployeeRegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping("/registerEmployee")
    public String registerEmployee(HttpServletRequest request) throws IOException {
        User employee = extractEmployee(request);
        String message = getValidationError(employee);
        if (message == null) {
            try {
                registrationService.register(employee);
                message = "{\"messageType\":\"success\", \"message\": \"Registration successful\", \"employeeID\":\"" + employee.getUserID() + "\"}";
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                message = "{\"messageType\":\"error\", \"message\": \"Can not save user to database, possible duplicate user name.\"}";
            }
        }
        return message;
    }

    private com.powell.security.domain.User extractEmployee(HttpServletRequest request) throws IOException {
        EmployeeWrapper wrapper = new Gson().fromJson(request.getReader(), EmployeeWrapper.class);
        return wrapper.getEmployee();
    }

    private String getValidationError(User employee) {
        String error = null;
        String baserError = "{\"messageType\":\"error\", \"message\": \"field is not valid.\"}";
        if (StringUtils.isBlank(employee.getFirstName())) {
            error = baserError.replace("field", "First name");
        } else if (StringUtils.isBlank(employee.getLastName())) {
            error = baserError.replace("field", "Last name");
        } else if (StringUtils.isBlank(employee.getUserName())) {
            error = baserError.replace("field", "User name");
        } else if (StringUtils.isBlank(employee.getPosition())) {
            error = baserError.replace("field", "Position");
        } else if (employee.getCompany().getCompanyID() <= 0) {
            error = baserError.replace("field", "Company ID");
        }
        return error;
    }
}