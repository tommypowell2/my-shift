package com.powell.service;

import com.powell.dao.AdminDAO;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by tpowell on 10/9/16.
 * -_-
 */
public class AdminServiceTest {

    @Mock
    AdminDAO adminDAO;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void loginDAOIsNotNull() {
        expectedException.expect(NullPointerException.class);
        new AdminService(null);
    }

    @Test
    public void validateUserReturnsFalseWhenUserNotFound(){
        AdminService adminService = new AdminService(adminDAO);
        assertEquals(false, adminService.validateUser("", ""));
    }

    @Test
    public void validateUserReturnsTrueWhenUserFound() throws SQLException {
        when(adminDAO.validateUser(anyString(), anyString())).thenReturn(true);
        AdminService adminService = new AdminService(adminDAO);
        assertEquals(true, adminService.validateUser("", ""));
    }
}