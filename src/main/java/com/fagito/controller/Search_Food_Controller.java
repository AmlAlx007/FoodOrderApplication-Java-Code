package com.fagito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fagito.service.FoodSearchServiceInterface;
import com.fagito.view.Food_Form;
import com.fagito.view.Food_Form_UI;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/search")
public class Search_Food_Controller {
	
	
	//FoodSearchServiceInterface and FoodSerchService
	@Autowired
	private FoodSearchServiceInterface foodSearchServiceInterface;
	
	@PostMapping("/food_search")
	public ResponseEntity<?> food_search(@RequestBody Food_Form_UI food_form_ui)
	{
		try
		{
			List<Food_Form> food_form=foodSearchServiceInterface.foodSearch(food_form_ui);
			
			return ResponseEntity.status(200).body(food_form);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
			
	}
	
	

}
