package com.fagito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fagito.service.PaymentService;
import com.fagito.view.Payment_Form_UI;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired 
	private PaymentService paymentService;
	
	@GetMapping("/verify")
	public ResponseEntity<?> Payment_Api(@RequestBody Payment_Form_UI payment_form_ui)
	{
		try
		{
			return ResponseEntity.ok().body(paymentService.verify_payment(payment_form_ui));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
	}

}
