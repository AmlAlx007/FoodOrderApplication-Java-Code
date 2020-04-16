package com.fagito.dto;

public class RestaurantDTO {

	private String restaurant_id;
	private String restaurant_name;
	private String opening_time;
	private String closing_time;
	private String days;
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getOpening_time() {
		return opening_time;
	}
	public void setOpening_time(String opening_time) {
		this.opening_time = opening_time;
	}
	public String getClosing_time() {
		return closing_time;
	}
	public void setClosing_time(String closing_time) {
		this.closing_time = closing_time;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
}
