package com.powell.domain;

/**
 * Created by tpowell on 11/13/16.
 * -_-
 */
public class EmployeeWrapper {
    private final Employee employee;

    public EmployeeWrapper(Employee employee){
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
