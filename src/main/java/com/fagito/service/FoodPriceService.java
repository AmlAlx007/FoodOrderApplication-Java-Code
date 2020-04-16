package com.fagito.service;


import java.util.Date;
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
import com.fagito.service.abstractfactory.CalculateRewardAbstract;
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

	public Food_Price get_food_details(Food_Price_UI food_price_ui) throws Exception {
		// Factory Pattern implemented in this method
		MembershipContext membershipContext=new MembershipContext();
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

	/*public List<Food_Price> get_all_food_price(String customer_id) {
		
		int admin_discount_rate=0;
		GetCustomerFactory getCustomerFactory = new GetCustomerFactory();
		Customer_Discount customer_discount=getCustomerFactory.getCustomer(customer_id.substring(0, 1));
		boolean is_gold=customerRepository.findGoldByCustomerId(customer_id);
        Date date=new Date(System.currentTimeMillis());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        if(is_gold)
        {
        	AdminSeasonalOffers adminSeasonalOffers=adminOffers.findLatest();
        	if(!sqlDate.after(adminSeasonalOffers.getIs_offer_expired()))
        	{
        		admin_discount_rate=adminSeasonalOffers.getOffer_percentage();
        	}
        }
		List<Food_Price> food_price_list = new ArrayList<Food_Price>();

		List<Food> food_list = food_repository.findAll();
		List<Menu> menu_list = menu_repository.findAll();
		List<Restaurant> restaurant_list = restaurant_repository.findAll();

		Iterator<Menu> menu_iterator;
		Iterator<Restaurant> restaurant_iterator;

		Iterator<Food> food_iterator = food_list.iterator();
		while (food_iterator.hasNext()) {
			Food food_object = new Food();
			food_object = food_iterator.next();
			menu_iterator = menu_list.iterator();
			while (menu_iterator.hasNext()) {
				Menu menu_object = new Menu();
				menu_object = menu_iterator.next();
				if (food_object.getMenu_id().equals(menu_object.getMenu_id())) {
					restaurant_iterator = restaurant_list.iterator();
					while (restaurant_iterator.hasNext()) {
						Restaurant restaurant_object = new Restaurant();
						restaurant_object = restaurant_iterator.next();
						if (menu_object.getRestaurant_id().equals(restaurant_object.getRestaurant_id())) {
							Food_Price food_price_object = new Food_Price();

							food_price_object.setRestaurant_name(restaurant_object.getRestaurant_name());
							food_price_object.setFood_name(food_object.getFood_name());
							if (customer_id.substring(0, 1).equals("S"))
								food_price_object.setDiscount(restaurant_object.getStudent_discount());
							else
								food_price_object.setDiscount(restaurant_object.getGeneral_discount()+admin_discount_rate);

							food_price_object.setActual_price(food_object.getFood_price());

							RestaurantRecord restaurant_record_object = restaurant_record_repository
									.findRestaurantRecord(restaurant_object.getRestaurant_id());
							food_price_object.setIs_gold(restaurant_record_object.getAccept_gold());
							
							customer_discount.set_rate(food_price_object.getDiscount(),admin_discount_rate);
							food_price_object.setDisount_price(customer_discount.calculateDiscountRate(food_price_object.getActual_price()));
							
							food_price_list.add(food_price_object);
						}

					}
				}

			}

		}
		
		return food_price_list;
	}*/

}
