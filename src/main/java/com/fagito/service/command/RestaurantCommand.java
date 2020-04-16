package com.fagito.service.command;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.fagito.dto.SignUpDTO;
import com.fagito.model.Food;
import com.fagito.model.Restaurant;
import com.fagito.model.RestaurantRecord;
import com.fagito.model.SignUp;
import com.fagito.repository.FoodRepository;
import com.fagito.repository.MenuRepository;
import com.fagito.repository.RestaurantRecordRepository;
import com.fagito.repository.RestaurantRepository;
import com.fagito.repository.SignUpRepository;
import com.fagito.service.UserService;
import com.fagito.validator.UserValidator;
import com.fagito.view.Add_Restaurant_UI;
@Service
@PropertySource("application.properties")
public class RestaurantCommand {
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private SignUpRepository signupRepository;
	
	@Autowired 
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private RestaurantRecordRepository restaurantRecordRepository;
	@Autowired
	private FoodRepository foodRepository;
	
	@Value("${spring.user.already_exsist}")
	String errorRegister;
	public String add(Add_Restaurant_UI add_restaurant_ui) throws Exception 
	{
		try
		{
				UserService us=new UserService();
				int exsist=signupRepository.findByEmailbyId(add_restaurant_ui.getEmail());
				if(exsist!=0) {
					System.out.print(errorRegister);
					throw new Exception(errorRegister);
				}
				String signup_id,rest_id,valid;
				SignUp signup=new SignUp();
				SignUpDTO signupDTO=new SignUpDTO();
				BeanUtils.copyProperties(add_restaurant_ui, signupDTO);
				/*alid=userValidator.validate(signupDTO);
				if(valid!=null)
				{
					throw new Exception(valid);
				}*/
				Restaurant restaurant=new Restaurant();
				RestaurantRecord restaurantRecord=new RestaurantRecord();
				
				rest_id=restaurantRecordRepository.findLastRecord();
				if(rest_id==null)
				{
					restaurantRecord.setRestaurant_id("R"+100);
				}
				else
				{
					restaurantRecord.setRestaurant_id("R"+String.valueOf(Integer.parseInt(rest_id.substring(1))+1));
				}
				
				restaurantRecord.setAccept_gold(add_restaurant_ui.getAccept_gold());
				restaurantRecord.setLatitude(add_restaurant_ui.getLatitude());
				restaurantRecord.setLongitude(add_restaurant_ui.getLongitude());
				
				restaurantRecordRepository.save(restaurantRecord);
				
				restaurant.setRestaurant_id(restaurantRecord.getRestaurant_id());
				restaurant.setRestaurant_name(add_restaurant_ui.getRestaurant_name());
				restaurant.setOpening_time(null);
				restaurant.setClosing_time(null);
				restaurant.setDays(null);
				restaurant.setStudent_discount(0);
				restaurant.setGeneral_discount(0);
				
				restaurantRepository.save(restaurant);
				
				
				signup_id=signupRepository.findLastRecord();
				signup.setSign_up_id("S"+String.valueOf(Integer.parseInt(signup_id.substring(2))+1));
				signup.setEmail(add_restaurant_ui.getEmail());
				signup.setPassword(add_restaurant_ui.getPassword());
				signup.setUser_id(restaurant.getRestaurant_id());
				
				signupRepository.save(signup);
				
				
				
				return "Successfully Added Restaurant";
		}
		catch(Exception e)
		{
			throw new Exception("Restaurant not added successfully because of "+e.getMessage());
		}
		
	}
	
	public String remove(Add_Restaurant_UI add_restaurant_ui) throws Exception 
	{
		try
		{
			String restaurant_id,signup_id,menu_id;
			
			restaurant_id=restaurantRepository.findByRestaurantName(add_restaurant_ui.getRestaurant_name());
			if(restaurant_id==null)
				throw new Exception();
			menu_id=menuRepository.findMenuId(restaurant_id);
			List<Food> food_list=foodRepository.findByMenuId(menu_id);
			Iterator<Food> food_iterator=food_list.iterator();
			while(food_iterator.hasNext())
			{
				foodRepository.deleteById(food_iterator.next().getFood_id());
			}
			if(menu_id!=null)
				menuRepository.deleteById(menu_id);
			restaurantRepository.deleteById(restaurant_id);
			restaurantRecordRepository.deleteById(restaurant_id);
			signup_id=signupRepository.getByUserId(restaurant_id);
			signupRepository.deleteById(signup_id);
			
			
			return "Successfully Deleted Restaurant";
		}
		catch(Exception e)
		{
			throw new Exception("Restaurant not Successfully deleted because name not present");
		}
	}
}


