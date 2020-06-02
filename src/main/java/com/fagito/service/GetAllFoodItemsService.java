package com.fagito.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.fagito.model.Food;
import com.fagito.model.Menu;
import com.fagito.model.Restaurant;
import com.fagito.repository.FoodRepository;
import com.fagito.repository.MenuRepository;
import com.fagito.repository.RestaurantRepository;
import com.fagito.view.Food_Form;

@Service
public class GetAllFoodItemsService {

	@Autowired
	private FoodRepository foodRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public List<Food_Form> getAllItems()
	{
		List<Food_Form> foodFormList=new ArrayList<Food_Form>();
		
		List<Food> foodItemsList=foodRepository.findAll();
		List<Menu> menuList=new ArrayList<Menu>();
		List<Restaurant> restaurantsList=new ArrayList<Restaurant>();
		
		menuList=getMenuList(foodItemsList);
		HashMap<String,ArrayList<Menu>> restaurantMenuList= getRestaurantMenuList(menuList);
		//findByIdRestaurantId
		
		
		
		foodFormList=null;
		return foodFormList;
		
	}
	public List<Menu> getMenuList(List<Food> food_list)
	{
		List<Menu> menu_list=new ArrayList<Menu>();
		Food food_object=null;
		Iterator<Food> food_iterate=food_list.iterator();
		while(food_iterate.hasNext())
		{
			food_object=food_iterate.next();
			Menu menu_object=menuRepository.findByMenuId(food_object.getMenu_id());
			if(!menu_list.contains(menu_object))
				menu_list.add(menu_object);
		}
		return menu_list;
	}
	
	public HashMap<String,ArrayList<Menu>> getRestaurantMenuList(List<Menu> menuList)
	{
		HashMap<String,ArrayList<Menu>> restaurantMenu=new HashMap<String, ArrayList<Menu>>();
		ArrayList<Menu> restaurantMenuList;
		Iterator<Menu> menuListIterator=menuList.iterator();
		while(menuListIterator.hasNext())
		{
			restaurantMenuList=new ArrayList<Menu>();
			Menu menuObject=(Menu)menuListIterator.next();
			Restaurant restaurantObject=new Restaurant();
			
			restaurantObject=restaurantRepository.findByRestaurantId(menuObject.getRestaurant_id());
						
			if(restaurantMenu.containsKey(restaurantObject.getRestaurant_id()))
			{
				restaurantMenuList=restaurantMenu.get(restaurantObject.getRestaurant_id());
			}
			
			restaurantMenuList.add(menuObject);
			restaurantMenu.put(restaurantObject.getRestaurant_id(),restaurantMenuList);
		}
		
		return restaurantMenu;
	}
	
}
