package com.powell.service;

import com.powell.dao.AdminDAO;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    public void adminDAOIsNotNull() {
        expectedException.expect(NullPointerException.class);
        new AdminService(null);
    }
}