package com.fagito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fagito.service.OrderService;
import com.fagito.view.Order_Place_UI;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/order")
public class Order_Controller {

	@Autowired
	private OrderService orderservice;
	//posting order
	@PostMapping("/post_order")
	public ResponseEntity<?> order_place(@RequestBody Order_Place_UI order_place_ui)
	{
		try
		{
			return ResponseEntity.ok().body(orderservice.place_order(order_place_ui));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	
}
