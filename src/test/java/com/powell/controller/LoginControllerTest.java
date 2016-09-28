package com.powell.controller;

import com.powell.controller.LoginController;
import com.powell.service.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    private LoginController controller;
    private MockMvc mockMvc;
    @Mock
    LoginService loginService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        controller = new LoginController(loginService);
    }

    @Test
    public void userNameIsEmpty() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/login?username= &password=test")).andExpect(content().string("Please provide valid login credentials")).andDo(print());
    }

    @Test
    public void userNameIsNull() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/login?password=test")).andExpect(content().string("Please provide valid login credentials")).andDo(print());
    }

    @Test
    public void passwordIsEmpty() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/login?username=tpowell&password= ")).andExpect(content().string("Please provide valid login credentials")).andDo(print());
    }

    @Test
    public void passwordIsNull() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/login?username=tpowell")).andExpect(content().string("Please provide valid login credentials")).andDo(print());
    }

    @Test
    public void loginFailed() throws Exception {
        when(loginService.validateUser(anyString(), anyString())).thenReturn(false);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/login?username=tpowell&password=test")).andExpect(content().string("Please provide valid login credentials")).andDo(print());
    }

    @Test
    public void loginSucceeds() throws Exception {
        when(loginService.validateUser(anyString(), anyString())).thenReturn(true);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/login?username=tpowell&password=test")).andExpect(content().string("true")).andDo(print());
    }
}
