package com.fagito.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.model.Restaurant;
import com.fagito.repository.RestaurantRatingRepository;
import com.fagito.repository.RestaurantRepository;
import com.fagito.view.RestaurantDetails;

@Service
public class GetAllRestaurantsService {

	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantRatingRepository restaurantRatingRepository;
	
	
	public List<RestaurantDetails> getAllRestaurants()
	{
		int rating;
		ArrayList<Restaurant> restaurantsList=new ArrayList<Restaurant>();
		List<RestaurantDetails> result=new ArrayList<RestaurantDetails>();
		
		restaurantsList=(ArrayList<Restaurant>)restaurantRepository.findAll();
		
		Iterator<Restaurant> restaurantListIterator=restaurantsList.iterator();
		while(restaurantListIterator.hasNext())
		{
			RestaurantDetails restaurantDetails=new RestaurantDetails();
			Restaurant restaurantObject=(Restaurant)restaurantListIterator.next();
			
			rating=restaurantRatingRepository.findByIdRestaurantId(restaurantObject.getRestaurant_id());
			restaurantDetails.setRestaurantName(restaurantObject.getRestaurant_name());
			restaurantDetails.setCuisine(restaurantObject.getCusine_type());
			restaurantDetails.setRestaurantRating(rating);
			
			result.add(restaurantDetails);
			
		}
		
		return result;
	}
}
