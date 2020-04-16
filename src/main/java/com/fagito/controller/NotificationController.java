package com.fagito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fagito.service.NotificationService;
import com.fagito.service.OrderService;
import com.fagito.view.UserNotiView;
import com.fagito.view.Order_Place_UI;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	//posting order
	@PostMapping("/user")
	public ResponseEntity<?> customerNotification(@RequestBody UserNotiView userNotiView)
	{
		try
		{
			return ResponseEntity.ok().body(notificationService.userNotification(userNotiView));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

}
