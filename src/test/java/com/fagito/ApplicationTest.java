package com.fagito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fagito.view.CustomerForm;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@EnableWebMvc
@AutoConfigureMockMvc
public class ApplicationTest {
	
	@Autowired
	private MockMvc mockMvc;

	private CustomerForm customerForm;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testsignupUser() throws Exception {

		String expected = "success";
		customerForm = new CustomerForm();
		customerForm.setCustomer_name("AMAL ALEX");
		customerForm.setEmail("19121512@studentmail.ul.ie");
		customerForm.setPassword("AML@ul007");

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(customerForm);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/User/signup")
				.contentType(MediaType.APPLICATION_JSON).content(jsonString).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String str = result.getResponse().getContentAsString();
		assertEquals(expected, str);

	}

	@Test
	public void contextLoads() {

	}

}
