
package com.fagito.service.visitor;

import com.fagito.model.PaymentReference;

public class PaymentElement implements ObjectElement{

	PaymentReference paymentReference;
	public PaymentElement(PaymentReference paymentReference)
	{
		this.paymentReference=paymentReference;
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