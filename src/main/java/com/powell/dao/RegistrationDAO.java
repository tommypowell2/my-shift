package com.powell.dao;

import com.powell.domain.Employee;

import java.sql.SQLException;

/**
 * Created by tpowell on 11/23/16.
 * -_-
 */
public interface RegistrationDAO {
    void registerEmployee(Employee employee) throws SQLException;
}
