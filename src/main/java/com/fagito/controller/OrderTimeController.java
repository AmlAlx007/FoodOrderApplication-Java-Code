package com.fagito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fagito.view.Calculate;

@RestController
@RequestMapping("/api/OrderTime")
public class OrderTimeController {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@PostMapping("/calculate")
	public ResponseEntity<?> orderTimeCalculate(@RequestBody Calculate calculate)
	{
		
		String result=null;
		
		try
		{
			result=restTemplate.postForObject("http://127.0.0.1:5002/orderTime", calculate, String.class);
			return ResponseEntity.ok().body("Time Delay for deliver is: "+result);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
	}

}
