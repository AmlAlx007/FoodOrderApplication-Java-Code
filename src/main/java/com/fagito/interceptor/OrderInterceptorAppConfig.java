package com.fagito.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Component
public class OrderInterceptorAppConfig extends WebMvcConfigurerAdapter{

	@Autowired
	OrderInterceptor orderInterceptor;
	
	@Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(orderInterceptor)
	      .addPathPatterns("/api/users/signup");
	   }
}
