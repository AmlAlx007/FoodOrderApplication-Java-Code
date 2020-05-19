package com.fagito.service.abstractfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
