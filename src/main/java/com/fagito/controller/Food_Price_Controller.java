package com.fagito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fagito.service.FoodPriceService;
import com.fagito.view.Food_Price;
import com.fagito.view.Food_Price_UI;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/food_price")
public class Food_Price_Controller {
	
	@Autowired
	private FoodPriceService food_price_service;
	//get food discount price
	@PostMapping("/specific_food")
	public ResponseEntity<?> food_price_search(@RequestBody Food_Price_UI food_price_ui)
	{
		try {
			return ResponseEntity.ok().body(food_price_service.get_food_details(food_price_ui));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	//get all food discounts based on user id front end has to be made
	/*@PostMapping("/{customer_id}")
	public List<Food_Price> all_food_price_search(@PathVariable String customer_id)
	{
		return food_price_service.get_all_food_price(customer_id);
	}*/
}
