package com.fagito.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="payment_ref")
public class PaymentReference {

	@Id
	private String payment_ref;
	
	public String getPayment_ref() {
		return payment_ref;
	}
	public void setPayment_ref(String payment_ref) {
		this.payment_ref = payment_ref;
	}
	
	
}
