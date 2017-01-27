package com.powell.domain;

import com.powell.security.domain.*;
import com.powell.security.domain.User;

/**
 * Created by tpowell on 11/13/16.
 * -_-
 */
public class EmployeeWrapper {
    private final com.powell.security.domain.User employee;

    public EmployeeWrapper(com.powell.security.domain.User employee){
        this.employee = employee;
    }

    public com.powell.security.domain.User getEmployee() {
        return new User(employee.getFirstName(), employee.getLastName(), employee.getUserName(),
                employee.getPassword(), employee.getCompany(), employee.getPosition());
    }
}
