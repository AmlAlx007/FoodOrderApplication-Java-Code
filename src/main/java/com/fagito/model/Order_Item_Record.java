package com.fagito.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_item_record")
public class Order_Item_Record {
	
	@Id
	private String order_item_id;
	@OneToOne(optional=false)
	@JoinColumn(name="order_id",insertable=false,updatable=false)
	private Order order;
	private String order_id;
	@OneToOne(optional=false)
	@JoinColumn(name="food_id",insertable=false,updatable=false)
	private Food food;
	private String food_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getFood_id() {
		return food_id;
	}
	public void setFood_id(String food_id) {
		this.food_id = food_id;
	}
	private int  quantity;
	public String getOrder_item_id() {
		return order_item_id;
	}
	public void setOrder_item_id(String order_item_id) {
		this.order_item_id = order_item_id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
