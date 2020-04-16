package com.fagito.service.mediator;

public abstract class User {
	
	protected Mediator mediator;
	String id;
	
	public User(Mediator mediator,String id)
	{
		this.mediator=mediator;
		this.id=id;
	}
	
	public void send(String msg) {};
	public void revieve(String msg) {};
	
}
