
package com.fagito.service;
   
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.model.Order;
import com.fagito.model.Order_Item_Record;
import com.fagito.repository.NotificationRepository;
import com.fagito.repository.OrderRepository;
import com.fagito.repository.Order_Item_Record_Repository;
import com.fagito.service.mediator.Mediator;
import com.fagito.service.mediator.MediatorImpl;
import com.fagito.service.mediator.OrderUser;
import com.fagito.service.mediator.RestaurantUser;
import com.fagito.service.mediator.User;
import com.fagito.view.CurrentOrder;
import com.fagito.view.Order_Products;
   
@Service 
public class RestaurantService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private Order_Item_Record_Repository itemRecordRepository;
	
	@Autowired 
	private NotificationRepository notificationRepository;
   
   public List<CurrentOrder> getCurrentOrders(String restaurantId) {
	   
	   List<CurrentOrder> currentOrder=new ArrayList<>();
	   List<String> statusList=new ArrayList<String>();
	   statusList.add("Order Placed");
	   statusList.add("Accepted By Restaurant");
	   statusList.add("Food Prepared in Kitchen");
	   statusList.add("Out for Delivery");
	   statusList.add("Order Delivered");
	   
	   List<Order_Item_Record> itemRecordList=new ArrayList<>();  
	   Iterator<Order_Item_Record> itemRecordListIterator;
	   List<Order> order_list= orderRepository.getByRId(restaurantId);
	   Iterator<Order> orderIterator= order_list.iterator();
	   
	   DateFormat df = new SimpleDateFormat("dd/MM/yy");
	   Timestamp ts=new Timestamp(System.currentTimeMillis());
	   Date todayDate=new Date(ts.getTime());
	   String today=df.format(todayDate);
	   System.out.println(today);
	   while(orderIterator.hasNext())
	   {
		   Order orderObject=orderIterator.next();
		   Date orderDate=new Date(orderObject.getOrder_timestamp().getTime());
		   String order=df.format(orderDate);
		   System.out.println(orderObject.getOrder_id()+"  "+order);
		   if((orderObject.getOrder_status()!=4) && (today.compareTo(order)==0))
		   {
			   itemRecordList=itemRecordRepository.findByOrderId(orderObject.getOrder_id());
			   itemRecordListIterator=itemRecordList.iterator();
			   List<Order_Products> orderProducts=new ArrayList<>();
			   while(itemRecordListIterator.hasNext())
			   {
				   Order_Item_Record recordObject=itemRecordListIterator.next();  
				   Order_Products orderProductsObj=new Order_Products();
				   orderProductsObj.setFood_id(recordObject.getFood_id());
				   orderProductsObj.setQuantity(recordObject.getQuantity());
				   orderProducts.add(orderProductsObj);
			   }
			   CurrentOrder currentOrderObj=new CurrentOrder();
			   currentOrderObj.setFoodList(orderProducts);
			   currentOrderObj.setOrderId(orderObject.getOrder_id());
			   currentOrderObj.setTimeStamp(orderObject.getOrder_timestamp());
			   currentOrderObj.setOrderStatus(statusList.get(orderObject.getOrder_status()));
			   currentOrder.add(currentOrderObj);
		   }
	   }
	   return currentOrder;
   }

   public String acceptOrder(String orderId)
   {
	   
	   Order orderObject=new Order();
	   orderObject=orderRepository.getByOrderId(orderId);
	   orderObject.setOrder_status(1);
	   orderRepository.save(orderObject);
	   
	   
	   List<User> customerList=new ArrayList<User>();
		
		Mediator mediator=new MediatorImpl(orderRepository,notificationRepository);
		
		User orderUser=new OrderUser(mediator,"CUSTOMER");
		User restaurantUser=new RestaurantUser(mediator,"RESTAURANT");
		//User deliveryUser=new DeliveryUser(mediator,"DELIVERY"); A new deliver user can be added
		
		customerList.add(orderUser);
		customerList.add(restaurantUser);
		//customerList.add(deliveryUser);
		mediator.addCustomerList(customerList);
	   
	   restaurantUser.send(orderId);
	   
	   
	   return "Order Accepted";
   }
   
 }
 
   