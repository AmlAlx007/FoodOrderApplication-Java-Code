package com.fagito.exception;

public class UserExisitingException extends RuntimeException
{
	public UserExisitingException(String exception)
	{
		super(exception);
	}
	
}