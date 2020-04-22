package com.fagito.service.builder;

import java.util.List;

import com.fagito.model.Food;

public class Menu implements MenuPlan{

	private List<Food> vegFoodList;
	private List<Food> nonVegFoodList;

	
	public void vegCuisine(List<Food> foodList)
	{
		this.vegFoodList=foodList;
	}
	public void nonVegCuisine(List<Food> foodList)
	{
		this.nonVegFoodList=foodList;
	}
	
	public List<Food> getVegCuisine()
	{
		return this.vegFoodList;
	}
	public List<Food> getNonVegCuisine()
	{
		return this.nonVegFoodList;
	}
	
}
