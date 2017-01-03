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
public class AdminDAOJDBCTest {

    @Autowired
    DataSource dataSource;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void dataSourceIsNull() {
        expectedException.expect(NullPointerException.class);
        new AdminDAOJDBC(null);
    }

    @Test
    @Sql(value = {"classpath:sql/registration/test-admin-get-company-id-successfully.sql"})
    public void getCompanyID() throws SQLException {
        AdminDAOJDBC adminDAOJDBC = new AdminDAOJDBC(dataSource);
        int companyID = adminDAOJDBC.getCompanyID("username");
        assertEquals(1, companyID);
    }

}