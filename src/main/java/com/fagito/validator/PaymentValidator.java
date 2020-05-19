package com.fagito.validator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.model.PaymentReference;
import com.fagito.repository.PaymentReferenceRepository;

@Service
public class PaymentValidator {
	
	@Autowired
	private PaymentReferenceRepository paymentReferenceRepository;
	
	public boolean validate(PaymentReference paymentReference) throws Exception
	{
		int result=paymentReferenceRepository.findRecord(paymentReference.getPayment_ref());
		if(result==1)
		{
			return true;
		}
		throw new Exception("Invalid Payment Reference Id!!");
	}
	
	
	
	
	public boolean verify_account(long acc_no)
	{
		String acc=String.valueOf(acc_no);
		if(acc.length()==12)
			return true;
		else
			return false;
	}

	@SuppressWarnings("deprecation")
	public boolean verify_date(int month,int year)
	{
		Date date=new Date();
		if(year>=date.getYear())
		{
			if(month>=date.getMonth())
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean verify_cvv(int cvv)
	{
		String cvv_value=String.valueOf(cvv);
		if(cvv_value.length()==3)
			return true;
		
		return false;
	}
}
