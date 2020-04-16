package com.fagito.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fagito.dto.PaymentGoldDTO;
import com.fagito.service.RegisterService;
import com.fagito.view.Payment_Gold_UI;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/membership_register")
public class Gold_Membership_Controller {
	
	@Autowired
	private RegisterService register_service;
	
	@PostMapping("/register")
	public ResponseEntity<?> register_membership(@RequestBody Payment_Gold_UI payment_gold_ui)
	{
		try
		{
			PaymentGoldDTO paymentgoldDTO=new PaymentGoldDTO();
			BeanUtils.copyProperties(payment_gold_ui, paymentgoldDTO);
			return ResponseEntity.ok().body(register_service.set_membership(paymentgoldDTO));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

}
