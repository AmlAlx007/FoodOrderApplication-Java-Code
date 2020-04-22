package com.fagito.service.builder;

public class Restaurant {

	private MenuBuilder menuBuilder;
	
	public Restaurant(MenuBuilder menuBuilder)
	{
		this.menuBuilder=menuBuilder;
	}
	
	public Menu getMenu()
	{
		return this.menuBuilder.getMenu();
	}
	
	public void constructMenu(String restaurantId)
	{
		this.menuBuilder.buildVegCuisine(restaurantId);
		this.menuBuilder.buildNonVegCuisine(restaurantId);
	}
	
}
