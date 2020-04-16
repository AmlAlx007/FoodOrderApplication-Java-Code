package com.fagito.service.command;

import org.springframework.stereotype.Service;

import com.fagito.view.Add_Restaurant_UI;
@Service
public class Admin_AddRestaurant implements Command_Restaurant{
	
	RestaurantCommand restaurant;
	public Admin_AddRestaurant(RestaurantCommand restaurant)
	{
		this.restaurant=restaurant;
	}
	public String execute(Add_Restaurant_UI add_restaurant_ui) throws Exception
	{
		try
		{
			return this.restaurant.add(add_restaurant_ui);
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

}
