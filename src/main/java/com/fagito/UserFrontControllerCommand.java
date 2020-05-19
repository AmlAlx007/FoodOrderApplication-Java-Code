package com.fagito;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class UserFrontControllerCommand extends FrontControllerCommand{

	public void process(HttpServletRequest request) throws IOException, ServletException
	{
		
		RequestDispatcher disptach=context.getRequestDispatcher("/User/signup");
		disptach.forward(request, this.response);
	}
}
