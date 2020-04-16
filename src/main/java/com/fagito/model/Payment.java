package com.fagito.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {
	
	@Id
	private String payment_id;
	private float payment_amount;
	private String payment_type;
	@OneToOne(optional=false)
	@JoinColumn(name="order_id",insertable=false,updatable=false)
	private Order order;
	private String order_id;
	private String payment_ref;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public float getPayment_amount() {
		return payment_amount;
	}
	public void setPayment_amount(float payment_amount) {
		this.payment_amount = payment_amount;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getPayment_ref() {
		return payment_ref;
	}
	public void setPayment_ref(String payment_ref) {
		this.payment_ref = payment_ref;
	}
	

}
