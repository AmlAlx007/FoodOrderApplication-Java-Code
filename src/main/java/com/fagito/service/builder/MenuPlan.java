package com.fagito.service.builder;

import java.util.List;

import com.fagito.model.Food;

public interface MenuPlan {

	public void vegCuisine(List<Food> foodList);
	public void nonVegCuisine(List<Food> foodList);
	
}
