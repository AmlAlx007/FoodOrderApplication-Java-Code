package com.fagito.service.mediator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fagito.model.Customer;
import com.fagito.model.Notification;
import com.fagito.model.Order;
import com.fagito.repository.NotificationRepository;
import com.fagito.repository.OrderRepository;

public class MediatorImpl extends Mediator{

	
	private List<User> userList;
	private OrderRepository orderRepository;
	private NotificationRepository notificationRepository;
	public MediatorImpl(OrderRepository orderRepository,NotificationRepository notificationRepository)
	{
		this.userList=new ArrayList<User>();
		this.orderRepository=orderRepository;
		this.notificationRepository=notificationRepository;
	}
	
	public void addCustomerList(List<User> userList)
	{
		this.userList=userList;
	}
	
	public void sendMessage(String orderId,String id,String msg)
	{	
		String notification_id;
		Notification notiId=new Notification();
		for(User userObj:this.userList)
		{
			if(userObj.id==id)
			{
				Order orderObject=orderRepository.getByOrderId(orderId);
				Notification notificationObject=new Notification();
				
				notiId=null;
				
				if(id=="CUSTOMER")
					notiId=notificationRepository.getNotiId(orderObject.getOrder_id(),orderObject.getCustomer_id());
				else if(id=="RESTAURANT")
					notiId=notificationRepository.getNotiId(orderObject.getOrder_id(),orderObject.getRestaurant_id());
				
				if(notiId==null)
				{
					notification_id=notificationRepository.findLastRecord();
					if(notification_id==null)
					{
						notificationObject.setNid("N100");
					}
					else
					{
						notificationObject.setNid("N"+String.valueOf(Integer.parseInt(notification_id.substring(1))+1));
					}
					notificationObject.setOid(orderObject.getOrder_id());
					if(id=="CUSTOMER")
						notificationObject.setUserId(orderObject.getCustomer_id());
					else if(id=="RESTAURANT")
						notificationObject.setUserId(orderObject.getRestaurant_id());
					
					notificationObject.setMessage(msg);
					
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					notificationObject.setTime(timestamp);
					notificationRepository.save(notificationObject);
				}
				else
				{
					notiId.setMessage(msg);
					notificationRepository.save(notiId);
				}
				
			}
		}
		
	}
	
}
