package com.fagito.service.abstractfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.repository.CustomerRepository;
import com.fagito.repository.OrderRepository;
import com.fagito.repository.RestaurantRepository;

@Service
public class General extends Customer{

	@Autowired
	private GeneralCalculateDiscount generalCalculateDiscount;
	
	@Autowired
	private GeneralCalculateReward generalCalculateReward;
	
	public CalculateDiscountAbstract calculateDiscountRate() {
		
		return generalCalculateDiscount;
	}	
	public CalculateRewardAbstract calculateRewardPoints() {
		
		return generalCalculateReward;
	}
}
