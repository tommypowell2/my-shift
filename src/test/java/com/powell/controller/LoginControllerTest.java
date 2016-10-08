package com.powell.controller;

import com.powell.domain.User;
import com.powell.service.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * Created by tpowell on 9/24/16.
 * -_-
 */
public class LoginControllerTest {

    private final String expectedContent = "{\"message\":\"Please provide valid login credentials\"}";
    private LoginControllerOverride controller;
    private MockMvc mockMvc;
    @Mock
    LoginService loginService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        controller = new LoginControllerOverride(loginService, "", "");
    }

    @Test
    public void userNameIsEmpty() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/login2")).andExpect(content().json(expectedContent)).andDo(print());
    }

    @Test
    public void userNameIsNull() throws Exception {
        controller = new LoginControllerOverride(loginService, null, "");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/login2")).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void passwordIsEmpty() throws Exception {
        controller = new LoginControllerOverride(loginService, "test", "");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/login2")).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void passwordIsNull() throws Exception {
        controller = new LoginControllerOverride(loginService, "test", null);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/login2")).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void loginFailed() throws Exception {
        controller = new LoginControllerOverride(loginService, "test", "password");
        when(loginService.validateUser(anyString(), anyString())).thenReturn(false);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/login2")).andExpect(content().string(expectedContent)).andDo(print());
    }

    @Test
    public void loginSucceeds() throws Exception {
        controller = new LoginControllerOverride(loginService, "test", "password");
        when(loginService.validateUser(anyString(), anyString())).thenReturn(true);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/login2")).andExpect(content().string("{\"message\":\"true\"}")).andDo(print());
    }
}

class LoginControllerOverride extends LoginController {
    private String userName;
    private String password;


    public LoginControllerOverride(LoginService loginService, String userName, String password) {
        super(loginService);
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected User getUser(HttpServletRequest request){
        return new User(userName, password);
    }
}
