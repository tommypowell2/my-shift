package com.powell.controller;

import com.powell.dao.RegistrationDAO;
import com.powell.domain.Employee;
import com.powell.service.RegistrationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by tpowell on 11/13/16.
 * -_-
 */
public class EmployeeRegistrationControllerTest {

    private EmployeeRegistrationController controller;
    private MockMvc mockMvc;

    private RegistrationServiceForTest registrationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registrationService = new RegistrationServiceForTest(null);
        controller = new EmployeeRegistrationController(registrationService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void firstNameIsEmpty() throws Exception {
        String jsonMessage = "{\"employee\": {\"firstName\": \"\", \"lastName\": \"fdfd\", \"userName\": \"fdfd\", \"position\": \"Automation Engineer\", \"companyID\":\"1\"}}";
        String expectedContent = "{\"messageType\":\"error\", \"message\": \"First name is not valid.\"}";
        mockMvc.perform(post("/registerEmployee").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void lastNameIsEmpty() throws Exception {
        String jsonMessage = "{\"employee\": {\"firstName\": \"test\", \"lastName\": \"\", \"userName\": \"fdfd\", \"position\": \"Automation Engineer\", \"companyID\":\"1\"}}";
        String expectedContent = "{\"messageType\":\"error\", \"message\": \"Last name is not valid.\"}";
        mockMvc.perform(post("/registerEmployee").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void userNameIsEmpty() throws Exception {
        String jsonMessage = "{\"employee\": {\"firstName\": \"test\", \"lastName\": \"user\", \"userName\": \"\", \"position\": \"Automation Engineer\", \"companyID\":\"1\"}}";
        String expectedContent = "{\"messageType\":\"error\", \"message\": \"User name is not valid.\"}";
        mockMvc.perform(post("/registerEmployee").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void positionIsEmpty() throws Exception {
        String jsonMessage = "{\"employee\": {\"firstName\": \"test\", \"lastName\": \"user\", \"userName\": \"tuser\", \"position\": \"\", \"companyID\":\"1\"}}";
        String expectedContent = "{\"messageType\":\"error\", \"message\": \"Position is not valid.\"}";
        mockMvc.perform(post("/registerEmployee").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void companyIDIsZero() throws Exception {
        String jsonMessage = "{\"employee\": {\"firstName\": \"test\", \"lastName\": \"user\", \"userName\": \"tuser\", \"position\": \"Awesome Dev\", \"companyID\":\"0\"}}";
        String expectedContent = "{\"messageType\":\"error\", \"message\": \"Company ID is not valid.\"}";
        mockMvc.perform(post("/registerEmployee").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void companyIDIsLessThanZero() throws Exception {
        String jsonMessage = "{\"employee\": {\"firstName\": \"test\", \"lastName\": \"user\", \"userName\": \"tuser\", \"position\": \"Awesome Dev\", \"companyID\":\"-1\"}}";
        String expectedContent = "{\"messageType\":\"error\", \"message\": \"Company ID is not valid.\"}";
        mockMvc.perform(post("/registerEmployee").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void registerEmployee() throws Exception {
        String jsonMessage = "{\"employee\": {\"firstName\": \"test\", \"lastName\": \"user\", \"userName\": \"tuser\", \"position\": \"Awesome Dev\", \"companyID\":\"1\"}}";
        String expectedContent = "{\"messageType\":\"success\", \"message\": \"Registration successful\", \"employeeID\":\"1\"}";
        mockMvc.perform(post("/registerEmployee").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }
}

