package com.fagito.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fagito.model.Order_Item_Record;
import com.fagito.repository.FoodRepository;
import com.fagito.repository.OrderRepository;
import com.fagito.repository.Order_Item_Record_Repository;
import com.fagito.repository.RestaurantRepository;
import com.fagito.view.Restaurant_Analysis;

@Service
public class RestaurantAnalysisImpl implements RestaurantAnalysis {
	
		@Autowired
		private RestaurantRepository restaurantRepository;
		@Autowired
		private OrderRepository orderRepository;
		@Autowired
		private Order_Item_Record_Repository order_Item_Record_Repository;
		@Autowired
		private FoodRepository food_repository;
	
	public Restaurant_Analysis menu_analysis(String restaurant_name)
	{
		
		String restaurant_id,food_id,food_name;
		int qty,max_qty=0,i=0;
		
		Restaurant_Analysis rest_analysis=new Restaurant_Analysis();
		Map<String,Integer> map_food_list=new HashMap<String,Integer>();
		List<String> food_id_list=new ArrayList<String>();
		List<String> food_name_list=new ArrayList<String>();
		
		restaurant_id=restaurantRepository.findByRestaurantName(restaurant_name);
		
		List<String> order_id_list=orderRepository.getByRestaurantId(restaurant_id);
		
		Iterator<String> order_id_iterator=order_id_list.iterator();
		
		while(order_id_iterator.hasNext())
		{
			List<Order_Item_Record> order_item_record_list=order_Item_Record_Repository.findByOrderId(order_id_iterator.next());
			
			Iterator<Order_Item_Record> order_item_record_iterator=order_item_record_list.iterator();
			
			while(order_item_record_iterator.hasNext())
			{
				Order_Item_Record order_item_record_object=order_item_record_iterator.next();
				food_id=order_item_record_object.getFood_id();
				qty=order_item_record_object.getQuantity();
				
				if(map_food_list.containsKey(food_id))
				{
					map_food_list.put(food_id,map_food_list.get(food_id)+qty);
				}
				else
					map_food_list.put(food_id,qty);
				
			}
		}
		
		for (Map.Entry<String,Integer> entry : map_food_list.entrySet())
		{
			if(max_qty<=entry.getValue())
			{
				max_qty=entry.getValue();
			}
		}
		for (Map.Entry<String,Integer> entry : map_food_list.entrySet())
		{
			if(max_qty==entry.getValue())
				food_id_list.add(entry.getKey());
		}
		//list of most_ordered
		for(i=0;i<food_id_list.size();i++)
		{
			food_name=food_repository.getById(food_id_list.get(i));
			food_name_list.add(food_name);
		}
		rest_analysis.setMost_ordered(food_name_list);
		
		return rest_analysis;
		
	}
	

}
