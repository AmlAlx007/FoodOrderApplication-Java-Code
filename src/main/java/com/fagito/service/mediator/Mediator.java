package com.fagito.service.mediator;

import java.util.List;

public abstract class Mediator {
	
	public abstract void sendMessage(String orderId,String id,String msg);
	public abstract void addCustomerList(List<User> userList);

}
