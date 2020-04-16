package com.fagito.service.observer;

import java.util.ArrayList;
import java.util.List;

public class GoldService implements Subject {
	
	List<Observer> observer_list;
	List<GoldorNotGold> goldOrNotGold_list;
	
	public GoldService() {
		observer_list=new ArrayList<Observer>();
		goldOrNotGold_list=new ArrayList<GoldorNotGold>();
	}
	
	public List<GoldorNotGold> getGoldOrNotGold_list() {
		return goldOrNotGold_list;
	}
	public void addGoldOrNotGold_list(GoldorNotGold gn) throws Exception {
		goldOrNotGold_list.add(gn);
		Notify();
	}
	public void Attach(Observer o)
	{
		observer_list.add(o);
	}
	public void Dettach(Observer o)
	{
		observer_list.remove(o);
	}
	public void Notify() throws Exception
	{
		int i;
		for(i=0;i<observer_list.size();i++)
		{
			observer_list.get(i).update(this);
		}
	}
}
