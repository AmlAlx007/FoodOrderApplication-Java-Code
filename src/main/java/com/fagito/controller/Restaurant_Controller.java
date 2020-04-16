package com.fagito.controller;
   
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import com.fagito.service.RestaurantService;
   
@RestController
@RequestMapping("/api/restaurant") 
public class Restaurant_Controller {
	
   @Autowired 
   private RestaurantService restaurantService;
   
   @GetMapping("/{restaurantId}")
   public ResponseEntity<?> get_current_orders(@PathVariable String restaurantId) { 
	   try { 
		   return ResponseEntity.ok().body(restaurantService.getCurrentOrders(restaurantId));
		  	}
		   catch(Exception e) { 
			   return ResponseEntity.status(500).body("Error");
		  }
   }
	   
	   @PostMapping("/acceptOrder/{orderId}")
	   public ResponseEntity<?> acceptOrder(@PathVariable String orderId) { 
		   try { 
			   return ResponseEntity.ok().body(restaurantService.acceptOrder(orderId));
			  	}
	   catch(Exception e) { 
		   return ResponseEntity.status(500).body("Error");
	  }

   }
} 