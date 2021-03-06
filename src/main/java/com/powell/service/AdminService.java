package com.powell.service;

import com.powell.dao.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by tpowell on 9/24/16.
 * -_-
 */
@Service
public class AdminService {
    private AdminDAO adminDAO;

    @Autowired
    public AdminService(AdminDAO adminDAO) {
        if (adminDAO == null) {
            throw new NullPointerException("The DAO was not initialized");
        }
        this.adminDAO = adminDAO;
    }

    public int getCompanyID(String userName) throws SQLException {
        return adminDAO.getCompanyID(userName);
    }
}
