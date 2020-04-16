package com.fagito.view;

import java.util.List;

public class Order_Place_UI {
	
	private String login_id;
	private String restaurant_id;
	private List<Order_Products> order_products;
	private String payment_type;
	private float payment_amount;
	private String payment_ref_id;
	private int paymentStatus;
	
	public int getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public float getPayment_amount() {
		return payment_amount;
	}
	public void setPayment_amount(float payment_amount) {
		this.payment_amount = payment_amount;
	}
	public String getPayment_ref_id() {
		return payment_ref_id;
	}
	public void setPayment_ref_id(String payment_ref_id) {
		this.payment_ref_id = payment_ref_id;
	}
	public List<Order_Products> getOrder_products() {
		return order_products;
	}
	public void setOrder_products(List<Order_Products> order_products) {
		this.order_products = order_products;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	

}
