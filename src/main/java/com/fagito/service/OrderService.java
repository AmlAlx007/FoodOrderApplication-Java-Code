package com.fagito.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fagito.model.Order;
import com.fagito.model.Order_Item_Record;
import com.fagito.model.Payment;
import com.fagito.model.PaymentReference;
import com.fagito.repository.NotificationRepository;
import com.fagito.repository.OrderRepository;
import com.fagito.repository.Order_Item_Record_Repository;
import com.fagito.repository.PaymentReferenceRepository;
import com.fagito.repository.PaymentRepository;
import com.fagito.service.mediator.RestaurantUser;
import com.fagito.service.mediator.Mediator;
import com.fagito.service.mediator.MediatorImpl;
import com.fagito.service.mediator.OrderUser;
import com.fagito.service.mediator.User;
import com.fagito.service.momento.CareTaker;
import com.fagito.service.momento.Originator;
import com.fagito.service.visitor.CustomerElement;
import com.fagito.service.visitor.ObjectElementList;
import com.fagito.service.visitor.PaymentElement;
import com.fagito.service.visitor.ValidationVisitorImpl;
import com.fagito.validator.PaymentValidator;
import com.fagito.view.Order_Place_UI;
import com.fagito.view.Order_Products;
@Service
public class OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private Order_Item_Record_Repository order_Item_Record_Repository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private ObjectElementList objectElementList;
	
	@Autowired
	private ValidationVisitorImpl validationVisitorImpl;
	
	public String place_order(Order_Place_UI order_place_ui) throws Exception
	{	
		PaymentReference paymentRef=new PaymentReference();
		
		paymentRef.setPayment_ref(order_place_ui.getPayment_ref_id());
		
		//ObjectElementList objectElementList=new ObjectElementList();
		objectElementList.addElements(new PaymentElement(paymentRef));
		try
		{
			objectElementList.accept(validationVisitorImpl);
		}
		catch(Exception e)
		{
			throw e;
		}
		
		
		Originator originator=new Originator();
		CareTaker careTaker=new CareTaker();
		
		originator.setState("CREATED");
		careTaker.add(originator.saveStateToMemento());
		
		Order order=new Order();
		Order_Item_Record order_item_record=new Order_Item_Record();
		
		
		try
		{
			String order_id,order_item_id,payment_id;
			order_id=orderRepository.findLastRecord();
			if(order_id==null)
			{
				order.setOrder_id("OR100");
			}
			else
			{
				order.setOrder_id("OR"+String.valueOf(Integer.parseInt(order_id.substring(2))+1));
			}
			order.setCustomer_id(order_place_ui.getLogin_id());
			order.setRestaurant_id(order_place_ui.getRestaurant_id());
			order.setTotal_amount(order_place_ui.getPayment_amount());
			order.setTotal_items(order_place_ui.getOrder_products().size());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			order.setOrder_timestamp(timestamp);
			order.setOrder_status(0);
			
			
			Iterator<Order_Products> order_product_iterator=order_place_ui.getOrder_products().iterator();
			while(order_product_iterator.hasNext())
			{
				order_item_id=order_Item_Record_Repository.findLastRecord();
				Order_Products order_products=order_product_iterator.next();
				if(order_item_id==null)
				{
					order_item_record.setOrder_item_id("OI100");
				}
				else
				{
					order_item_record.setOrder_item_id("OI"+String.valueOf(Integer.parseInt(order_item_id.substring(2))+1));
				}
				order_item_record.setFood_id(order_products.getFood_id());
				order_item_record.setQuantity(order_products.getQuantity());
				order_item_record.setOrder_id(order.getOrder_id());
				
			}
			
			originator.setState("PAYMENT DUE");
			careTaker.add(originator.saveStateToMemento());
			
			Payment payment=new Payment();
			
			if(order_place_ui.getPaymentStatus()==1)
			{
				payment_id=paymentRepository.findLastRecord();
				if(payment_id==null)
				{
					payment.setPayment_id("P100");
				}
				else
				{
					payment.setPayment_id("P"+String.valueOf(Integer.parseInt(payment_id.substring(1))+1));
				}
				payment.setPayment_type(order_place_ui.getPayment_type());
				payment.setPayment_amount(order_place_ui.getPayment_amount());
				payment.setOrder_id(order.getOrder_id());
				payment.setPayment_ref(order_place_ui.getPayment_ref_id());
				
				originator.setState("COMPLETE");
				careTaker.add(originator.saveStateToMemento());
			}
			
			if(careTaker.lastInstrction().compareTo("COMPLETE")==0)
			{
				order.setInternal_status(careTaker.lastInstrction());
				orderRepository.save(order);
				order_Item_Record_Repository.save(order_item_record);
				paymentRepository.save(payment);
			}
			else if(careTaker.lastInstrction().compareTo("PAYMENT DUE")==0)
			{
				return "Payment Unsuccessful";
			}
			List<User> customerList=new ArrayList<User>();
			
			Mediator mediator=new MediatorImpl(orderRepository,notificationRepository);
			
			User orderUser=new OrderUser(mediator,"CUSTOMER");
			User restaurantUser=new RestaurantUser(mediator,"RESTAURANT");
			
			customerList.add(orderUser);
			customerList.add(restaurantUser);
			mediator.addCustomerList(customerList);
			
			orderUser.send(order.getOrder_id());
			return "Successfully placed order";
		}
		catch(Exception e)
		{
			throw new Exception("Order not Successfully placed");
		}
		
	}	
}
