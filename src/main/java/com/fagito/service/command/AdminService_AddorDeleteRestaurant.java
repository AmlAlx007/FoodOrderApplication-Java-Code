package com.fagito.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fagito.view.Add_Restaurant_UI;

@Service
public class AdminService_AddorDeleteRestaurant {
	
	@Autowired
	private AdminService_Switch adminservice_switch;
	@Autowired
	private RestaurantCommand restaurantCommand;
	
	public String add_restaurant_admin(Add_Restaurant_UI add_restaurant_ui) throws Exception
	{
			
			Command_Restaurant add_rest=new Admin_AddRestaurant(restaurantCommand);
			adminservice_switch.setCommand_Restaurant(add_rest);
			return adminservice_switch.switchService(add_restaurant_ui);
		
	}
	
	public String delete_restaurant(Add_Restaurant_UI add_Restaurant_UI) throws Exception
	{
		
		Command_Restaurant delete_rest=new Admin_DeleteRestaurant(restaurantCommand);
		adminservice_switch.setCommand_Restaurant(delete_rest);
		return adminservice_switch.switchService(add_Restaurant_UI);
	}
}


