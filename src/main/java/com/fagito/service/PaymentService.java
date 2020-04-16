package com.fagito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.model.PaymentReference;
import com.fagito.repository.CustomerRepository;
import com.fagito.repository.OrderRepository;
import com.fagito.repository.RestaurantRepository;
import com.fagito.service.abstractfactory.CalculateDiscountAbstract;
import com.fagito.service.abstractfactory.CalculateRewardAbstract;
import com.fagito.service.abstractfactory.Customer;
import com.fagito.service.abstractfactory.General;
import com.fagito.service.abstractfactory.Student;
import com.fagito.service.facade.Facade;
//import com.fagito.model.PaymentReference;
//import com.fagito.repository.PaymentReferenceRepository;
import com.fagito.validator.PaymentValidator;
import com.fagito.view.Payment_Form_UI;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentValidator paymentValidator;
	
	@Autowired 
	private Student student;
	
	@Autowired 
	private General general;
	
	@Autowired
	private Facade facade;
	
	public String verify_payment(Payment_Form_UI payment_form_ui) throws Exception
	{
		
		String payString;//pymtRef;
		PaymentReference paymentReference=new PaymentReference();

		if(paymentValidator.verify_account(payment_form_ui.getAccount_no()))
		{
			if(paymentValidator.verify_date(payment_form_ui.getMonth(),payment_form_ui.getYear()))
			{
				if(paymentValidator.verify_cvv(payment_form_ui.getCvv()))
				{
					payString="PAIDFAGITO"+String.valueOf(payment_form_ui.getAccount_no()).substring(0,5);
					
					paymentReference.setPayment_ref(payString);
					//paymentReferenceRepository.save(paymentReference);
					
//					Customer customer=student;
//					CalculateDiscountAbstract calculateDiscountAbstract=customer.calculateDiscountRate();
//					calculateDiscountAbstract.calculateDiscountRate(2000,"R100");
//					
//					Customer customer1=general;
//					CalculateRewardAbstract calculateRewardAbstract=customer1.calculateRewardPoints();
//					calculateRewardAbstract.calculateRewardPoints(2000,"C100");
					
					
					//Facade facade1=facade.FacadeCreateProduct("S","discount");
					
					//facade1.calculateDiscountAbstract.calculateDiscountRate(2000,"R100");
					
					return payString;
					
				}
			}
		}
		throw new Exception("Payment Failure");
	}

}
