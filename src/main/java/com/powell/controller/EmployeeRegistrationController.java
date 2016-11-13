package com.powell.controller;

import com.google.gson.Gson;
import com.powell.domain.EmployeeWrapper;
import com.powell.domain.Employee;
import com.powell.service.RegistrationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by tpowell on 11/13/16.
 * -_-
 */
@RestController
public class EmployeeRegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public EmployeeRegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping("/registerEmployee")
    public String registerEmployee(HttpServletRequest request) throws IOException {
        Employee employee = extractEmployee(request);
        String validationError = getValidationError(employee);
        if(validationError != null){
            return validationError;
        }
        Long employeeID = registrationService.register(employee);
        return "{messageType:\"success\", message: \"Registration successful\", employeeID:\""+employeeID+"\"}";
    }

    private Employee extractEmployee(HttpServletRequest request) throws IOException {
        EmployeeWrapper wrapper = new Gson().fromJson(request.getReader(), EmployeeWrapper.class);
        return wrapper.getEmployee();
    }

    private String getValidationError(Employee employee) {
        String error = null;
        String baserError = "{messageType:\"error\", message: \"field is not valid.\"}";
        if (StringUtils.isBlank(employee.getFirstName())) {
            error = baserError.replace("field", "First name");
        } else if (StringUtils.isBlank(employee.getLastName())) {
            error = baserError.replace("field", "Last name");
        } else if (StringUtils.isBlank(employee.getUserName())) {
            error = baserError.replace("field", "User name");
        } else if (StringUtils.isBlank(employee.getPosition())) {
            error = baserError.replace("field", "Position");
        }
        return error;
    }
}
