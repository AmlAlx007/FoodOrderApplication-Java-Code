package com.fagito.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="restaurant_record")
public class RestaurantRecord {
	
	@Id
	private String restaurant_id;
	private int accept_gold;
	private double latitude;
	private double longitude;
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public int getAccept_gold() {
		return accept_gold;
	}
	public void setAccept_gold(int accept_gold) {
		this.accept_gold = accept_gold;
	}
	

}
