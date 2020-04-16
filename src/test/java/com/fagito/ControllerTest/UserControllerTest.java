package com.fagito.ControllerTest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fagito.ApplicationTests;
import com.fagito.controller.User_Controller;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ApplicationTests.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	@InjectMocks
    private User_Controller user_controller;
 
    private MockMvc mockMvc;
 
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(user_controller).build();
    }
 
    @Test
    public void testCreateSignupFormInvalidUser() throws Exception {
        this.mockMvc.perform(post("/api/users/signup")).andExpect(status().isOk()).andExpect(content().string("success"));
    }
}
