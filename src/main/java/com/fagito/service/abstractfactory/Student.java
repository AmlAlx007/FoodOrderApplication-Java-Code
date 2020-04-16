package com.fagito.service.abstractfactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student extends Customer{

	@Autowired
	private StudentCalculateDiscount studentCalculateDiscount;
	
	@Autowired
	private StudentCalculateReward studentCalculateReward;
	
	
	public CalculateDiscountAbstract calculateDiscountRate(){
		
		return studentCalculateDiscount; 
	}	
	public CalculateRewardAbstract calculateRewardPoints() {
		
		return studentCalculateReward;
	}
}
