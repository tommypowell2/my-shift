package com.powell.controller;

import static org.junit.Assert.*;

/**
 * Created by tpowell on 10/23/16.
 * -_-
 */
public class AdminLandingPageControllerTest {

    /*
    *
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
    *
    * */

}