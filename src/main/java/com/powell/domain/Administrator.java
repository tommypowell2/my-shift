package com.powell.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by tpowell on 12/17/16.
 * -_-
 */
public class Administrator extends com.powell.security.domain.User{

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String position;
    private Company company;


    public Administrator(String firstName, String lastName, String username, String password, Company company) {
        super(firstName, lastName, username, password, company, "admin");
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = super.getPassword();
        this.position = "admin";
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}