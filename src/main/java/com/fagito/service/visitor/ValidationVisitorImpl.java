package com.fagito.service.visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fagito.validator.PaymentValidator;
import com.fagito.validator.UserValidator;

@Component
public class ValidationVisitorImpl implements ValidationVisitor {

	@Autowired
	private PaymentValidator paymentValidator;
	public boolean visit(CustomerElement customerElement) throws Exception
	{
		try {
			UserValidator userValidator=new UserValidator();
			return userValidator.validate(customerElement.signUpDTO);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	public boolean visit(PaymentElement paymentElement) throws Exception
	{
		try
		{
			return paymentValidator.validate(paymentElement.paymentReference);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
