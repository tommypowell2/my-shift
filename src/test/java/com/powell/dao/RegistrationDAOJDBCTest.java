package com.powell.dao;

import com.powell.domain.Employee;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.sql.*;

import static org.junit.Assert.*;

/**
 * Created by tpowell on 11/23/16.
 * -_-
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RegistrationDAOJDBCTest {

    @Autowired
    private DataSource dataSource;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Sql("classpath:test-registration.sql")
    @Test
    public void registrationSucceeds() throws SQLException {
        Employee employee = new Employee("test", "user", "tuser", "Test Dummy");
        RegistrationDAO registrationDAO = new RegistrationDAOJDBC(dataSource);
        registrationDAO.registerEmployee(employee);
        String sql = "select employeeID from employee where username = 'tuser'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            long returnedEmployeeID = 0;
            if(resultSet.next()){
                returnedEmployeeID = resultSet.getLong("EmployeeID");
            }
            assertNotNull(employee.getEmployeeID());
            assertEquals(new Long(returnedEmployeeID), employee.getEmployeeID());
        }
    }


    @Sql(value = {"classpath:test-registration.sql","classpath:sql/registration/test-user-exists-registration.sql"})
    @Test
    public void registrationFailsIfUserAlreadyExists() throws SQLException {
        expectedException.expect(SQLException.class);
        Employee employee = new Employee("test", "user", "tuser", "Test Dummy");
        RegistrationDAO registrationDAO = new RegistrationDAOJDBC(dataSource);
        registrationDAO.registerEmployee(employee);
    }
}