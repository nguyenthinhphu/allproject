package net.luvina.insurance.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javafx.application.Application;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration

public class LoginControllerTest {
 
	  @Autowired
	    private WebApplicationContext wac;

	    private MockMvc mockMvc;

	    @Before
	    public void setUp() throws Exception {
	        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	    }

	    @Test
	    public void verifiesHomePageLoads() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.get("/"))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }
 
}
