package com.powell.service;

import com.powell.dao.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tpowell on 9/24/16.
 * -_-
 */
@Service
public class LoginService {
    private LoginDAO loginDAO;

    @Autowired
    public LoginService(LoginDAO loginDAO) {
        if(loginDAO == null){
            throw new NullPointerException("The DAO was not initialized");
        }
        this.loginDAO = loginDAO;
    }

    public boolean validateUser(String userName, String password) {
        return loginDAO.validateUser(userName, password);
    }
}
