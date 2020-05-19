package com.fagito.service.visitor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ObjectElementList {

	List<ObjectElement> elementList;
	
	public ObjectElementList()
	{
		elementList=new ArrayList<ObjectElement>();
	}
	public void addElements(ObjectElement objectElement)
	{
		elementList.add(objectElement);
	}
	public boolean accept(ValidationVisitor validationVisitor) throws Exception
	{
		try
		{
			for (ObjectElement object:elementList) {
				object.accept(validationVisitor);
			}
			return true;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
