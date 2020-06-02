package com.fagito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fagito.service.GetAllFoodItemsService;
import com.fagito.service.GetAllRestaurantsService;
import com.fagito.view.Food_Form;
import com.fagito.view.Food_Form_UI;
import com.fagito.view.RestaurantDetails;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class GetAllItemsController {

	@Autowired
	private GetAllFoodItemsService getAllItems;
	
	@Autowired
	private GetAllRestaurantsService getAllRestaurants;
	
	@GetMapping("/allFoodItems")
	public ResponseEntity<?> allFoodItems()
	{
		try
		{
			List<Food_Form> food_form=getAllItems.getAllItems();
			
			return ResponseEntity.status(200).body(food_form);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
			
	}
	
	@GetMapping("/allRestaurant")
	public ResponseEntity<?> allRestaurants()
	{
		try
		{
			List<RestaurantDetails> restaurantDetails=getAllRestaurants.getAllRestaurants();
			
			return ResponseEntity.status(200).body(restaurantDetails);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
			
	}
}
