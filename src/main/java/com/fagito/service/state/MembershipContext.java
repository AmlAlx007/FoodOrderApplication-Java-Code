package com.fagito.service.state;

import org.springframework.stereotype.Component;

@Component
public class MembershipContext implements State {

	private State membershipState;
	
	public void setState(State state)
	{
		this.membershipState=state;
	}
	public State getState()
	{
		return this.membershipState;
	}
	public float doAction(float amount)
	{
		return this.membershipState.doAction(amount);		
	}
	public int getDiscountRate()
	{
		return this.membershipState.getDiscountRate();
	}
}
