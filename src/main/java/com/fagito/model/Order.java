package com.fagito.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_accept")
public class Order {
	
	@Id
	private String order_id;
	@OneToOne(optional=false)
	@JoinColumn(name="customer_id",insertable=false,updatable=false)
	private Customer customer;
	private String customer_id;
	@OneToOne(optional=false)
	@JoinColumn(name="restaurant_id",insertable=false,updatable=false)
	private Restaurant restaurant;
	private String restaurant_id;
	private int total_items;
	private float total_amount;
	private Timestamp order_timestamp;
	private int order_status;
	private String internal_status;
	public String getInternal_status() {
		return internal_status;
	}
	public void setInternal_status(String internal_status) {
		this.internal_status = internal_status;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public int getTotal_items() {
		return total_items;
	}
	public void setTotal_items(int total_items) {
		this.total_items = total_items;
	}
	public float getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public Timestamp getOrder_timestamp() {
		return order_timestamp;
	}
	public void setOrder_timestamp(Timestamp order_timestamp) {
		this.order_timestamp = order_timestamp;
	}
	
	

}
