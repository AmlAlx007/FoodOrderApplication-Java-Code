package com.fagito.view;

public class Food_Price {
	
	private String restaurant_name;
	private String food_name;
	private int is_gold;
	private float actual_price;
	private float disount_price;
	private int discount;
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public int getIs_gold() {
		return is_gold;
	}
	public void setIs_gold(int is_gold) {
		this.is_gold = is_gold;
	}
	public float getActual_price() {
		return actual_price;
	}
	public void setActual_price(float actual_price) {
		this.actual_price = actual_price;
	}
	public float getDisount_price() {
		return disount_price;
	}
	public void setDisount_price(float disount_price) {
		this.disount_price = disount_price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
}
