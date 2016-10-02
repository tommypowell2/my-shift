package com.powell.domain;

/**
 * Created by tpowell on 10/2/16.
 * -_-
 */
public class User {
    private final String password;
    private final String username;


    public User(String username, String password){
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
