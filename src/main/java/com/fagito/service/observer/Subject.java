package com.fagito.service.observer;

public interface Subject {
	
	public void Attach(Observer o);
	public void Dettach(Observer o);
	public void Notify() throws Exception;
	
}
