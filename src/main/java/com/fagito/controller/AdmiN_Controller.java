package com.fagito.controller;

import java.lang.annotation.Repeatable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fagito.service.AdminService;
import com.fagito.service.command.AdminService_AddorDeleteRestaurant;
import com.fagito.service.RestaurantAnalysis;
import com.fagito.service.RestaurantAnalysisImpl;
import com.fagito.view.Add_Restaurant_UI;
import com.fagito.view.AdminOfferUI;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdmiN_Controller {
	
	
	@Autowired 
	private AdminService adminservice;
	@Autowired
	private AdminService_AddorDeleteRestaurant adminservice_addrestaurant;
    @Autowired
	private RestaurantAnalysis restaurantAnalysis;
	//set discount percentage for gold customers
	@PostMapping("/save_offer")
	public ResponseEntity<?> set_offers_details(@RequestBody AdminOfferUI admin_offer_ui)
	{
		try
		{
			String message=adminservice.set_offer(admin_offer_ui);
			return ResponseEntity.ok().body(message);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	//adding restaurants
	@PostMapping("/add_restaurant")
	public ResponseEntity<?> add_restaurants(@RequestBody Add_Restaurant_UI add_restaurant_ui)
	{
		try
		{
			String message=adminservice_addrestaurant.add_restaurant_admin(add_restaurant_ui);
			return ResponseEntity.ok().body(message);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	//deleting Restaurants
	@DeleteMapping("/delete_restaurant")
	public ResponseEntity<?> delete_restaurants(@RequestBody Add_Restaurant_UI add_restaurant_ui)
	{
		try
		{
			String message=adminservice_addrestaurant.delete_restaurant(add_restaurant_ui);
			return ResponseEntity.ok().body(message);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	@GetMapping("/restaurant_analysis/{restaurant_name}")
	public ResponseEntity<?> restaurant_analysis(@PathVariable String restaurant_name)
	{
		try
		{
			return ResponseEntity.ok().body(restaurantAnalysis.menu_analysis(restaurant_name));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}

	}
	

}
