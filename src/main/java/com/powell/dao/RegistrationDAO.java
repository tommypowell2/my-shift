package com.powell.dao;

import com.powell.domain.Administrator;
import com.powell.domain.Employee;
import com.powell.security.domain.User;

import java.sql.SQLException;

/**
 * Created by tpowell on 11/23/16.
 * -_-
 */
public interface RegistrationDAO {
    void registerEmployee(User employee) throws SQLException;

    void registerAdmin(User administrator) throws SQLException;
}
