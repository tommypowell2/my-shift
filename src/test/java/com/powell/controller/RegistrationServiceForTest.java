package com.powell.controller;

import com.powell.dao.RegistrationDAO;
import com.powell.domain.Administrator;
import com.powell.domain.Company;
import com.powell.domain.Employee;
import com.powell.service.RegistrationService;

/**
 * Created by tpowell on 12/17/16.
 * -_-
 */
class RegistrationServiceForTest extends RegistrationService {

    public RegistrationServiceForTest(RegistrationDAO registrationDAO) {
        super(registrationDAO);
    }

    public void register(Employee employee) {
        employee.setEmployeeID(1l);
    }

    public void register(Administrator administrator) {
        Company company = new Company("MyCompany", 1);
        administrator.setCompany(company);;
    }
}
