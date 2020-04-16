package com.fagito.service.abstractfactory;

import org.springframework.stereotype.Service;

import com.fagito.repository.CustomerRepository;
import com.fagito.repository.OrderRepository;
import com.fagito.repository.RestaurantRepository;

@Service
public abstract class Customer {

	public abstract CalculateDiscountAbstract calculateDiscountRate();
	public abstract CalculateRewardAbstract calculateRewardPoints();
}
