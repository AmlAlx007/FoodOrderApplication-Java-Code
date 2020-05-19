package com.fagito.service.abstractfactory;

import org.springframework.stereotype.Service;

@Service
public abstract class Customer {

	public abstract CalculateDiscountAbstract calculateDiscountRate();
	public abstract CalculateRewardAbstract calculateRewardPoints();
}
