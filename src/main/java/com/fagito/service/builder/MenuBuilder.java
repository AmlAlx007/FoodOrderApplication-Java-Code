package com.fagito.service.builder;

public interface MenuBuilder {

	public void buildVegCuisine(String restaurantId);
	public void buildNonVegCuisine(String restaurantId);
	
	public Menu getMenu();
}
