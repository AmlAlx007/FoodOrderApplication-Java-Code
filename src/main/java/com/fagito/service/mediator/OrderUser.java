package com.fagito.service.mediator;

public class OrderUser extends User{
	
	public OrderUser(Mediator mediator,String id)
	{
		super(mediator,id);
	}
	
	public void send(String orderId)
	{
		mediator.sendMessage(orderId,"RESTAURANT","Incomming new Order "+orderId+"!!!");
		mediator.sendMessage(orderId,"CUSTOMER","Placed a new order with order id :"+orderId+"!!!");
	}
}
