package com.powell.domain;

import com.powell.security.domain.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by tpowell on 11/13/16.
 * -_-
 */
public class Employee extends com.powell.security.domain.User{


    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String position;
    private final int companyID;
    private Long employeeID;
    private String password;


    public Employee(String firstName, String lastName, String userName, String position, int companyID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.position = position;
        this.companyID = companyID;
    }

    public Employee(String firstName, String lastName, String userName, String password, String position, Company company){
        super(firstName, lastName, userName, password, company, position);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.position = position;
        this.companyID = company.getCompanyID();
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

}
