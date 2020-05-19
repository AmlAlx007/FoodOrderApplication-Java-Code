package com.fagito;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Component
@SuppressWarnings("serial")
public class FrontControllerServlet extends HttpServlet{
	
	@Autowired
	private UserFrontControllerCommand userFrontControllerCommand;
	
	private AnnotationConfigWebApplicationContext context;
	
	public FrontControllerServlet()
	{
		this.context=new AnnotationConfigWebApplicationContext();
		System.out.println("dispatcher servlet is initialized ");
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{   
		FrontControllerCommand commandObject=getCommand(request);
		commandObject.init(getServletContext(), request, response);
		commandObject.process(request);
	}
	
	private FrontControllerCommand getCommand(HttpServletRequest request)
	{
		FrontControllerCommand object=null;
		Class<?> result=null;
		Map<String,Object> objectSet=new HashMap<String, Object>();
		objectSet.put("com.fagito.UserFrontControllerCommand",userFrontControllerCommand);
		
		try
		{
			result=getFrontCommandClass(request);
			//object=(FrontControllerCommand) getFrontCommandClass(request).getDeclaredConstructor().newInstance();
			object=(FrontControllerCommand) objectSet.get(result.getName());	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return object;
	}
	@SuppressWarnings("rawtypes")
	private Class getFrontCommandClass(HttpServletRequest request) 
	{
		Class result=null;
		String[] urlPath=request.getPathInfo().split("/");
		final String commandClassName = "com.fagito." +urlPath[1]+ "FrontControllerCommand";
		try 
		{ 
			result = Class.forName(commandClassName);
		} 
		catch(Exception e)
		{
			System.out.print(e);
		}
		
		return result;
	}
	
}
