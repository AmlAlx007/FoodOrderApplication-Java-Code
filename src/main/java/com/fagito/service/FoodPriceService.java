package com.fagito.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.model.Food;
import com.fagito.model.Restaurant;
import com.fagito.model.RestaurantRecord;
import com.fagito.repository.AdminSeasonalOffersRepository;
import com.fagito.repository.CustomerRepository;
import com.fagito.repository.FoodRepository;
import com.fagito.repository.OrderRepository;
import com.fagito.repository.RestaurantRecordRepository;
import com.fagito.repository.RestaurantRepository;
import com.fagito.service.abstractfactory.CalculateDiscountAbstract;
import com.fagito.service.abstractfactory.Customer;
import com.fagito.service.abstractfactory.General;
import com.fagito.service.abstractfactory.Student;
import com.fagito.service.factory.Customer_Discount;
import com.fagito.service.factory.GetCustomerFactory;
import com.fagito.service.state.GoldMemberState;
import com.fagito.service.state.MembershipContext;
import com.fagito.service.state.SilverMemberState;
import com.fagito.view.Food_Price;
import com.fagito.view.Food_Price_UI;

@Service
public class FoodPriceService {

	@Autowired
	private FoodRepository food_repository;
	@Autowired
	private RestaurantRecordRepository restaurant_record_repository;
	@Autowired
	private RestaurantRepository restaurant_repository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AdminSeasonalOffersRepository adminOffers;
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired 
	private Student student;
	
	@Autowired
	private General general;
	
	@Autowired
	private MembershipContext membershipContext;

	public Food_Price get_food_details(Food_Price_UI food_price_ui) throws Exception {
		// Factory Pattern implemented in this method

		GoldMemberState goldState;
		SilverMemberState silverState;

		int adminDiscount=0;
		float discountAmount=0;
		Food_Price food_price = new Food_Price();
		GetCustomerFactory getCustomerFactory = new GetCustomerFactory();
		Customer_Discount customer_discount;
		
        String membership=customerRepository.findGoldByCustomerId(food_price_ui.getCustomer_id());
        
        
        
        Optional<Food> food = food_repository.findById(food_price_ui.getFood_id());
        
        discountAmount=food.get().getFood_price();
        
        if(membership.compareTo("N")!=0)
        {
        	goldState=new GoldMemberState(adminOffers);
        	silverState=new SilverMemberState(adminOffers);
        	
        	if(membership.compareTo("G")==0)
        		membershipContext.setState(goldState);
        	else if(membership.compareTo("S")==0)
        		membershipContext.setState(silverState);
        	
        	discountAmount=membershipContext.doAction(discountAmount);
        	adminDiscount=membershipContext.getDiscountRate();
        }
		
		
		if(!food.isPresent())
			throw new Exception("Food Not Present");
		
		food_price.setFood_name(food.get().getFood_name());
		food_price.setActual_price(food.get().getFood_price());

		Optional<RestaurantRecord> restaurant_record = restaurant_record_repository.findById(food_price_ui.getRestaurant_id());
		food_price.setIs_gold(restaurant_record.get().getAccept_gold());

		Optional<Restaurant> restaurant = restaurant_repository.findById(food_price_ui.getRestaurant_id());
		food_price.setRestaurant_name(restaurant.get().getRestaurant_name());

		
		Customer customer;
		
		// factory pattern-setting up values and calculating amount
		customer_discount = getCustomerFactory.getCustomer(food_price_ui.getCustomer_id().substring(0, 1));
		if (food_price_ui.getCustomer_id().substring(0, 1).equals("S")) {
			customer=student;
			food_price.setDiscount(restaurant.get().getStudent_discount()+adminDiscount);
		} else {
			          
			customer=general;
			food_price.setDiscount(restaurant.get().getGeneral_discount()+adminDiscount);
		}
		
		CalculateDiscountAbstract calculateDiscountAbstract=customer.calculateDiscountRate();
		food_price.setDisount_price(calculateDiscountAbstract.calculateDiscountRate(discountAmount,food_price_ui.getRestaurant_id()));


		return food_price;

	}

}
