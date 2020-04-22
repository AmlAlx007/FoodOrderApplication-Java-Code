package com.fagito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fagito.service.MenuService;
import com.fagito.view.GetMenuDetails;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@PostMapping("/getByCuisine")
	public ResponseEntity<?> getMenuDetailsByCuisine(@RequestBody GetMenuDetails getMenuDetails)
	{
		try
		{
			System.out.print("Hello");
			return ResponseEntity.ok().body(menuService.getMenuByCuisine(getMenuDetails));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
}
