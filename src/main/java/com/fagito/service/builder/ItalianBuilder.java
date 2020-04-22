package com.fagito.service.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fagito.model.Food;
import com.fagito.repository.FoodRepository;
import com.fagito.repository.MenuRepository;

@Component
public class ItalianBuilder implements MenuBuilder {

	@Autowired
	private MenuRepository menuRepository;
	@Autowired 
	private FoodRepository foodRepository;
	
	
	private Menu menu;
	
	public ItalianBuilder() {
		this.menu=new Menu();
	}
	
	public void buildVegCuisine(String restaurantId)
	{
		String menuId=menuRepository.findMenuId(restaurantId);
		List<Food> foodList=foodRepository.findByMenuId(menuId);
		
		List<Food> vegFoodList=new ArrayList<Food>();
		
		Iterator<Food> foodIterator=foodList.iterator();
		while(foodIterator.hasNext())
		{
			Food food=foodIterator.next();
			if(food.getVeg_or_nonveg()==0 && food.getFood_cuisine().compareTo("Italian")==0)
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
		
		List<Food> nonVegFoodList=new ArrayList<Food>();
		
		Iterator<Food> foodIterator=foodList.iterator();
		while(foodIterator.hasNext())
		{
			Food food=foodIterator.next();
			if(food.getVeg_or_nonveg()==1 && food.getFood_cuisine().compareTo("Italian")==0)
			{
				nonVegFoodList.add(food);
			}
		}
		this.menu.nonVegCuisine(nonVegFoodList);
	}
	
	public Menu getMenu()
	{
		return this.menu;
	}
}
