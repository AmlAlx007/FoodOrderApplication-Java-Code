package com.fagito.service;

import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fagito.dto.PaymentGoldDTO;
import com.fagito.model.Customer;
import com.fagito.model.PaymentGold;
import com.fagito.repository.CustomerRepository;
import com.fagito.repository.PaymentGoldRepository;

@Service
public class RegisterService{

	@Autowired
	private PaymentGoldRepository payment_gold_repository;
	@Autowired
	private CustomerRepository customerRepository;

	@Value("${spring.user.gold.success}")
	String successRegister;
	@Value("${spring.user.gold.payment.fail}")
	String errorRegister;

	@SuppressWarnings("deprecation")
	public String set_membership(PaymentGoldDTO paymentgoldDTO) throws Exception {
		   
			
			PaymentGold payment_gold = new PaymentGold();
			String pay_id = payment_gold_repository.findLastRecord();
			if (pay_id == null)
				payment_gold.setPay_id("P100");
			else
				payment_gold.setPay_id("P" + String.valueOf(Integer.parseInt(pay_id.substring(1)) + 1));
			if (paymentgoldDTO.getPay_result().toLowerCase().equals("success")) {
				payment_gold.setCustomer_id(paymentgoldDTO.getCustomer_id());
				payment_gold.setPay_result("success");
				payment_gold_repository.save(payment_gold);

				Customer customer_object = customerRepository.findByCustomerId(paymentgoldDTO.getCustomer_id());

				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.MONTH, cal.getTime().getMonth() + paymentgoldDTO.getMonths());
				Date date = new Date(cal.getTimeInMillis());
				customer_object.setValidity(date);
                customer_object.setMembership(paymentgoldDTO.getMembership());
                
				customerRepository.save(customer_object);
	
				return successRegister;
			} else {
				throw new Exception(errorRegister);
			}
	}

}
