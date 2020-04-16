package com.fagito.service.mediator;

public class RestaurantUser extends User {
	
	public RestaurantUser(Mediator mediator,String id)
	{
		super(mediator,id);
	}
	public void send(String orderId)
	{
		//mediator.sendMessage(orderId,"DELIVERY","Waiting of delivery"+orderId+"!!!");
		mediator.sendMessage(orderId,"CUSTOMER","Food prepared and out for delivery"+orderId+"!!!");
	}
	
}
