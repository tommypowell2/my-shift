package com.powell.dao;

import com.powell.domain.Administrator;
import com.powell.domain.Company;
import com.powell.domain.Employee;
import org.junit.Before;
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

    private RegistrationDAO registrationDAO;

    Company company;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private final String readablePassword = "xyz";
    private Employee employee;

    private Administrator administrator;

    @Before
    public void setUp(){
        registrationDAO = new RegistrationDAOJDBC(dataSource);
        company = new Company("My Company", 1);
        administrator = new Administrator("test", "user", "tuser", readablePassword, company);
        employee = new Employee("test", "user", "tuser1", readablePassword, "Test Dummy", company);
    }

    @Sql(value = {"classpath:test-registration.sql", "classpath:sql/registration/test-user-exists-registration.sql"})
    @Test
    public void registrationFailsIfUserAlreadyExists() throws SQLException {
        expectedException.expect(SQLException.class);
        registrationDAO.registerEmployee(employee);
    }

    @Sql(value = {"classpath:sql/registration/test-admin-registered-successfully.sql","classpath:sql/registration/test-admin-auth-exists.sql"})
    @Test
    public void adminRegistrationAuthorityExists() throws SQLException {
        expectedException.expect(SQLIntegrityConstraintViolationException.class);
        registrationDAO.registerAdmin(administrator);
    }

    @Test
    public void adminRegistrationUserExists() throws SQLException {
        expectedException.expect(SQLException.class);
        registrationDAO.registerAdmin(administrator);
    }

    @Test
    public void adminRegistrationSucceeds() throws SQLException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        registrationDAO.registerAdmin(administrator);
        String sql = "select * " +
                "from authorities,  shift_user, company " +
                "where authorities.username = 'tuser' " +
                "and authorities.username = shift_user.username " +
                "and shift_user.companyid = company.id";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            String returnedAuthority = "";
            String returnedPassword = "";
            String returnedFirstName = "";
            String returnedLastName = "";
            String returnedPosition = "";
            int returnedCompanyID = 0;
            while (resultSet.next()) {
                returnedAuthority = resultSet.getString("authority");
                returnedPassword = resultSet.getString("password");
                returnedCompanyID = resultSet.getInt("id");
                returnedFirstName = resultSet.getString("firstname");
                returnedLastName = resultSet.getString("lastname");
                returnedPosition = resultSet.getString("position");
            }
            String expectedAuthority = "admin";
            assertEquals(expectedAuthority, returnedAuthority);
            assertEquals(true, bCryptPasswordEncoder.matches(readablePassword, returnedPassword));
            assertEquals(administrator.getCompany().getCompanyID(), returnedCompanyID);
            assertEquals(administrator.getFirstName(), returnedFirstName);
            assertEquals(administrator.getLastName(), returnedLastName);
            assertEquals(administrator.getPosition(), returnedPosition);
            assertNotEquals(null, returnedPosition);
        }
    }

    @Sql(value = {"classpath:sql/registration/test-employee-registered-successfully.sql"})
    @Test
    public void employeeRegistrationSucceeds() throws SQLException {
        Employee employee = new Employee("test", "user", "tuser2", readablePassword, "Test Dummy", company);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        registrationDAO.registerEmployee(employee);
        String sql =
        "select * " +
                "from authorities , shift_user, company " +
                "where authorities.username = 'tuser2' " +
                "and authorities.username = shift_user.username " +
                "and shift_user.companyid = company.id";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            String returnedAuthority = "";
            String returnedPassword = "";
            int returnedCompanyID = 0;
            boolean returnedEnabled = false;
            String returnedFirstName = "";
            String returnedLastName = "";
            String returnedPosition = "";
            while (resultSet.next()) {
                returnedAuthority = resultSet.getString("authority");
                returnedPassword = resultSet.getString("password");
                returnedCompanyID = resultSet.getInt("id");
                returnedEnabled = resultSet.getBoolean("enabled");
                returnedFirstName = resultSet.getString("firstname");
                returnedLastName = resultSet.getString("lastname");
                returnedPosition = resultSet.getString("position");
            }
            String expectedAuthority = "employee";
            assertEquals(expectedAuthority, returnedAuthority);
            assertEquals(true, bCryptPasswordEncoder.matches(readablePassword, returnedPassword));
            assertEquals(employee.getCompany().getCompanyID(), returnedCompanyID);
            assertNotEquals(0, returnedCompanyID);
            assertEquals(true, returnedEnabled);
            assertNotEquals(0L, employee.getUserID().longValue());
            assertEquals(employee.getFirstName(), returnedFirstName);
            assertEquals(employee.getLastName(), returnedLastName);
            assertEquals(employee.getPosition(), returnedPosition);
            assertNotEquals(null, returnedPosition);
        }
    }
}