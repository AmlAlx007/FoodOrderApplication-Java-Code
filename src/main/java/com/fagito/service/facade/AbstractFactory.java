package com.fagito.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fagito.service.abstractfactory.CalculateDiscountAbstract;
import com.fagito.service.abstractfactory.CalculateRewardAbstract;
import com.fagito.service.abstractfactory.General;
import com.fagito.service.abstractfactory.Student;
@Component
public class AbstractFactory extends AbstractEntity {

	@Autowired
	private Student student;
	
	@Autowired
	private General general;
	
	public CalculateDiscountAbstract CreateUserTypeA(String strFactoryType)
    {
		if(strFactoryType.compareTo("S")==0)
		{
			return student.calculateDiscountRate();
		}
		else
		{
			return general.calculateDiscountRate();
		}
    }

    public CalculateRewardAbstract CreateUserTypeB(String strFactoryType)
    {
    	if(strFactoryType.compareTo("S")==0)
		{
			return student.calculateRewardPoints();
		}
		else
		{
			return general.calculateRewardPoints();
		} 
    }

}
