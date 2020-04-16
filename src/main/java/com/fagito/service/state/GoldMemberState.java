package com.fagito.service.state;

import com.fagito.model.AdminSeasonalOffers;
import com.fagito.repository.AdminSeasonalOffersRepository;

public class GoldMemberState implements State{

	private AdminSeasonalOffersRepository adminOffersRepository;
	
	private int discount;
	
	public GoldMemberState(AdminSeasonalOffersRepository adminOffersRepository)
	{
		this.adminOffersRepository=adminOffersRepository;
	}
	
	
	public float doAction(float amount)
	{
		AdminSeasonalOffers adminSeasonalOffers=new AdminSeasonalOffers();
		adminSeasonalOffers=this.adminOffersRepository.findLatestByDate("G");
		this.discount=adminSeasonalOffers.getOffer_percentage();
		float discount_value=(float)(adminSeasonalOffers.getOffer_percentage()/100.0);

		return amount-(discount_value*amount);

	}
	
	public int getDiscountRate()
	{
		return this.discount;
	}
}
