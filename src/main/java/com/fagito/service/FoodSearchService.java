package com.fagito.service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.model.Food;
import com.fagito.model.Menu;
import com.fagito.model.Restaurant;
import com.fagito.model.RestaurantRecord;
import com.fagito.repository.FoodRepository;
import com.fagito.repository.MenuRepository;
import com.fagito.repository.RestaurantRecordRepository;
import com.fagito.repository.RestaurantRepository;
import com.fagito.service.iterator.IteratorInterface;
import com.fagito.service.iterator.Repository;
import com.fagito.view.Food_Form;
import com.fagito.view.Food_Form_UI;

@Service
public class FoodSearchService implements FoodSearchServiceInterface{
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired 
	private FoodRepository foodRepository;
	@Autowired
	private RestaurantRecordRepository restaurantrecordRepository;
	
	Map<String,Float>distance_map=new HashMap<String,Float>();
	
	
	
	@SuppressWarnings("deprecation")
	public List<Food_Form> foodSearch(Food_Form_UI food_form_ui) throws Exception 
	{
		int distance=10,i;
		boolean is_gold;
		String restaurant_id,menu_id;
		List<Food_Form> food_form=new ArrayList<Food_Form>();
		List<Menu> menu_list=null;
		List<Restaurant> restaurant_list=null;
		
		List<String> list = Arrays.asList("1","2","3","4","5","6","7","8","9","0");
		String string = food_form_ui.getFood_name();
		boolean match = list.stream().anyMatch(string::contains);
		if(match)
			throw new Exception("Food Name Not Proper");
		  
		
		
		List<Food> food_list=foodRepository.findByName(food_form_ui.getFood_name().toLowerCase());
		
		int val= !food_list.isEmpty() ? 0:1;
		
		switch(val)
		{
			case 0:	menu_list=this.getMenuList(food_list);
					restaurant_list=this.getRestaurantList(menu_list);
			break;
			case 1: food_list=foodRepository.findAll();
					menu_list=this.getMenuList(food_list);
					restaurant_list=this.getRestaurantList(menu_list);
			break;
		}
		
		restaurant_list=get_restaurant_with_distance(restaurant_list,food_form_ui.getLatitude(),food_form_ui.getLongitude(),distance);
		if(restaurant_list.size()==0)
			throw new Exception("No Restaurants with Specified Food Name within the limit");
		//Iterator for Food Objects-Food_List
		List<Object> object_list_food=new ArrayList<Object>();
		for(i=0;i<food_list.size();i++)
			object_list_food.add(food_list.get(i));
		Repository food_repo=new Repository(object_list_food);
		
		//Iterator for Menu Objects-Menu List
		List<Object> object_list_menu=new ArrayList<Object>();
		for(i=0;i<menu_list.size();i++)
			object_list_menu.add(menu_list.get(i));
		Repository menu_repo=new Repository(object_list_menu);
		
		//Iterator for Restaurant Objects-Restaurant List
		List<Object> object_list_restaurant=new ArrayList<Object>();
		for(i=0;i<restaurant_list.size();i++)
			object_list_restaurant.add(restaurant_list.get(i));
		Repository restaurant_repo=new Repository(object_list_restaurant);
		
		
		for(IteratorInterface iter_food=food_repo.getIterator();iter_food.hasNext();)
		{
			Food_Form food_form_object=new Food_Form();
			Food food_object=(Food)iter_food.next();
			
			menu_id=(String)food_object.getMenu_id();
			for(IteratorInterface iter_menu=menu_repo.getIterator();iter_menu.hasNext();)
			{
				Menu menu_object=(Menu)iter_menu.next();
				if(menu_object.getMenu_id().equals(menu_id))
				{
					restaurant_id=menu_object.getRestaurant_id();
					
					for(IteratorInterface iter_restaurant=restaurant_repo.getIterator();iter_restaurant.hasNext();)
					{
						Timestamp ts=new Timestamp(System.currentTimeMillis());
						Restaurant restaurant_object=(Restaurant)iter_restaurant.next();
						if(restaurant_object.getRestaurant_id().equals(restaurant_id))
						{
							food_form_object.setRestaurant_id(restaurant_object.getRestaurant_id());
							food_form_object.setFood_id(food_object.getFood_id());
							food_form_object.setRestaurant_name(restaurant_object.getRestaurant_name());;
							food_form_object.setFood_name(food_object.getFood_name());
							food_form_object.setTime(restaurant_object.getOpening_time().getHours()+"-"+(restaurant_object.getClosing_time().getHours()-12));
							//System.out.print(restaurantrecordRepository.findIsGold(restaurant_object.getRestaurant_id()));
							is_gold=restaurantrecordRepository.getIsGold(restaurant_object.getRestaurant_id());
							if(is_gold)
								food_form_object.setIs_gold(1);
							else
								food_form_object.setIs_gold(0);
							
							if(ts.getHours()>=restaurant_object.getOpening_time().getHours() && ts.getHours()<=restaurant_object.getClosing_time().getHours())
							{
								food_form_object.setOpenorclose("Open");
							}
							else
							{
								food_form_object.setOpenorclose("Close");
							}
							food_form_object.setDistance(distance_map.get(restaurant_object.getRestaurant_id()));
							food_form.add(food_form_object);
						}
					}
				}
			}	
		}
		return food_form;
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
	
	public List<Restaurant> getRestaurantList(List<Menu> menu_list)
	{
		List<Restaurant> restaurant_list=new ArrayList<Restaurant>();
		
		Iterator<Menu> menu_iterator=menu_list.iterator();
		while(menu_iterator.hasNext())
		{
			restaurant_list.add(restaurantRepository.findByRestaurantId(menu_iterator.next().getRestaurant_id()));
		}
		
		return restaurant_list;
	}
	
	public List<Restaurant> get_restaurant_with_distance(List<Restaurant> restaurant_list, double latitude,double longitude,int distance)
	{
		double actual_distance;
		RestaurantRecord restaurantRecord=new RestaurantRecord();
		List<Restaurant> restaurant_new_list=new ArrayList<Restaurant>();
		Iterator<Restaurant> restaurant_iterator=restaurant_list.iterator();
		while(restaurant_iterator.hasNext())
		{
			Restaurant restaurant_object=restaurant_iterator.next();
			restaurantRecord=restaurantrecordRepository.findRestaurantRecord(restaurant_object.getRestaurant_id());
			actual_distance=get_the_distance(latitude,longitude,restaurantRecord.getLatitude(),restaurantRecord.getLongitude());
			if((int)actual_distance<=distance)
			{
				restaurant_new_list.add(restaurant_object);
				distance_map.put(restaurant_object.getRestaurant_id(),(float)distance);
			}
		}
		return restaurant_new_list;
		
	}
	private static double get_the_distance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;
			return dist;
		}
		
	}
}
	
	
	

