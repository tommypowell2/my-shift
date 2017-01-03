package com.powell.domain;

/**
 * Created by tpowell on 12/17/16.
 * -_-
 */
public class Company {

    private final String companyName;
    private final int companyID;

    public Company(String companyName, int companyID){
        this.companyName = companyName;
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getCompanyID() {
        return companyID;
    }
}
