package com.fagito.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment_gold")
public class PaymentGold {
	
	@Id
	private String pay_id;
	private String pay_result;
	private String customer_id;
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	public String getPay_result() {
		return pay_result;
	}
	public void setPay_result(String pay_result) {
		this.pay_result = pay_result;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	

}
