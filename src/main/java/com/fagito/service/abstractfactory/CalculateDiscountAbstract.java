package com.fagito.service.abstractfactory;


import org.springframework.stereotype.Service;



@Service
public abstract class CalculateDiscountAbstract {
	
	
	public abstract float calculateDiscountRate(float actual_amount,String restaurantId);
}
