package com.fagito.service.factory;

public abstract class Customer_Discount {
	protected int rest_discount_rate;
	public abstract void set_rate(int rest_discout_rate);
	
	public float calculateDiscountRate(float actual_amount)
	{
		float discount_value=(float)(rest_discount_rate/100.0);
		float discount=actual_amount-(discount_value*actual_amount);
		return discount;
	}

}
