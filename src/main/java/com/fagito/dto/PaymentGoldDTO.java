package com.fagito.dto;

public class PaymentGoldDTO {
	private String membership;
	private String customer_id;
	private String pay_result;
	private int months;
	private int subscribe;
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getPay_result() {
		return pay_result;
	}
	public void setPay_result(String pay_result) {
		this.pay_result = pay_result;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	public String getMembership() {
		return membership;
	}
	public void setMembership(String membership) {
		this.membership = membership;
	}
	
	

}
