package com.powell.dao;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by tpowell on 10/9/16.
 * -_-
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(value = {"classpath:LoginDAOJDBCTest.sql"})
public class LoginDAOJDBCTest {

    @Autowired
    DataSource dataSource;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void dataSourceIsNull() {
        expectedException.expect(NullPointerException.class);
        new LoginDAO(null);
    }

    @Test
    public void userNotValidated() throws SQLException {
        LoginDAO loginDAO = new LoginDAO(dataSource);
        assertEquals(false, loginDAO.validateUser("username", "password"));
    }

    @Test
    @Sql(value = {"classpath:LoginDAOJDBCTest.sql", "classpath:userValidated.sql"})
    public void userValidated() throws SQLException {
        LoginDAO loginDAO = new LoginDAO(dataSource);
        assertEquals(true, loginDAO.validateUser("username", "password"));
    }

}