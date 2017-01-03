package com.powell.controller;

import com.powell.service.RegistrationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by tpowell on 12/17/16.
 * -_-
 */
public class AdminRegistrationControllerTest {

    private MockMvc mockMvc;
    private AdminRegistrationController controller;

    @Mock
    private RegistrationService registrationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registrationService = new RegistrationServiceForTest(null);
        controller = new AdminRegistrationController(registrationService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void firstNameIsEmpty() throws Exception {
        String jsonMessage = "{\"administrator\": {\"firstName\": \"\", \"lastName\": \"fdfd\", \"userName\": \"fdfd\", \"password\":\"secret\", \"company\": {\"companyName\": \"\"}}}";
        String expectedContent = "{\"messageType\":\"error\", \"message\": \"First name is not valid.\"}";
        mockMvc.perform(post("/registerAdmin").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void lastNameIsEmpty() throws Exception {
        String jsonMessage = "{\"administrator\": {\"firstName\": \"dfdsds\", \"lastName\": \"\", \"userName\": \"fdfd\", \"password\":\"secret\", \"company\": {\"companyName\": \"\"}}}";
        String expectedContent = "{\"messageType\":\"error\", \"message\": \"Last name is not valid.\"}";
        mockMvc.perform(post("/registerAdmin").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void userNameIsEmpty() throws Exception {
        String jsonMessage = "{\"administrator\": {\"firstName\": \"test\", \"lastName\": \"user\", \"userName\": \"\", \"password\":\"secret\", \"company\": {\"companyName\": \"\"}}}";
        String expectedContent = "{\"messageType\":\"error\", \"message\": \"User name is not valid.\"}";
        mockMvc.perform(post("/registerAdmin").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void passwordIsEmpty() throws Exception {
        String jsonMessage = "{\"administrator\": {\"firstName\": \"test\", \"lastName\": \"user\", \"userName\": \"tuser\", \"password\":\"\", \"company\": {\"companyName\": \"\"}}}";
        String expectedContent = "{\"messageType\":\"error\", \"message\": \"Password is not valid.\"}";
        mockMvc.perform(post("/registerAdmin").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void registerAdmin() throws Exception {
        String jsonMessage = "{\"administrator\": {\"firstName\": \"test\", \"lastName\": \"user\", \"userName\": \"tuser\", \"password\":\"secret\", \"company\": {\"companyName\": \"\"}}}";
        String expectedContent = "{\"messageType\":\"success\", \"message\": \"Registration successful\", \"companyID\":\"1\"}";
        mockMvc.perform(post("/registerAdmin").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMessage)).andExpect(content().string(expectedContent)).andDo(print());
    }

}