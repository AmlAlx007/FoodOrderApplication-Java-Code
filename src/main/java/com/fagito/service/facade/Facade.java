package com.fagito.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fagito.service.abstractfactory.CalculateDiscountAbstract;
import com.fagito.service.abstractfactory.CalculateRewardAbstract;
@Component
public class Facade {

	public CalculateDiscountAbstract calculateDiscountAbstract;
	public CalculateRewardAbstract calculateRewardAbstract;

	@Autowired
	private AbstractFactory abstractFactory;

	public Facade FacadeCreateProduct(String customerType, String operation) {
		switch (operation) {
		case "discount":
			calculateDiscountAbstract = abstractFactory.CreateUserTypeA(customerType);
			break;
		case "reward":
			calculateRewardAbstract = abstractFactory.CreateUserTypeB(customerType);
			break;
		}
		return this;
	}
	
	public void calculateDiscount(Float amount,String restaurantId)
	{
		
	}
}
