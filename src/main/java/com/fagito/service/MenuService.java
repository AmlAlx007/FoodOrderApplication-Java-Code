package com.fagito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.service.builder.AmericanBuilder;
import com.fagito.service.builder.ItalianBuilder;
import com.fagito.service.builder.Menu;
import com.fagito.service.builder.MenuBuilder;
import com.fagito.service.builder.Restaurant;
import com.fagito.view.GetMenuDetails;
import com.fagito.view.SendMenuDetails;
@Service
public class MenuService {

	@Autowired
	private ItalianBuilder italianBuilder;
	
	@Autowired
	private AmericanBuilder americanBuilder;
	
	public SendMenuDetails getMenuByCuisine(GetMenuDetails getMenuDetails)
	{
		MenuBuilder menuBuilder=null;
		SendMenuDetails sendMenuDetails=new SendMenuDetails();
		switch(getMenuDetails.getCuisineType())
		{
			case "Italian": menuBuilder=italianBuilder;
							break;
			case "American": menuBuilder=americanBuilder;
							break;
		}
		
		Restaurant restaurant=new Restaurant(menuBuilder);
		restaurant.constructMenu(getMenuDetails.getRestaurantId());
		Menu menu=restaurant.getMenu();
		sendMenuDetails.setNonVegMenu(menu.getNonVegCuisine());
		sendMenuDetails.setVegMenu(menu.getVegCuisine());
		return sendMenuDetails;
	}
}
