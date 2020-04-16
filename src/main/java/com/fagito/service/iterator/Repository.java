package com.fagito.service.iterator;

import java.util.ArrayList;
import java.util.List;

public class Repository implements Container{
	public List<Object> names=new ArrayList<Object>();
	
	public Repository(List<Object> object_list)
	{
		this.names=object_list;
	}
	@Override
	public IteratorInterface getIterator()
	{
		return new NameIterator();
	}
	
	private class NameIterator implements IteratorInterface
	{
		int index;
		
		@Override
		public boolean hasNext()
		{
			if(index<names.size())
			{
				return true;
			}
			return false;
		}
		
		@Override
		public Object next()
		{
			if(this.hasNext())
			{
				return names.get(index++);
			}
			return null;
		}
	}				
}