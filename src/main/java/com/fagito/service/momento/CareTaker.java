package com.fagito.service.momento;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {

	private List<Memento> mementoList = new ArrayList<Memento>();

	   public void add(Memento state)
	   {
	      mementoList.add(state);
	   }
	   public Memento get(int index)
	   {
	      return mementoList.get(index);
	   }
	   public String lastInstrction()
	   {
		   return mementoList.get(mementoList.size()-1).getState();
	   }
}
