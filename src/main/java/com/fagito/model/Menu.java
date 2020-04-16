package com.fagito.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class Menu {
	
	@Id
	private String menu_id;
	private String restaurant_id;
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaraunt_id) {
		this.restaurant_id = restaraunt_id;
	}
	
	
	

}
