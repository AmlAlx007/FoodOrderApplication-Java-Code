package com.fagito.service.visitor;

import com.fagito.dto.SignUpDTO;


public class CustomerElement implements ObjectElement{
	
	SignUpDTO signUpDTO;
	public CustomerElement(SignUpDTO signUpDTO)
	{
		this.signUpDTO=signUpDTO;
	}
	
	public boolean accept(ValidationVisitor validationVisitor) throws Exception
	{
		try
		{
			return validationVisitor.visit(this);
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}
	
}