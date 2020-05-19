package com.fagito.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer
{
	@Id
	private String customer_id;
	private String customer_name;
	private String membership;
	private Date validity;
	private String usertype;
	private float rewardpoints;
	
	
	public float getRewardpoints() {
		return rewardpoints;
	}
	public void setRewardpoints(float rewardpoints) {
		this.rewardpoints = rewardpoints;
	}
	public Date getValidity() {
		return validity;
	}
	public void setValidity(Date validity) {
		this.validity = validity;
	}
	
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getMembership() {
		return membership;
	}
	public void setMembership(String membership) {
		this.membership = membership;
	}
	
}

