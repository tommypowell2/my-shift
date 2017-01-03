package com.powell.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by tpowell on 12/17/16.
 * -_-
 */
public class Administrator {

    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;
    private Company company;


    public Administrator(String firstName, String lastName, String userName, String password, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.company = company;
        if (StringUtils.isBlank(password)) {
            this.password = null;
        } else {
            this.password = new BCryptPasswordEncoder().encode(password);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public Company getCompany() {
        return company;
    }

    public String getPassword() {
        return password;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}