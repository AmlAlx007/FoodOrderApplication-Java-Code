package com.fagito.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.model.AdminSeasonalOffers;
import com.fagito.model.Customer;
import com.fagito.repository.AdminSeasonalOffersRepository;
import com.fagito.repository.CustomerRepository;
import com.fagito.repository.SignUpRepository;
import com.fagito.service.observer.GoldCustomer;
import com.fagito.service.observer.GoldService;
import com.fagito.service.observer.GoldorNotGold;
import com.fagito.view.AdminOfferUI;

@Service
public class AdminService {
	
	
	@Autowired
	private AdminSeasonalOffersRepository adminseasonalofferrepo;
	
	@Autowired
	private CustomerRepository customerRespository;
	
	@Autowired
	private SignUpRepository signuprepository;
	
	@SuppressWarnings("deprecation")
	public String set_offer(AdminOfferUI adminofferui)  throws Exception
	{

		
		List<Customer> customer_list;
		
		AdminSeasonalOffers adminseasonaloffers=new AdminSeasonalOffers();
		String offer_id=adminseasonalofferrepo.findLastRecord();
		
		adminseasonaloffers.setOffer_id("O"+String.valueOf(Integer.parseInt(offer_id.substring(1))+1));
		adminseasonaloffers.setOffer_percentage(adminofferui.getGold_per());
		adminseasonaloffers.setUser_type(adminofferui.getMembership());
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getTime().getDate()+adminofferui.getValidity());
        Date date=new Date(cal.getTimeInMillis());
		adminseasonaloffers.setIs_offer_expired(date);
		
		adminseasonalofferrepo.save(adminseasonaloffers);
		
		//observer pattern starts
		customer_list=customerRespository.findAllCustomers();
		System.out.println(customer_list);
		GoldorNotGold gn=new GoldorNotGold();
        gn.setIs_gold(1);
        gn.setMessage("New Offers Available for Gold Customers"+adminofferui.getGold_per()+" %");
        
		GoldService gold_service=new GoldService();
		
		Iterator<Customer> customer_list_iterator=customer_list.iterator();
		
		while(customer_list_iterator.hasNext())
		{
			Customer cust_obj=customer_list_iterator.next();
			GoldCustomer gold_cust=new GoldCustomer((Object)cust_obj,signuprepository);
			gold_service.Attach(gold_cust);
		}
		gold_service.addGoldOrNotGold_list(gn);
		
		return "Successfully updated Offer";
	}
	

}
