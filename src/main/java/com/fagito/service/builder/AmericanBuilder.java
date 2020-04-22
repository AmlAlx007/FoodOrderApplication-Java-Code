package com.fagito.service.builder;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fagito.model.Food;
import com.fagito.repository.FoodRepository;
import com.fagito.repository.MenuRepository;

@Component
public class AmericanBuilder implements MenuBuilder {

	@Autowired
	private MenuRepository menuRepository;
	@Autowired 
	private FoodRepository foodRepository;
	
	private List<Food> vegFoodList;
	private List<Food> nonVegFoodList;
	private Menu menu;
	
	public AmericanBuilder() {
		this.menu=new Menu();
	}
	
	public void buildVegCuisine(String restaurantId)
	{
		String menuId=menuRepository.findMenuId(restaurantId);
		List<Food> foodList=foodRepository.findByMenuId(menuId);
		
		Iterator<Food> foodIterator=foodList.iterator();
		while(foodIterator.hasNext())
		{
			Food food=foodIterator.next();
			if(food.getVeg_or_nonveg()==0 && food.getFood_cuisine().compareTo("American")==0)
			{
				vegFoodList.add(food);
			}
		}
		this.menu.vegCuisine(vegFoodList);	
	}
	
	public void buildNonVegCuisine(String restaurantId)
	{
		String menuId=menuRepository.findMenuId(restaurantId);
		List<Food> foodList=foodRepository.findByMenuId(menuId);
		
		Iterator<Food> foodIterator=foodList.iterator();
		while(foodIterator.hasNext())
		{
			Food food=foodIterator.next();
			if(food.getVeg_or_nonveg()==1 && food.getFood_cuisine().compareTo("American")==0)
			{
				nonVegFoodList.add(food);
			}
		}
		this.menu.vegCuisine(nonVegFoodList);
	}
	
	public Menu getMenu()
	{
		return this.menu;
	}
}
