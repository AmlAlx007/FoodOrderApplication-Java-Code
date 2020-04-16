package com.fagito.service.abstractfactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.model.Restaurant;
import com.fagito.repository.RestaurantRepository;


@Service
public class StudentCalculateDiscount extends CalculateDiscountAbstract {
	@Autowired
	RestaurantRepository restaurantRepository;
	
	
	public float calculateDiscountRate(float actual_amount,String restaurantId) {
		
		Restaurant record=restaurantRepository.findByRestaurantId(restaurantId);
		
		
		float discount_value=(float)(record.getStudent_discount()/100.0);
		float discount=actual_amount-(discount_value*actual_amount);
		System.out.println(discount);
		return discount;
	}
}
