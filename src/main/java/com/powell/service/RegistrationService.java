package com.powell.service;

import com.powell.domain.Administrator;
import com.powell.domain.Employee;
import com.powell.dao.RegistrationDAO;
import com.powell.security.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by tpowell on 11/13/16.
 * -_-
 */
@Service
public class RegistrationService {

    private RegistrationDAO registrationDAO;

    @Autowired
    public RegistrationService(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    public void register(User employee) throws SQLException {
        registrationDAO.registerEmployee(employee);
    }

    public void registerAdmin(User administrator) throws SQLException {
        registrationDAO.registerAdmin(administrator);
    }
}
