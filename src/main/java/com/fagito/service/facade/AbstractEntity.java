package com.fagito.service.facade;

import com.fagito.service.abstractfactory.CalculateDiscountAbstract;
import com.fagito.service.abstractfactory.CalculateRewardAbstract;

public abstract class AbstractEntity {
	
	public abstract CalculateDiscountAbstract CreateUserTypeA(String strType);
    public abstract CalculateRewardAbstract CreateUserTypeB(String strType);
}
