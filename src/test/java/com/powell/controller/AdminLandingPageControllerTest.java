package com.powell.controller;

import com.powell.service.AdminService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by tpowell on 10/23/16.
 * -_-
 */
public class AdminLandingPageControllerTest {


    private AdminLandingPageController controller;
    private MockMvc mockMvc;
    @Mock
    private AdminService adminService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        controller = new AdminLandingPageController(adminService);
    }

    @Test
    public void getCompanyID() throws Exception {
        String jsonMessage = "{\"username\":\"test\"}";
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        String expectedContent = "{\"companyID\":\"1\"}";
        when(adminService.getCompanyID(anyString())).thenReturn(1);
        mockMvc.perform(post("/getCompanyID").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void companyIDIsZero() throws Exception {
        String jsonMessage = "{\"username\":\"test\"}";
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        String expectedContent = "{\"error\":\"Could not find companyID\"}";
        when(adminService.getCompanyID(anyString())).thenReturn(0);
        mockMvc.perform(post("/getCompanyID").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }
}