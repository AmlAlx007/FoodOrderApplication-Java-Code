package com.fagito.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="food")
public class Food {
	
	@Id
	private String food_id;
	private String menu_id;
	private String food_name;
	private float food_price;
	private String food_cuisine;
	public String getFood_id() {
		return food_id;
	}
	public void setFood_id(String food_id) {
		this.food_id = food_id;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public float getFood_price() {
		return food_price;
	}
	public void setFood_price(float food_price) {
		this.food_price = food_price;
	}
	public String getFood_cuisine() {
		return food_cuisine;
	}
	public void setFood_cuisine(String food_cuisine) {
		this.food_cuisine = food_cuisine;
	}
	

}
