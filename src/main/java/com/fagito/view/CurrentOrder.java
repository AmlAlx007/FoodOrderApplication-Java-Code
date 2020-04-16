package com.fagito.view;

import java.sql.Timestamp;
import java.util.List;

public class CurrentOrder {
	
	private String orderId;
	private String orderStatus;
	private Timestamp timeStamp;
	private List<Order_Products> foodList;
	public List<Order_Products> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<Order_Products> foodList) {
		this.foodList = foodList;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
}
