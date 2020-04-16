package com.fagito.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_seasonal_offers")
public class AdminSeasonalOffers {
	@Id
	private String offer_id;
	private String user_type;
	private int offer_percentage;
	private Date is_offer_expired;
	public String getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(String offer_id) {
		this.offer_id = offer_id;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public int getOffer_percentage() {
		return offer_percentage;
	}
	public void setOffer_percentage(int offer_percentage) {
		this.offer_percentage = offer_percentage;
	}
	public Date getIs_offer_expired() {
		return is_offer_expired;
	}
	public void setIs_offer_expired(Date is_offer_expired) {
		this.is_offer_expired = is_offer_expired;
	}
	
	

}
