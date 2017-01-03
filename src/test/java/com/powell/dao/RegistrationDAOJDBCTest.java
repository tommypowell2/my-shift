package com.powell.dao;

import com.powell.domain.Administrator;
import com.powell.domain.Company;
import com.powell.domain.Employee;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by tpowell on 11/23/16.
 * -_-
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Sql("classpath:sql/registration/test-admin-registered-successfully.sql")
public class RegistrationDAOJDBCTest {

    @Autowired
    private DataSource dataSource;

    @Mock
    Company company;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Sql("classpath:test-registration.sql")
    @Test
    public void employeeRegistrationSucceeds() throws SQLException {
        Employee employee = new Employee("test", "user", "tuser", "Test Dummy", 1);
        RegistrationDAO registrationDAO = new RegistrationDAOJDBC(dataSource);
        registrationDAO.registerEmployee(employee);
        String sql = "select employeeID, companyID from employee where username = 'tuser'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            long returnedEmployeeID = 0;
            int returnedCompanyID = 0;
            if (resultSet.next()) {
                returnedEmployeeID = resultSet.getLong("EmployeeID");
                returnedCompanyID = resultSet.getInt("CompanyID");
            }
            assertNotNull(employee.getEmployeeID());
            assertEquals(new Long(returnedEmployeeID), employee.getEmployeeID());
            assertEquals(returnedCompanyID, employee.getCompanyID());
        }
    }


    @Sql(value = {"classpath:test-registration.sql", "classpath:sql/registration/test-user-exists-registration.sql"})
    @Test
    public void registrationFailsIfUserAlreadyExists() throws SQLException {
        expectedException.expect(SQLException.class);
        Employee employee = new Employee("test", "user", "tuser", "Test Dummy", 1);
        RegistrationDAO registrationDAO = new RegistrationDAOJDBC(dataSource);
        registrationDAO.registerEmployee(employee);
    }

//    @Sql("classpath:sql/registration/test-admin-registered-successfully.sql")
    @Test
    public void adminRegistrationSucceeds() throws SQLException {
        String readablePassword = "xyz";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Administrator administrator = new Administrator("test", "user", "tuser", readablePassword, company);
        RegistrationDAO registrationDAO = new RegistrationDAOJDBC(dataSource);
        when(company.getCompanyName()).thenReturn("My Company");
        registrationDAO.registerAdmin(administrator);
        String sql = "select authority, password, company.id " +
                "from authorities,  shift_admin_users, company " +
                "where authorities.username = 'tuser' " +
                "and authorities.username = shift_admin_users.username " +
                "and shift_admin_users.companyid = company.id";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            String returnedAuthority = "";
            String returnedPassword = "";
            int returnedCompanyID = 0;
            while (resultSet.next()) {
                returnedAuthority = resultSet.getString("authority");
                returnedPassword = resultSet.getString("password");
                returnedCompanyID = resultSet.getInt("id");
            }
            String expectedAuthority = "admin";
            int expectedCompanyID = 0;
            assertEquals(expectedAuthority, returnedAuthority);
            assertEquals(true, bCryptPasswordEncoder.matches(readablePassword, returnedPassword));
            assertEquals(expectedCompanyID, returnedCompanyID);
        }
    }

    @Sql(value = {"classpath:sql/registration/test-admin-registered-successfully.sql","classpath:sql/registration/test-admin-auth-exists.sql"})
    @Test
    public void adminRegistrationAuthorityExists() throws SQLException {
        expectedException.expect(SQLIntegrityConstraintViolationException.class);
        Administrator administrator = new Administrator("test", "user", "tuser", "xyz", company);
        RegistrationDAO registrationDAO = new RegistrationDAOJDBC(dataSource);
        when(company.getCompanyName()).thenReturn("My Company");
        registrationDAO.registerAdmin(administrator);
    }

    @Sql(value = {"classpath:sql/registration/test-admin-registered-successfully.sql","classpath:sql/registration/test-admin-user-exists.sql"})
    @Test
    public void adminRegistrationUserExists() throws SQLException {
        expectedException.expect(SQLException.class);
        Administrator administrator = new Administrator("test", "user", "tuser1", "xyz", company);
        RegistrationDAO registrationDAO = new RegistrationDAOJDBC(dataSource);
        when(company.getCompanyName()).thenReturn("My Company");
        registrationDAO.registerAdmin(administrator);
    }
}