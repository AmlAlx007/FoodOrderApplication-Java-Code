package com.fagito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class Application {

	@Autowired
	private FrontControllerServlet frontControllerServlet;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	
	
	@Bean
	protected ServletRegistrationBean<HttpServlet> dispatcherServletBean() throws ServletException{
		ServletRegistrationBean<HttpServlet> dispatcherServlet= new ServletRegistrationBean<HttpServlet>(frontControllerServlet, "/newServlet/*");
		dispatcherServlet.setLoadOnStartup(1);
		return dispatcherServlet;
	}
}
