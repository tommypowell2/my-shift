package com.powell.domain;

/**
 * Created by tpowell on 11/13/16.
 * -_-
 */
public class Employee {


    private String firstName;
    private final String lastName;
    private final String userName;
    private final String position;

    public Employee(String firstName, String lastName, String userName, String position){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPosition() {
        return position;
    }
}
